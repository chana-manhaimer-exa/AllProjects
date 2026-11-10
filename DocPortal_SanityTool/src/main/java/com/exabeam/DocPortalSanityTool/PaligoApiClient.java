package com.exabeam.DocPortalSanityTool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PaligoApiClient {
    private static final String API_URL = "https://exabeam.paligoapp.com/api/v2";
    private static final String API_KEY = "84F404FBB7414C90C091325798688E61917DDEB8";
    private static final String USERNAME = "charissa@exabeam.com";



    public static String getDocumentOwnerByFilename(String fileName) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/files?name=" + fileName))
                .header("Authorization", "Basic " + getBase64EncodedCredentials(USERNAME, API_KEY))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Parse JSON response to get the owner ID
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.body());
            JsonNode files = responseBody.get("files");
            if (files != null && files.isArray() && files.size() > 0) {
                // Assuming the first file in the list is the desired one
                String ownerId = files.get(0).get("owner_id").asText();
                return ownerId;
            } else {
                return null;
            }
        } else {
            throw new RuntimeException("Failed to get file information: " + response.body());
        }
    }
    public static String getFolderOwnerIdByFolderName(String folderName) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        return searchTopLevelFolders(client, folderName);
    }

    private static String searchTopLevelFolders(HttpClient client, String folderName) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/folders")) // Assuming the endpoint to list top-level folders
                .header("Authorization", "Basic " + getBase64EncodedCredentials(USERNAME, API_KEY))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.body());
            JsonNode folders = responseBody.get("folders");
            if (folders != null && folders.isArray()) {
                for (JsonNode folder : folders) {
                    String ownerId = searchFolderRecursively(client, folderName, folder.get("uuid").asText());
                    if (ownerId != null) {
                        return ownerId;
                    }
                }
            }
            return null;
        } else {
            throw new RuntimeException("Failed to get folder information: " + response.body());
        }
    }

    private static String searchFolderRecursively(HttpClient client, String folderName, String folderId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/folders/" + folderId ))
                .header("Authorization", "Basic " + getBase64EncodedCredentials(USERNAME, API_KEY))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.body());
            JsonNode folders = responseBody.get("children");
            if (folders != null && folders.isArray()) {
                for (JsonNode folder : folders) {
                    if (folderName.equals(folder.get("name").asText())) {
                        String permissions=getFolderContents(client,folderId);
                        return folder.get("owner_id").asText();
                    }
                    // Recursively search in subfolders
                    String ownerId = searchFolderRecursively(client, folderName, folder.get("uuid").asText());
                    if (ownerId != null) {
                        return ownerId;
                    }
                }
            }
            return null;
        } else {
            return null;
        }
    }
    private static String getFolderContents(HttpClient client, String folderUuid) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL +  "/folders/" + folderUuid + "/contents" ))
                .header("Authorization", "Basic " + getBase64EncodedCredentials(USERNAME, API_KEY))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.body());
            JsonNode folders = responseBody.get("children");
        }
        return "";
    }

    private static String getFolderPermissions(HttpClient client,String folderUuid) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/folders/" + folderUuid + "/permissions"))
                .header("Authorization", "Basic " + getBase64EncodedCredentials(USERNAME, API_KEY))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.body());
            return responseBody.toString();
        }
        return "";
    }

    private static void parseAndPrintFolderPermissions(String jsonResponse) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject responseObject = (JSONObject) parser.parse(jsonResponse);
        JSONArray permissionsArray = (JSONArray) responseObject.get("permissions");

        for (Object permission : permissionsArray) {
            JSONObject permissionObject = (JSONObject) permission;
            String role = (String) permissionObject.get("role");
            JSONObject userObject = (JSONObject) permissionObject.get("user");
            System.out.println("User ID: " + userObject.get("id"));
            System.out.println("User Name: " + userObject.get("name"));
            System.out.println("Role: " + role);
        }
    }
    public static String parseOwnerFromResponse(String jsonResponse) {
        // Assuming the JSON response has a structure like {"document": {"owner": "owner_name"}}
        // Use a JSON library like org.json or Gson to parse the JSON response
        // Here we use org.json.JSONObject for demonstration purposes
        org.json.JSONObject jsonObject = new org.json.JSONObject(jsonResponse);
        String owner = jsonObject.getJSONObject("document").getString("owner");
        return owner;
    }
    public static String getUserFullNameByOwnerId(String ownerId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/users/" + ownerId))
                .header("Authorization", "Basic " + getBase64EncodedCredentials(USERNAME, API_KEY))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Parse JSON response to get the full name
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseBody = objectMapper.readTree(response.body());
            String fullName = responseBody.get("name").asText(); // Adjust according to actual JSON response structure
            return fullName;
        } else {
            throw new RuntimeException("Failed to get user information: " + response.body());
        }
    }

    private static String getBase64EncodedCredentials(String username, String password) {
        String credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }
    public static void GetDocumentById() throws IOException {
        URL obj = new URL(API_URL + "/documents/291324");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String auth = USERNAME + ":" + API_KEY;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeaderValue = "Basic " + encodedAuth;
        con.setRequestProperty("Authorization", authHeaderValue);

        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            in.close();
            System.out.println(content.toString());
        }

    }

}
