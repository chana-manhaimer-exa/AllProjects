package com.exabeam.DocPortalSanityTool;

import javax.swing.JOptionPane;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkChecker {

    private static int totalFiles = 0;
    private static int totalBrokenLinks = 0;
    private static int totalValidLinks = 0;

    private static final String PORTAL_URL = "https://docs.exabeam.com/en/";
    private static final String ROOT_INPUT = "Input/dist/private";
    private static final String RESOURCES_FOLDER = "Resources/";
    private static final String ERROR_CODES_FILE = "ErrorCodes.md";
    private static final String GITHUB_URL_1 = "https://github.com/ExabeamLabs/Content-Library-CIM1";
    private static final String GITHUB_URL_2 = "https://github.com/ExabeamLabs/Content-Library-CIM2";
    private static final String GITHUB_REF_FOLDER = "githublinks/";
    private static final String GITHUB_REF_FILE = "GitHubRefReport.md";

    private static final Pattern LINK_PATTERN = Pattern.compile(
            "<a\\s+[^>]*href\\s*=\\s*\"(http[^\"]*)\"[^>]*>(.*?)</a>", Pattern.CASE_INSENSITIVE);
    private static final Pattern TITLE_PATTERN = Pattern.compile(
            "<h2\\s+[^>]*class\\s*=\\s*\"[^\"]*title[^\"]*\"[^>]*>(.*?)</h2>", Pattern.CASE_INSENSITIVE);

    private static String outputReportFile = "DocumentationQualityReport.md";
    private static String outputFolder = "";
    private static String inputFolder = "";
    private static Map<String, ProductCounter> productsHashMap;

    public static void CheckLinks(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java LinkChecker <input_folder> <output_report_file>");
            return;
        }

        inputFolder = args[0];
        outputFolder = args[1];
        outputReportFile = outputFolder + outputReportFile;
        productsHashMap = new HashMap<>();

        File folder = new File(inputFolder);
        if (!folder.isDirectory()) {
            System.out.println("The input path is not a directory.");
            return;
        }

        try {
            // Phase 1: scan all HTML files — no network I/O
            List<RawLink> rawLinks = new ArrayList<>();
            List<GitHubLink> githubLinks = new ArrayList<>();
            processFolder(folder, rawLinks, githubLinks);

            // Phase 2: check all unique URLs in parallel
            Set<String> uniqueUrls = new HashSet<>();
            for (RawLink r : rawLinks) uniqueUrls.add(r.url);
            Map<String, Integer> urlStatusMap = checkLinksInParallel(uniqueUrls);

            // Phase 3: classify links and build report data
            List<InvalidLink> invalidLinks = new ArrayList<>();
            for (RawLink raw : rawLinks) {
                int responseCode = urlStatusMap.getOrDefault(raw.url, -1);
                InvalidLink link = new InvalidLink(raw.url, raw.folderName, raw.fileName, responseCode, raw.linkText, raw.pageTitle);
                if (isBroken(responseCode)) {
                    totalBrokenLinks++;
                    invalidLinks.add(link);
                    addOrUpdateProduct(productsHashMap, link.productName, 1);
                } else {
                    totalValidLinks++;
                    addOrUpdateProduct(productsHashMap, link.productName, 0);
                }
            }

            createErrorCodesMDFile();
            writeMainReportPage(outputReportFile, productsHashMap);
            writeProductReportsByInvalidLinks(invalidLinks);
            writeGitHubRefReport(githubLinks);
            showCompletionStatus(totalFiles, totalBrokenLinks, totalValidLinks);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isBroken(int responseCode) {
        return responseCode != 200 && responseCode != 301 && responseCode != 402
                && responseCode != 403 && responseCode != -1;
    }

    private static Map<String, Integer> checkLinksInParallel(Set<String> urls) throws InterruptedException {
        Map<String, Integer> results = new ConcurrentHashMap<>();
        if (urls.isEmpty()) return results;
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(urls.size(), 50));
        for (String url : urls) {
            executor.submit(() -> {
                int code = checkLink(url);
                results.put(url, code);
                System.out.println((isBroken(code) ? "Broken" : "Valid") + " link: " + url);
            });
        }
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.MINUTES);
        return results;
    }

    private static void addOrUpdateProduct(Map<String, ProductCounter> map, String productName, int count) {
        ProductCounter pc = map.get(productName);
        if (pc != null) {
            pc.setInvalidLinksCounter(pc.getInvalidLinksCounter() + count);
        } else {
            map.put(productName, new ProductCounter(productName, count));
        }
    }

    private static void processFolder(File folder, List<RawLink> rawLinks, List<GitHubLink> githubLinks) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                processFolder(file, rawLinks, githubLinks);
            } else if (file.getName().endsWith(".html")) {
                totalFiles++;
                processFile(file, rawLinks, githubLinks);
            }
        }
    }

    private static void processFile(File file, List<RawLink> rawLinks, List<GitHubLink> githubLinks) throws IOException {
        String htmlContent = Files.readString(file.toPath());
        String pageTitle = extractTitle(htmlContent);
        if (pageTitle.isEmpty()) pageTitle = file.getName();

        Matcher matcher = LINK_PATTERN.matcher(htmlContent);
        while (matcher.find()) {
            String url = matcher.group(1);
            String linkText = matcher.group(2);

            if (url.startsWith(GITHUB_URL_1) || url.startsWith(GITHUB_URL_2)) {
                InvalidLink meta = new InvalidLink(url, file.getParent(), file.getName(), 0, linkText, pageTitle);
                githubLinks.add(new GitHubLink(meta.bookName, pageTitle, linkText, url, sourceFileLink(meta)));
            } else {
                rawLinks.add(new RawLink(url, file.getParent(), file.getName(), linkText, pageTitle));
            }
        }
    }

    public static String extractTitle(String htmlContent) {
        Matcher matcher = TITLE_PATTERN.matcher(htmlContent);
        return matcher.find() ? matcher.group(1).trim() : "";
    }

    private static int checkLink(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            return connection.getResponseCode();
        } catch (IOException e) {
            return -1;
        }
    }

    private static String sourceFileLink(InvalidLink link) {
        String path = link.folderName.replace("en/", "");
        return path.replace(ROOT_INPUT, PORTAL_URL) + "/" + link.fileName;
    }

    private static String capitalizeEachWord(String str) {
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void createErrorCodesMDFile() {
        File directory = new File(outputFolder + RESOURCES_FOLDER);
        if (!directory.exists()) directory.mkdirs();

        StringBuilder content = new StringBuilder();
        content.append("# HTTP Error Response Codes\n\n");
        content.append("[< Back to Documentation Quality Report](../DocumentationQualityReport.md)\n\n");
        content.append("| HTTP Status Code | Description                | Detailed Description                                                |\n");
        content.append("|-----------------|----------------------------|----------------------------------------------------------------------|\n");
        content.append("| -1              | Failed                     | The request failed for unknown reason. |\n");
        content.append("| 300              | Multiple Choices           | The request has more than one possible response. The user-agent or user should choose one of them. |\n");
        content.append("| 301              | Moved Permanently          | The URL of the requested resource has been changed permanently. The new URL is provided in the response. |\n");
        content.append("| 302              | Found                      | The requested resource resides temporarily under a different URL. The user-agent may follow the redirect URL. |\n");
        content.append("| 303              | See Other                  | The server is redirecting the user-agent to a different resource, as indicated by a URI in the response. |\n");
        content.append("| 304              | Not Modified               | Indicates that the resource has not been modified since the last request. The client can use a cached version. |\n");
        content.append("| 307              | Temporary Redirect         | The resource requested has been temporarily moved to a different URI, and the user-agent should use the original URI for future requests. |\n");
        content.append("| 308              | Permanent Redirect         | The resource requested has been permanently moved to a different URI, and the user-agent should use the new URI for future requests. |\n");
        content.append("| 400              | Bad Request                | The server cannot or will not process the request due to something perceived to be a client error. |\n");
        content.append("| 401              | Unauthorized               | The client must authenticate itself to get the requested response. This status is similar to 403, but in this case, authentication is possible. |\n");
        content.append("| 403              | Forbidden                  | The server understood the request, but it refuses to authorize it. Authentication will not help and the request should not be repeated. |\n");
        content.append("| 404              | Not Found                  | The server cannot find the requested resource. In the browser, this means the URL is not recognized. |\n");
        content.append("| 405              | Method Not Allowed         | The request method is known by the server but has been disabled and cannot be used for that resource. |\n");
        content.append("| 406              | Not Acceptable             | The requested resource is capable of generating only content not acceptable according to the Accept headers sent in the request. |\n");
        content.append("| 408              | Request Timeout            | The server did not receive a complete request message within the time that it was prepared to wait. |\n");
        content.append("| 409              | Conflict                   | The request could not be completed due to a conflict with the current state of the resource. This code is used in situations where the user might be able to resolve the conflict and resubmit the request. |\n");
        content.append("| 410              | Gone                       | The requested resource is no longer available and will not be available again. This should be used when a resource has been intentionally removed and the resource should be purged. |\n");
        content.append("| 500              | Internal Server Error      | The server encountered an unexpected condition that prevented it from fulfilling the request. |\n");
        content.append("| 501              | Not Implemented            | The server does not support the functionality required to fulfill the request. This is the appropriate response when the server does not recognize the request method. |\n");
        content.append("| 502              | Bad Gateway                | The server, while acting as a gateway or proxy, received an invalid response from the upstream server. |\n");
        content.append("| 503              | Service Unavailable        | The server is not ready to handle the request. Common causes are a server that is down for maintenance or that is overloaded. |\n");
        content.append("| 504              | Gateway Timeout            | The server, while acting as a gateway or proxy, did not get a response in time from the upstream server. |\n");

        try (FileWriter writer = new FileWriter(new File(directory, ERROR_CODES_FILE))) {
            writer.write(content.toString());
            System.out.println("ErrorCodes.md created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeProductReportsByInvalidLinks(List<InvalidLink> invalidLinks) {
        Map<String, List<InvalidLink>> byProduct = new HashMap<>();
        for (InvalidLink link : invalidLinks) {
            byProduct.computeIfAbsent(link.productName, k -> new ArrayList<>()).add(link);
        }
        for (Map.Entry<String, List<InvalidLink>> entry : byProduct.entrySet()) {
            createProductMDFile(entry.getKey(), entry.getValue());
        }
    }

    public static void createProductMDFile(String productName, List<InvalidLink> invalidLinks) {
        File directory = new File(outputFolder + "Products");
        if (!directory.exists()) directory.mkdirs();

        StringBuilder content = new StringBuilder();
        content.append("# Invalid Links Report\n\n");
        content.append("## ").append(productName).append("\n\n");
        content.append("[< Back to Documentation Quality Report](../DocumentationQualityReport.md)\n\n");
        content.append("| Book | Source Page | Source Text | Broken Link | Error Code |\n");
        content.append("|------|-------------|-------------|-------------|------------|\n");

        Set<String> seenBooks = new HashSet<>();
        for (InvalidLink link : invalidLinks) {
            String bookCell = seenBooks.add(link.bookName) ? link.bookName : " ";
            String title = link.title.length() > 20 ? link.title.substring(0, 20) : link.title;
            content.append("| ").append(bookCell)
                   .append(" | [").append(link.pageTitle).append("](").append(sourceFileLink(link)).append(")")
                   .append(" | ").append(title)
                   .append(" | [Broken Link](").append(link.url).append(")")
                   .append(" | [").append(link.responseCode).append("](../Resources/ErrorCodes.md)")
                   .append(" |\n");
        }

        String fileName = productName.replace(" ", "") + ".md";
        try (FileWriter writer = new FileWriter(new File(directory, fileName))) {
            writer.write(content.toString());
            System.out.println(productName + ".md created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeMainReportPage(String reportFile, Map<String, ProductCounter> productsHashMap) {
        Set<String> productNames = new TreeSet<>(productsHashMap.keySet());

        StringBuilder content = new StringBuilder();
        content.append("# Documentation Quality Report\n\n");
        content.append("### Scoring Logic\n");
        content.append("0 broken links = 🟢 \n\n");
        content.append("1 - 5 broken links = 🟨 \n\n");
        content.append("6 or more broken links = 🟥 \n\n");
        content.append("## Products\n\n");

        for (String name : productNames) {
            ProductCounter pc = productsHashMap.get(name);
            String indicator = getIndicator(pc.getInvalidLinksCounter());
            if (pc.getInvalidLinksCounter() > 0) {
                content.append("- ").append(indicator).append(" [").append(name).append("](Products/")
                       .append(name.replace(" ", "")).append(".md)\n");
            } else {
                content.append("- ").append(indicator).append(" ").append(name).append("\n");
            }
        }

        content.append("\n### Summary\n");
        content.append("- Total Files: ").append(totalFiles).append("\n");
        content.append("- Total Broken Links: ").append(totalBrokenLinks).append("\n");
        content.append("- Total Valid Links: ").append(totalValidLinks).append("\n");

        try (FileWriter writer = new FileWriter(new File(reportFile))) {
            writer.write(content.toString());
            System.out.println("DocumentationQualityReport.md created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getIndicator(int count) {
        if (count == 0) return "🟢";
        if (count <= 5) return "🟨";
        return "🟥";
    }

    public static void writeGitHubRefReport(List<GitHubLink> githubLinks) {
        File directory = new File(outputFolder + GITHUB_REF_FOLDER);
        if (!directory.exists()) directory.mkdirs();

        // Group by book → source page (preserving insertion order for stable output)
        Map<String, Map<String, List<GitHubLink>>> byBookByPage = new LinkedHashMap<>();
        for (GitHubLink link : githubLinks) {
            byBookByPage
                .computeIfAbsent(link.bookName, k -> new LinkedHashMap<>())
                .computeIfAbsent(link.pageTitle, k -> new ArrayList<>())
                .add(link);
        }

        Set<String> uniquePages = new LinkedHashSet<>();
        for (GitHubLink link : githubLinks) uniquePages.add(link.pageTitle);

        StringBuilder content = new StringBuilder();
        content.append("# GitHub Reference Report\n\n");
        content.append("Links referencing Exabeam GitHub repositories:\n");
        content.append("- `").append(GITHUB_URL_1).append("`\n");
        content.append("- `").append(GITHUB_URL_2).append("`\n\n");
        content.append("[< Back to Documentation Quality Report](../DocumentationQualityReport.md)\n\n");
        content.append("| Book | Source Page | Source Text | GitHub Link |\n");
        content.append("|------|-------------|-------------|-------------|\n");

        for (Map.Entry<String, Map<String, List<GitHubLink>>> bookEntry : byBookByPage.entrySet()) {
            String bookName = bookEntry.getKey();
            boolean firstBook = true;
            for (Map.Entry<String, List<GitHubLink>> pageEntry : bookEntry.getValue().entrySet()) {
                String pageName = pageEntry.getKey();
                List<GitHubLink> pageLinks = pageEntry.getValue();
                boolean firstPage = true;
                for (GitHubLink link : pageLinks) {
                    String bookCell = firstBook ? bookName : " ";
                    String pageCell = firstPage
                            ? "[" + pageName + "](" + link.sourcePageUrl + ")"
                            : " ";
                    String sourceText = link.sourceText.length() > 20 ? link.sourceText.substring(0, 20) : link.sourceText;
                    content.append("| ").append(bookCell)
                           .append(" | ").append(pageCell)
                           .append(" | ").append(sourceText)
                           .append(" | [").append(link.githubUrl).append("](").append(link.githubUrl).append(")")
                           .append(" |\n");
                    firstBook = false;
                    firstPage = false;
                }
            }
        }

        content.append("\n### Summary\n");
        content.append("- Total Files Scanned: ").append(totalFiles).append("\n");
        content.append("- Total Source Pages with GitHub Links: ").append(uniquePages.size()).append("\n");
        content.append("- Total GitHub Links Found: ").append(githubLinks.size()).append("\n");

        try (FileWriter writer = new FileWriter(new File(directory, GITHUB_REF_FILE))) {
            writer.write(content.toString());
            System.out.println("GitHubRefReport.md created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showCompletionStatus(int files, int broken, int valid) {
        JOptionPane.showMessageDialog(null,
                "Link checking completed.\n" +
                        "Total files checked: " + files + "\n" +
                        "Total broken links found: " + broken + "\n" +
                        "Total valid links found: " + valid,
                "Completion Status",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static class RawLink {
        final String url;
        final String folderName;
        final String fileName;
        final String linkText;
        final String pageTitle;

        RawLink(String url, String folderName, String fileName, String linkText, String pageTitle) {
            this.url = url;
            this.folderName = folderName;
            this.fileName = fileName;
            this.linkText = linkText;
            this.pageTitle = pageTitle;
        }
    }

    private static class InvalidLink {
        String url;
        String folderName;
        String fileName;
        int responseCode;
        String title;
        String bookName;
        String productName;
        String pageTitle;

        InvalidLink(String url, String folderName, String fileName, int responseCode, String title, String pageTitle) {
            this.url = url;
            this.folderName = folderName;
            this.fileName = fileName;
            this.responseCode = responseCode;
            this.title = title;
            this.pageTitle = pageTitle;

            int enIdx = this.folderName.indexOf("en/");
            int endProductIdx = this.folderName.indexOf("/", enIdx + 3);
            this.productName = capitalizeEachWord(this.folderName.substring(enIdx + 3, endProductIdx).replace("-", " "));

            int allIdx = this.folderName.indexOf("all/");
            int versionIdx = findVersionIndex(this.folderName);
            int startBook = allIdx > -1 ? allIdx : versionIdx;
            int endBook = this.folderName.indexOf("/", startBook + 4);
            if (endBook == -1) endBook = this.folderName.length();
            this.bookName = startBook > -1
                    ? capitalizeEachWord(this.folderName.substring(startBook + 4, endBook).replace("-", " "))
                    : " ";
        }

        private static int findVersionIndex(String input) {
            Matcher matcher = Pattern.compile("\\bi\\d+/\\b").matcher(input);
            return matcher.find() ? matcher.start() : -1;
        }
    }

    private static class GitHubLink {
        final String bookName;
        final String pageTitle;
        final String sourceText;
        final String githubUrl;
        final String sourcePageUrl;

        GitHubLink(String bookName, String pageTitle, String sourceText, String githubUrl, String sourcePageUrl) {
            this.bookName = bookName;
            this.pageTitle = pageTitle;
            this.sourceText = sourceText;
            this.githubUrl = githubUrl;
            this.sourcePageUrl = sourcePageUrl;
        }
    }

    private static class ProductCounter {
        private String productName;
        private int invalidLinksCounter;

        ProductCounter(String productName, int invalidLinksCounter) {
            this.productName = productName;
            this.invalidLinksCounter = invalidLinksCounter;
        }

        String getProductName() { return productName; }
        void setProductName(String name) { this.productName = name; }
        int getInvalidLinksCounter() { return invalidLinksCounter; }
        void setInvalidLinksCounter(int count) { this.invalidLinksCounter = count; }
    }
}
