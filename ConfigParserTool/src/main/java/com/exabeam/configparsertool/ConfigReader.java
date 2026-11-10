package com.exabeam.configparsertool;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.code.CodeBlock;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import net.steppschuh.markdowngenerator.table.Table;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.simple.*;
import org.json.simple.parser.*;


public class ConfigReader {

    private static final String logFile = "CDSTool.md";
    private static final String inputSanityFile = "InputCONF/CIM1.0/summary.json";
    private static final String inputEventsFile = "InputCONF/CIM2.0/event_builder_default.conf";
    private static final String inputUsecaseScenario = "InputCONF/CIM1.0/usecase_scenario.csv";
    private static final String inputproductCategories = "InputCONF/CIM2.0/product-categories.csv";
    private static final String inputUseCasePath = "InputCONF/CIM1.0/";
    private static final String inputParsersPath = "InputCONF/CIM2.0/";
    private static final String inputCategoriesFile = "InputCONF/CIM2.0/categories.json";
    private static final String inputCorrelationRulesFile = "InputCONF/CIM2.0/correlation_templates.json";
    private static final String inputParsersLegacyFile = "InputCONF/CIM2.0/legacyParserNames.csv";
    private static final String inputVendorProduct = "InputCONF/CIM2.0/vendor-product.json";


    private static final String outputParsersPath = "Ps/";
    private static final String outputRulesModulesPath = "RM/";
    private static final String outputCorrelationRulesPath = "CR/";

    private static final String outputDataSourcesFile = "OutputMD/Exabeam Data Sources.md";
    private static final String outputProductCategoriesFile = "OutputMD/Exabeam Product Categories.md";
    private static final String outputCorrelationRulesFile = "OutputMD/Exabeam Correlation Rules.md";

    private static final String outputDataSourcesPath = "OutputMD/DS/";
    private static final String outputUseCasesPath = "OutputMD/UseCases/";
    private static final String outputMitreMapPath = "OutputMD/";
    private static final String relativeUseCasesPath = "../../../UseCases/";
    private static final String outputUseCasesFile = "OutputMD/Exabeam Use Cases.md";
    private static final String outputMitreMapFile = "OutputMD/MitreMap.md";
    private static final String outputRulesCountFile = "OutputMetrics/RulesCount.md";
    private static final String outputRulesCSVFile = "OutputMetrics/RulesCSVFile.csv";

    private static final String outputPath = "OutputMD/";
    private static final String FILENAMESPECIALCHRSREPLACEMENTPATTERN=" |/|#";

    private static Map<String, List<DataSource>> vendorEventsMap;

    private static Map<String,EventTypeFailure>  eventTypeFailures;

    private static Map<String, String> categoriesDescriptions;
    private static Map<String, String> compromisedInsiderCat;
    private static Map<String, String> maliciousInsiderCat;
    private static Map<String, String> externalThreatsCat;
    private static Map<String, String> compromisedInsiderUseCase;
    private static Map<String, String> maliciousInsiderUseCase;
    private static Map<String, String> externalThreatsUseCase;

    private static Map<String, Set<String>> useCaseMap;
    private static Map<String, String> mitreInitialA;
    private static Map<String, String> mitreExecution;
    private static Map<String, String> mitrePersistence;
    private static Map<String, String> mitrePrivilage;
    private static Map<String, String> mitreDefense;
    private static Map<String, String> mitreCredentialAccess;
    private static Map<String, String> mitreDiscovery;
    private static Map<String, String> mitreLateralMovement;
    private static Map<String, String> mitreCollection;
    private static Map<String, String> mitreCommandControl;
    private static Map<String, String> mitreExfiltration;
    private static Map<String, String> mitreImpact;

    private static Map<String, String> productToVendorMap;
    private static Map<String, String> productToVendorMap_NextGen;

    private static Map<String,EventType> eventTypeMap;
    // Cached concatenation of all parsers_ file contents — loaded once, reused by SetVendorProductByParser
    private static String allParsersContent = null;
    private static final Map<String, String> parserFileNameCache = new HashMap<>();
    private static final Map<String, Pattern> templatePatternCache = new HashMap<>();
    private static final Map<String, String> dataSourceFileNameCache = new HashMap<>();
    private static final Map<String, String> vendorDirectoryCache = new HashMap<>();
    private static final Map<String, String> useCaseFileNameCache = new HashMap<>();
    private static Map<String, List<UseCase>> rulesMap= new HashMap<>(); //<useCase, rulesList<UseCase>>
    private static Map<String, Model> modelsMap= new HashMap<>();
    private static Map<String, CorrelationRule> correlationRuleMap = new HashMap<>();
    private static Map<String, CorrelationRule> correlationRuleByUseCaseMap = new HashMap<>();

    private static Map<String, Integer> mitreRuleCounter=new HashMap<>();
    private static Map<String, String> mitreRuleList=new HashMap<>();
    private static Map<String, Integer> vendor_product_usecase_RulesCount=new java.util.concurrent.ConcurrentHashMap<>();
    private static Map<String, String> vendor_product_usecase_RuleName=new java.util.concurrent.ConcurrentHashMap<>();

    private static JSONObject jsonAdaptationObject;
    private static Logger logger;

    public static void main(String[] args) throws IOException {

        FileHandler handler = new FileHandler(logFile, true);
        handler.setFormatter(new java.util.logging.Formatter() {
            @Override
            public String getHead(java.util.logging.Handler h) {
                return "| Date | Time (Israel) | Level | Function | Message |\n" +
                       "|------|--------------|-------|----------|---------|\n";
            }

            @Override
            public String format(java.util.logging.LogRecord record) {
                java.time.ZonedDateTime zdt = java.time.Instant.ofEpochMilli(record.getMillis())
                        .atZone(java.time.ZoneId.of("Asia/Jerusalem"));
                String date = zdt.toLocalDate().toString();
                String time = String.format("%02d:%02d:%02d", zdt.getHour(), zdt.getMinute(), zdt.getSecond());
                String level = record.getLevel().getName();
                String method = record.getSourceClassName().replaceAll(".*\\.", "") + "." + record.getSourceMethodName() + "()";
                String message = record.getMessage().replace("|", "\\|").replace("\n", " ");
                return "| " + date + " | " + time + " | " + level + " | " + method + " | " + message + " |\n";
            }
        });

        logger = Logger.getLogger("Exabeam Documentation Site");
        logger.addHandler(handler);
        logger.info("Start running CDS Tool FOR CIM2.0 ");

        long startTime = System.currentTimeMillis();
        if (!init()) {
            logger.warning("Exit with ERROR. Process not finished");
        } else {
            long elapsed = System.currentTimeMillis() - startTime;
            long minutes = elapsed / 60000;
            long seconds = (elapsed % 60000) / 1000;
            logger.info("Complete running CDS Tool - Please check OutputMD folder for created content docs.");
            logger.info(String.format("Total run time: %d min %d sec", minutes, seconds));
        }
    }

    public static boolean init() throws IOException {



        logger.info("Creating output path:"+outputPath);
        File directory = new File(outputPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        CreateSanityCheckReport();

        CreateLegacyParserNamesPages();

        LoadProductCategories();
        LoadVendorProductMap_NextGen();

        //SET MITRE GROUPS
        initializeMITREGroups();
        //LOAD USECASE SCENARIOS
        LoadUseCaseScenarios();

        // DATA SOURCES
        logger.info("Start Load Data Sources & Parsers");
        LoadDataSourcesAndParsers();

        //Categories page
        CreateCategoriesPage();

        // Load Models
        logger.info("Start Load Models");
        LoadModels();

        // USE CASES
        logger.info("Usecases & Rules");
        LoadUseCasesRules();

        // Correlation Rules
        LoadCorrelationRules();
        CreateCorrelationRulesPage();

       //Creating Content Doc Pages
       //***********************************
        logger.info("Create Data Source Pages");
        createDataSourcePages();

        logger.info("Create Rules&Models Pages");
        createRulesModelsPages();


        logger.info("Create UseCase Pages");
        createUseCasePages();

        logger.info("Create Mitre Map Page");
        CreateMitreMapPage();

        CreateRuleCountReport();

        CreateRulesDataCSVReport();

        return true;
    }

    private static void CreateSanityCheckReport() {
    }

    private static void LoadVendorProductMap_NextGen() {

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(inputVendorProduct));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productToVendorMap_NextGen=new HashMap<>();

        JSONObject jsonVendorProductObject = (JSONObject) obj;


        if(jsonVendorProductObject!=null) {


                for (String vendorName : new TreeSet<String>(jsonVendorProductObject.keySet())) {
                    JSONObject vendorObject=(JSONObject) jsonVendorProductObject.get(vendorName)  ;
                    if(vendorObject!=null) {
                        JSONArray productsArray = (JSONArray) vendorObject.get("products");

                        for (int i = 0; i < productsArray.size(); i++) {
                            String productname = ((JSONObject) productsArray.get(i)).get("product_name").toString();
                            if (!(productToVendorMap_NextGen.containsKey(productname.toLowerCase())))
                            {
                                productToVendorMap_NextGen.put(productname.toLowerCase(),vendorName);
                            }
                        }
                    }
                }
            }
        }




    private static void CreateLegacyParserNamesPages() throws IOException {
        String fieldsmappingsPath = outputPath + "ParserNamesMatrix.md";
        LoadLegacyParserNames();
        String content = GetMainParsersMatrixPage();
        Files.deleteIfExists(Paths.get(fieldsmappingsPath));
        FileWriter myWriter = new FileWriter(fieldsmappingsPath);
        myWriter.write(content);
        myWriter.close();
    }
    private static String GetMainParsersMatrixPage() {
        Link linkTo=new Link("Parser Naming Conventions","https://docs.exabeam.com/en/content/all/exabeam-security-content-cim/exabeam-parsers/parser-naming-conventions.html");
        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading(" Matrix of Old vs. New-Scale Parser Names", 1))
                        .append("\n")
                        .append("\n")
                        .append(new Text(" Parser names follow a standardized set of conventions that ensure consistency across Exabeam products. " +
                                "For more information, see " + linkTo.toString()+". If you've been using Exabeam products prior to the introduction of this parser-naming convention, consult the alphabetic tables below to find the new name for existing parsers."))
                        .append("\n")
                        .append("\n");


        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER,Table.ALIGN_CENTER)

                .addRow(new Link("A", "ParsersLegacy/" +"a_parsers.md"),new Link("B", "ParsersLegacy/" +"b_parsers.md"),new Link("C", "ParsersLegacy/" +"c_parsers.md"),new Link("D", "ParsersLegacy/" +"d_parsers.md"),new Link("E", "ParsersLegacy/" +"e_parsers.md"),new Link("F", "ParsersLegacy/" +"f_parsers.md"),new Link("G", "ParsersLegacy/" +"g_parsers.md"),new Link("H", "ParsersLegacy/" +"h_parsers.md"),new Link("I", "ParsersLegacy/" +"i_parsers.md"),new Link("J", "ParsersLegacy/" +"j_parsers.md"),new Link("K", "ParsersLegacy/" +"k_parsers.md"),new Link("L", "ParsersLegacy/" +"l_parsers.md"),"","")
                .addRow(new Link("M", "ParsersLegacy/" +"m_parsers.md"),new Link("N", "ParsersLegacy/" +"n_parsers.md"),new Link("O", "ParsersLegacy/" +"o_parsers.md"),new Link("P", "ParsersLegacy/" +"p_parsers.md"),new Link("Q", "ParsersLegacy/" +"q_parsers.md"),new Link("R", "ParsersLegacy/" +"r_parsers.md"),new Link("S", "ParsersLegacy/" +"s_parsers.md"),new Link("T", "ParsersLegacy/" +"t_parsers.md"),new Link("U", "ParsersLegacy/" +"u_parsers.md"),new Link("V", "ParsersLegacy/" +"v_parsers.md"),new Link("W", "ParsersLegacy/" +"w_parsers.md"),new Link("X", "ParsersLegacy/" +"x_parsers.md"),new Link("Y", "ParsersLegacy/" +"y_parsers.md"),new Link("Z", "ParsersLegacy/" +"z_parsers.md"));
        sb.append(tableBuilder.build());
        return sb.toString();
    }

    private static void LoadLegacyParserNames() throws IOException {

        HashMap<String, String> legacyParserNames = new HashMap<>();



        Scanner sc = new Scanner(new File(inputParsersLegacyFile));
        sc.useDelimiter("\n");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            String line=sc.next();
            String[] parserLine = line.split(",");
            if (parserLine.length>1)
            {

                if (!legacyParserNames.containsKey(parserLine[0].trim()))
                    legacyParserNames.put(parserLine[0].trim(), parserLine[1].trim());

            }

        }

        sc.close();  //closes the scanner

        // TreeMap to store values of HashMap
        TreeMap<String, String> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(legacyParserNames);

        // Display the TreeMap which is naturally sorted
        String prevKey="A";
        String content="";
        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading(" Parsers Names", 1))
                        .append("\n")
                        .append("\n");

        Table.Builder tableBuilder=new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Old Parser Name","New-Scale Parser Name");

        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            if (entry.getKey().substring(0,1).toLowerCase().equals(prevKey.toLowerCase())) {
                tableBuilder.addRow(entry.getKey(),entry.getValue());

            }
            else
            {
                content=tableBuilder.build().toString();
                CreateLegacyParserPage(prevKey+"_parsers.md",content);
                content="";
                tableBuilder = new Table.Builder()
                        .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                        .addRow("Old Parser Name","New-Scale Parser Name");

                prevKey=entry.getKey().substring(0,1).toLowerCase();

            }

        }
        content=tableBuilder.build().toString();
        CreateLegacyParserPage(prevKey+"_parsers.md",content);
    }

    private static void CreateLegacyParserPage(String fileName, String content) throws IOException {
        String fieldsmappingsPath = outputPath + "ParsersLegacy/" + fileName;
        File directory = new File(outputPath + "ParsersLegacy");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Files.deleteIfExists(Paths.get(fieldsmappingsPath));
        FileWriter myWriter = new FileWriter(fieldsmappingsPath);
        myWriter.write(content);
        myWriter.close();
    }
    private static void CreateRulesDataCSVReport() {

        StringBuilder returnSB = new StringBuilder();
       returnSB.append("Vendor, Product, Usecase, RuleName, RuleDescription, ModelName"+"\n");

        for (String name: vendor_product_usecase_RuleName.keySet()) {

            String value = vendor_product_usecase_RuleName.get(name).toString();

            returnSB.append( name.split("_")[0].trim()+","+name.split("_")[1].trim()+","+name.split("_")[2].trim()+","+name.split("_")[3].trim()+","+name.split("_")[4].trim()+","+value+"\n");
        }





        returnSB.append("\n");
        returnSB.append("\n");

        CreateRulesDataCSVFile(returnSB);

    }

    private static void CreateRulesDataCSVFile(StringBuilder sb) {
        try {
            File directory = new File(outputRulesCSVFile);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Files.deleteIfExists(Paths.get(outputRulesCSVFile ));

            FileWriter myWriter = new FileWriter(outputRulesCSVFile, false);
            myWriter.write(sb.toString());
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void CreateRuleCountReport() {

        Table.Builder tableBuilder=new Table.Builder();
        StringBuilder returnSB = new StringBuilder();
        tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                //.withRowLimit(7)
                .addRow("Vendor", "Product", "Usecase","Rules");

        for (String name: vendor_product_usecase_RulesCount.keySet()) {
            String value = vendor_product_usecase_RulesCount.get(name).toString();

            tableBuilder.addRow( name.split("_")[0].trim(),name.split("_")[1].trim(),name.split("_")[2].trim(),value);
        }



        returnSB.append(tableBuilder.build());

        returnSB.append("\n");
        returnSB.append("\n");

        CreateRulesCountFile(returnSB);



    }

    private static void CreateCorrelationRulesPage() {
        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Exabeam Prebuilt Correlation Rules", 1))
                        .append("\n")
                        .append("\n")

                        .append(
                                new Text(
                                        "The following table lists the prebuilt supported correlation rules."))
                        .append("\n")
                        .append("\n")
                        .append(getListOfCorrelationRules());

        try {
            writeToFile(outputCorrelationRulesFile, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        private static StringBuilder getListOfCorrelationRules() {
            Table.Builder tableBuilder=new Table.Builder();
            StringBuilder returnSB = new StringBuilder();
            String warning="&#x26A0; Overlaps with Advanced Analytics rule.";

            tableBuilder = new Table.Builder()
                    .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                    .addRow("Rule Name","Description","Use Case","MITRE tactics", "Severity");

            for (Map.Entry<String,CorrelationRule> entry : correlationRuleMap.entrySet())
            {
                String crName=entry.getValue().getCrName();
                if ("true".equals(entry.getValue().getOverlap_with_AA())) {
                    crName += "<br><br>" + warning;
                }

                if (!entry.getValue().getCrUsecase().isEmpty()) {
                    String linkToUseCaseURL="UseCases/uc_"+entry.getValue().getCrUsecase()+".md";
                    Link linkToUsecase = new Link(capitalizeWord(entry.getValue().getCrUsecase()), linkToUseCaseURL);
                    tableBuilder.addRow(crName, entry.getValue().getCrDescription(), linkToUsecase, entry.getValue().getMitreTactic(), entry.getValue().getCrSeverity());
                }

            }
                returnSB.append(tableBuilder.build());
           return returnSB;
    }
    public static String capitalizeWord(String str) {
        StringBuilder sb = new StringBuilder();
        for (String w : str.split("_")) {
            sb.append(w.substring(0, 1).toUpperCase()).append(w.substring(1)).append(' ');
        }
        return sb.toString().trim();
    }
    private static void LoadCorrelationRules() {

        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader(inputCorrelationRulesFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonCRObject = (JSONObject) obj;

        JSONArray crArray = (JSONArray) jsonCRObject.get("templates");



        for (int i = 0; i < crArray.size(); i++) {
            String name= ((JSONObject) crArray.get(i)).get("name").toString();
            String description= ((JSONObject) crArray.get(i)).get("description").toString();
            String usecase= ((JSONObject) crArray.get(i)).get("useCase").toString();
            String severity= ((JSONObject) crArray.get(i)).get("severity").toString();
            String query= ((JSONObject) crArray.get(i)).get("authoringConfig").toString();
            String templateId = ((JSONObject) crArray.get(i)).get("templateId").toString();
            String mitreTactic = ((JSONObject) crArray.get(i)).get("mitreTactic").toString();
            String overlap_with_AA = ((JSONObject) crArray.get(i)).get("overlap_with_AA").toString();

            if (query.contains("activity_type:")) {
                Integer startQuery=query.indexOf("activity_type:");
                Integer endQuery=query.indexOf("\\",startQuery+16);
                if (endQuery>-1 && endQuery>startQuery+17)
                    query = query.substring(startQuery+17,endQuery);
                else
                    query="";
            }
            else
                query="";

            CorrelationRule cr=new CorrelationRule(templateId,name,description,usecase,severity,query,overlap_with_AA,mitreTactic);

            correlationRuleMap.put(cr.getCrName(),cr);


            correlationRuleByUseCaseMap.put(usecase+"_"+query,cr);

        }

    }

    private static void CreateCategoriesPage() {
        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Exabeam Product Categories", 1))
                        .append("\n")
                        .append("\n")

                        .append(
                                new Text(
                                        "The following table lists the out-of-the-box supported data sources grouped by product categories."))
                        .append("\n")
                        .append("\n")
                        .append(getListOfProductsByCategories());

        try {
            writeToFile(outputProductCategoriesFile, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static StringBuilder getListOfProductsByCategories() {
        Table.Builder tableBuilder=new Table.Builder();
        StringBuilder returnSB = new StringBuilder();

        tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Product Category", "Description","Product");
        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader(inputCategoriesFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonCategoriesObject = (JSONObject) obj;

        JSONObject categoriesNode = (JSONObject) jsonCategoriesObject.get("Product Category");
        if(categoriesNode!=null) {

            JSONObject categoriesList = (JSONObject) categoriesNode.get("category");

            if (categoriesList != null) {
                String previousCatagoryName="";
                //for (Iterator categoriesIterator = categoriesList.keySet().iterator(); categoriesIterator.hasNext(); ) {
                for(String categoryName: new TreeSet<String>(categoriesList.keySet())) {
                    //String categoryName = (String) categoriesIterator.next();
                    JSONArray productsArray = (JSONArray) categoriesList.get(categoryName);

                    StringBuilder rowBuilder=new StringBuilder();
                    ArrayList<String> sortedProducts = new ArrayList<String>();
                    String catDescription="";
                    String vendor = "";

                    for (int i = 0; i < productsArray.size(); i++) {

                        vendor = "";
                        catDescription="";
                        String productName=productsArray.get(i).toString();
                       // String productName=originalProductName.replaceAll("  "," ");

                        if(categoriesDescriptions.get(categoryName+": [")!=null)
                            catDescription=categoriesDescriptions.get(categoryName+": [");

                        if(productToVendorMap!=null && productToVendorMap.get(productName)!=null)
                            vendor=productToVendorMap.get(productName);


                        String linkToProduct=new Link(productName,"DS/"+vendor.replaceAll(" ","_")+"/"+productName.replaceAll(" ","_")+"/ds_"+vendor.toLowerCase().replaceAll(" ","_")+"_"+productName.replaceAll(" ","_")+".md").toString();

                        if (!categoryName.equals(previousCatagoryName))
                        {

                            if(sortedProducts.size()>0)
                            {
                                Collections.sort(sortedProducts);
                                String productList=sortedProducts.toString().replaceAll(",","");
                                productList=productList.substring(1,productList.length()-1);
                                tableBuilder.addRow("<b>" + categoryName + "</b>", catDescription, productList);
                                sortedProducts = new ArrayList<String>();

                            }
                            else
                            {
                               sortedProducts.add(linkToProduct+"<br>");

                            }
                            previousCatagoryName=categoryName;


                        }
                        else
                            {
                                sortedProducts.add(linkToProduct+"<br>");

                            }
                        }

                    if(sortedProducts.size()>0)
                    {
                        Collections.sort(sortedProducts);
                        String productList=sortedProducts.toString().replaceAll(",","");
                        productList=productList.substring(1,productList.length()-1);
                        tableBuilder.addRow("<b>" + categoryName + "</b>", catDescription, productList);

                    }

                }
            }
        }


        returnSB.append(tableBuilder.build());

        return returnSB;


    }

   /* private static void LoadLegacyAdaptation() {

        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader(inputAdaptationLayerFile));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

         jsonAdaptationObject = (JSONObject) obj;
    }*/

    private static void CreateRulesCountFile(StringBuilder sb) {
        try {
            File directory = new File(outputRulesCountFile);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Files.deleteIfExists(Paths.get(outputRulesCountFile ));

            FileWriter myWriter = new FileWriter(outputRulesCountFile, false);
            myWriter.write(sb.toString());
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void CreateMitreMapPage() {

            CountersHolder countHolder = new CountersHolder();
            String mitreTable=getMitreMapTable(countHolder);
            StringBuilder sb =
                    new StringBuilder()
                            .append(new Heading("MITRE ATT&CK® Framework for Enterprise" , 1))
                            .append("\n")
                            .append(new Heading("MITRE Techniques: "+countHolder.getMitreCount() , 3))
                            .append("\n")
                            .append(new Heading("MITRE Sub-techniques: "+countHolder.getMitreSubCount() , 3))
                            .append("\n")
                            .append(mitreTable);

        CreateMitreMapFile(sb);
    }

    private static void CreateMitreMapFile(StringBuilder sb) {
        try {
            File directory = new File(outputMitreMapPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Files.deleteIfExists(Paths.get(outputMitreMapFile ));

            FileWriter myWriter = new FileWriter(outputMitreMapFile, false);
            myWriter.write(sb.toString());
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getMitreMapTable(CountersHolder countersHolder) {
        Table.Builder tableBuilder=new Table.Builder();
        StringBuilder returnSB = new StringBuilder();
        String mitreTPP="";


        for (String vendorEvent : vendorEventsMap.keySet()) {

            if (!vendorEvent.contains("--")) {

                mitreTPP += getMitreListByDataSources(vendorEventsMap.get(vendorEvent),countersHolder).toString();
            }
        }

        if (mitreTPP.length()>0) {

            tableBuilder = new Table.Builder()
                    .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                    //.withRowLimit(7)
                    .addRow("Initial Access", "Execution", "Persistence", "Privilege Escalation", "Defense Evasion", "Credential Access", "Discovery", "Lateral Movement", "Collection", "Command and Control", "Exfiltration", "Impact");


            tableBuilder.addRow(getMitreGroupList(mitreInitialA, mitreTPP), getMitreGroupList(mitreExecution, mitreTPP), getMitreGroupList(mitrePersistence, mitreTPP), getMitreGroupList(mitrePrivilage, mitreTPP),
                    getMitreGroupList(mitreDefense, mitreTPP), getMitreGroupList(mitreCredentialAccess, mitreTPP), getMitreGroupList(mitreDiscovery, mitreTPP), getMitreGroupList(mitreLateralMovement, mitreTPP),
                    getMitreGroupList(mitreCollection, mitreTPP), getMitreGroupList(mitreCommandControl, mitreTPP), getMitreGroupList(mitreExfiltration, mitreTPP), getMitreGroupList(mitreImpact, mitreTPP));


            returnSB.append(tableBuilder.build());

            returnSB.append("\n");
            returnSB.append("\n");

            tableBuilder = new Table.Builder()
                    .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                    //.withRowLimit(7)
                    .addRow("TTP Code","Technique: Sub-technique", "Rules");



            for (String item : mitreRuleCounter.keySet().stream().sorted().collect(Collectors.toList())) {

                if(item.contains("."))
                    countersHolder.setMitreSubCount(countersHolder.getMitreSubCount()+1);
                else
                    countersHolder.setMitreCount(countersHolder.getMitreCount()+1);
                String linkTTP = new Link(getMitreName(item), "https://attack.mitre.org/techniques/"+item.replace(".","/"))+"<br><br>";
                tableBuilder.addRow(item, linkTTP, mitreRuleCounter.get(item));
            }



            returnSB.append(tableBuilder.build());
        }
        return returnSB.toString();
    }

    private static void LoadUseCasesRules() {
        try (Stream<Path> walk = Files.walk(Paths.get(inputUseCasePath))) {
            List<String> useCasesFilesList =
                    walk.map(x -> x.toString())
                            .filter(f -> f.startsWith(inputUseCasePath + "rules_"))
                            .collect(Collectors.toList());
            List<UseCase> useCasesList = new ArrayList<>();
            for (String ruleFileName : useCasesFilesList) {
                String useCaseContent = readInputStreamFile(ruleFileName);
                useCasesList.addAll(ruleEventSplitter(useCaseContent));
            }

            setUseCasesToEventTypsMap(useCasesList); // updating the use cases to event types map
            setUseCasesToRulesMap(useCasesList);
            String sbUseCase = prepareUseCasesMainPage(useCasesList);
            writeToFile(outputUseCasesFile, sbUseCase);

            castUseCaseListToUseCaseMap(useCasesList); // writing use case to use case map
            setUseCasesToEventTypsMap(useCasesList);


        } catch (IOException e) {

            logger.warning("ERROR: "+ e.toString());

        }
    }

    private static void LoadModels() {
        try (Stream<Path> walk = Files.walk(Paths.get(inputUseCasePath))) {
            List<String> modelsFilesList =
                    walk.map(x -> x.toString())
                            .filter(f -> f.startsWith(inputUseCasePath + "models_"))
                            .collect(Collectors.toList());
            List<Model> modelsList = new ArrayList<>();
            for (String modelFileName : modelsFilesList) {
                String modelContent = readInputStreamFile(modelFileName);
                modelsList.addAll(modelsSplitter(modelContent));
                setModelsMap(modelsList);
            }
       } catch (IOException e) {
            e.printStackTrace();
             logger.warning("ERROR: "+ e.toString());
        }
    }

    private static void LoadDataSourcesAndParsers() {
        try {
            Path inputDir = Paths.get(inputEventsFile).getParent();
            if (inputDir == null) inputDir = Paths.get(".");

            List<String> eventBuilderFiles;
            try (Stream<Path> walk = Files.walk(inputDir, 1)) {
                eventBuilderFiles = walk
                        .filter(path -> !Files.isDirectory(path))
                        .filter(path -> path.getFileName().toString().startsWith("event_builder_"))
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }

            List<DataSource> vendorList = new ArrayList<>();
            for (String eventFile : eventBuilderFiles) {
                vendorList.addAll(getVendorsEvents(readInputStreamFile(eventFile)));
            }

            setDatasourcesToEventTypsMap(vendorList);
            String sbDataSource = prepareMainDataSourcesPage(vendorList);
            writeToFile(outputDataSourcesFile, sbDataSource.replaceAll("-{10,}", "-").replaceAll(" {10,}", ""));
            castVendorListToVendorMap(vendorList);

            try (Stream<Path> walk = Files.walk(Paths.get(inputParsersPath))) {
                List<String> parsersFilesList = walk.map(Path::toString)
                        .filter(f -> f.startsWith(inputParsersPath + "parsers_"))
                        .collect(Collectors.toList());
                for (String parserFileName : parsersFilesList) {
                    createParsersFiles(readInputStreamFile(parserFileName), vendorList);
                }
            }

        } catch (IOException e) {
            logger.warning("ERROR: " + e);
        }
    }

    private static void RemoveSanityFailures() {
        try {
            int countremovedRules=0;
            int countremovedModels=0;

            ObjectMapper mapper = new ObjectMapper();

            Map<String,EventTypeFailure>  eventTypeFailures=mapper.readValue(Paths.get(inputSanityFile).toFile(), new TypeReference<Map<String,EventTypeFailure>>(){});
            logger.info("Loaded");

            for (String eventTypeKey : eventTypeFailures.keySet()) {

                if(eventTypeMap.get(eventTypeKey)!=null)
                {
                    if(eventTypeFailures.get(eventTypeKey).getExceptions()!=null)
                    {
                        //for each exception for this event type remove relevant rules and parsers
                        //-----------------------------------------------------------------------
                        for(String parserExceptionKey  : eventTypeFailures.get(eventTypeKey).getExceptions().keySet())
                        {
                            for(DataSource dataSource : eventTypeMap.get(eventTypeKey).getDataSources())
                            {
                                if(dataSource.getParsers().contains(parserExceptionKey))
                                {
                                     //parser found need to check RULES
                                    //----------------------------------------

                                    for(String ruleExceptionKey : eventTypeFailures.get(eventTypeKey).getExceptions().get(parserExceptionKey).getMissing_rule_fields().keySet())
                                    {
                                        if(eventTypeMap.get(eventTypeKey).getUseCases()!=null) {
                                            for (UseCase useCaseRule : eventTypeMap.get(eventTypeKey).getUseCases()) {
                                                if (useCaseRule.getRuleId().equals(ruleExceptionKey)) {

                                                    useCaseRule.setRuleId("EXCEPTION:"+useCaseRule.getRuleId());

                                                    //rulesMap.remove(useCaseRule);
                                                    logger.info("EXCEPTION: " + ruleExceptionKey);
                                                    logger.info("RULE REMOVED BY SANITY EXCEPTION:" + ruleExceptionKey + " IN PARSER:" + parserExceptionKey + "EVENT TYPE:" + eventTypeKey);
                                                    useCaseRule.setRuleId("EXCEPTION:" + ruleExceptionKey);
                                                    countremovedRules++;
                                                }
                                            }
                                        }
                                    }
                                    //parser found need to check MODELS
                                    //----------------------------------------

                                    for(String modelExceptionKey : eventTypeFailures.get(eventTypeKey).getExceptions().get(parserExceptionKey).getMissing_model_fields().keySet())
                                    {
                                        if(eventTypeMap.get(eventTypeKey).getUseCases()!=null) {
                                            for (UseCase useCaseRule : eventTypeMap.get(eventTypeKey).getUseCases()) {


                                                if (useCaseRule.getModelId().equals(modelExceptionKey)) {
                                                    if (modelsMap.containsValue(useCaseRule)) {

                                                        useCaseRule.setModelId("EXCEPTION:"+useCaseRule.getModelId());
                                                        //modelsMap.remove(useCaseRule);

                                                        logger.info("MODEL REMOVED BY SANITY EXCEPTION:" + modelExceptionKey + " IN PARSER:" + parserExceptionKey + "EVENT TYPE:" + eventTypeKey);
                                                        useCaseRule.setModelId("EXCEPTION:" + modelExceptionKey);

                                                        countremovedModels++;
                                                    }
                                                }
                                            }
                                        }
                                    }



                                }
                            }
                        }
                    }
                }
            }
            logger.info ("TOTAL Removed Rules:" + countremovedRules);
            logger.info ("TOTAL Removed Models:" +  countremovedModels);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("ERROR: "+ ex.toString());
        }
    }

    private static void LoadProductCategories() throws FileNotFoundException {
        categoriesDescriptions= new  HashMap<>();
try {
    Scanner sc = new Scanner(new File(inputproductCategories));
    sc.useDelimiter("\n");   //sets the delimiter pattern
    while (sc.hasNext())  //returns a boolean value
    {
        String line = sc.next();

        String[] categoryLine = line.split(";");
        if (categoryLine.length > 2) {
            if (categoryLine[0].trim().length() > 2) {
                if (!categoriesDescriptions.containsKey(categoryLine[0].trim()))
                    categoriesDescriptions.put(categoryLine[0].trim(), categoryLine[2].trim());

            }

        }
    }
    sc.close();  //closes the scanner

    } catch (Exception ex) {
        ex.printStackTrace();
        logger.warning("ERROR: "+ ex.toString());
    }

    }
    private static void LoadUseCaseScenarios() throws FileNotFoundException {

        compromisedInsiderCat = new  HashMap<>();
        externalThreatsCat = new HashMap<>();
        maliciousInsiderCat = new HashMap<>();

        compromisedInsiderUseCase = new  HashMap<>();
        externalThreatsUseCase = new HashMap<>();
        maliciousInsiderUseCase = new HashMap<>();


        Scanner sc = new Scanner(new File(inputUsecaseScenario));
        sc.useDelimiter("\n");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            String line=sc.next();
            String[] usecaseLine = line.split(",");
            if (usecaseLine.length>2) {
                if (usecaseLine[0].trim().equals("Compromised Insiders")) {
                    if (!compromisedInsiderCat.containsKey(usecaseLine[2].trim()))
                        compromisedInsiderCat.put(usecaseLine[2].trim(), line);
                    if (!compromisedInsiderUseCase.containsKey(usecaseLine[1].trim()))
                        compromisedInsiderUseCase.put(usecaseLine[1].trim(), line);
                }
                if (usecaseLine[0].trim().equals("External Threats")) {
                    if (!externalThreatsCat.containsKey(usecaseLine[2].trim()))
                        externalThreatsCat.put(usecaseLine[2].trim(), line);
                    if (!externalThreatsUseCase.containsKey(usecaseLine[1].trim()))
                        externalThreatsUseCase.put(usecaseLine[1].trim(), line);
                }
                if (usecaseLine[0].trim().equals("Malicious Insiders")) {
                    if (!maliciousInsiderCat.containsKey(usecaseLine[2].trim()))
                        maliciousInsiderCat.put(usecaseLine[2].trim(), line);
                    if (!maliciousInsiderUseCase.containsKey(usecaseLine[1].trim()))
                        maliciousInsiderUseCase.put(usecaseLine[1].trim(), line);
                }
            }
        }
        sc.close();  //closes the scanner

}
    private static void initializeMITREGroups() {
        mitreInitialA= new HashMap<>();
        mitreExecution= new HashMap<>();
        mitrePersistence= new HashMap<>();
        mitrePrivilage= new HashMap<>();
        mitreDefense= new HashMap<>();
        mitreCredentialAccess= new HashMap<>();
        mitreDiscovery= new HashMap<>();
        mitreLateralMovement= new HashMap<>();
        mitreCollection= new HashMap<>();
        mitreCommandControl= new HashMap<>();
        mitreExfiltration= new HashMap<>();
        mitreImpact= new HashMap<>();

        mitreInitialA.put("T1189","Drive-by Compromise");
        mitreInitialA.put("T1190","Exploit Public Fasing Application");
        mitreInitialA.put("T1133","External Remote Services");
        mitreInitialA.put("T1200","Hardware Additions");
        mitreInitialA.put("T1566","Phishing");
        mitreInitialA.put("T1566.002","Phishing: Spearphishing Link");
        mitreInitialA.put("T1091","Replication Through Removable Media");
        mitreInitialA.put("T1195","Supply Chain Compromise");
        mitreInitialA.put("T1199","Trusted Relationship");
        mitreInitialA.put("T1078","Valid Accounts");
        mitreInitialA.put("T1078.004","Valid Accounts: Cloud Accounts");

        mitreExecution.put("T1059","Command and Scripting Interperter");
        mitreExecution.put("T1059.001","Command and Scripting Interperter: PowerShell");
        mitreExecution.put("T1203","Exploitation for Client Execution");
        mitreExecution.put("T1559","Inter-Process Communication");
        mitreExecution.put("T1106","Native API");
        mitreExecution.put("T1053","Scheduled Task/Job");
        mitreExecution.put("T1053.002","Scheduled Task/Job: At (Windows)");
        mitreExecution.put("T1053.005","Scheduled Task/Job: Scheduled Task");
        mitreExecution.put("T1129","Shared Modules");
        mitreExecution.put("T1072","Software Deployment Tools");
        mitreExecution.put("T1569","System Services");
        mitreExecution.put("T1204","User Execution");
        mitreExecution.put("T1047","Windows Management Instrumentation");
        mitreExecution.put("T1064", "Scripting");
        mitreExecution.put("T1086","Command and Scripting Interpreter: PowerShell");

        mitrePersistence.put("T1098","Account Manipulation");
        mitrePersistence.put("T1098.002","Account Manipulation: Exchange Email Delegate Permissions");

        mitrePersistence.put("T1197","BITS Jobs");
        mitrePersistence.put("T1547","Boot or Logon Autostart Execution");
        mitrePersistence.put("T1037","Boot or Logon Initialization Scripts");
        mitrePersistence.put("T1176","Browser Extensions");
        mitrePersistence.put("T1554","Compromize Client Software Binary");
        mitrePersistence.put("T1136","Create Account");
        mitrePersistence.put("T1136.001","Create Account: Create: Local Account");
        mitrePersistence.put("T1136.003","Create Account: Create: Cloud Account");
        mitrePersistence.put("T1543","Create or Modify System Process");
        mitrePersistence.put("T1543.003","Create or Modify System Process: Windows Service");
        mitrePersistence.put("T1546","Event Triggered Execution");
        mitrePersistence.put("T1133","External Remote Services");
        mitrePersistence.put("T1574","Hijack Execution Flow");
        mitrePersistence.put("T1525","Implant Container Image");
        mitrePersistence.put("T1137","Office Application Startup");
        mitrePersistence.put("T1542","Pre-OS Boot");
        mitrePersistence.put("T1053","Scheduled Task/Job");
        mitrePersistence.put("T1505","Server Software Component");
        mitrePersistence.put("T1505.003","Server Software Component: Web Shell");
        mitrePersistence.put("T1205","Traffic Signaling");
        mitrePersistence.put("T1078","Valid Accounts");
        mitrePersistence.put("T1067","Pre-OS Boot: Bootkit");

        mitrePrivilage.put("T1548","Abuse Elevation Control Mechanism");
        mitrePrivilage.put("T1548.002","Abuse Elevation Control Mechanism: Bypass User Account Control");
        mitrePrivilage.put("T1088","Abuse Elevation Control Mechanism: Bypass User Account Control");
        mitrePrivilage.put("T1134","Access Token Manipulation");
        mitrePrivilage.put("T1134.001","Access Token Manipulation: Token Impersonation/Theft");
        mitrePrivilage.put("T1547","Boot or Logon Autostart Execution");
        mitrePrivilage.put("T1037","Boot or Logon Initialization Scripts");
        mitrePrivilage.put("T1543","Create or Modify System Process");
        mitrePrivilage.put("T1546","Event Triggered Execution");
        mitrePrivilage.put("T1068","Exploitation for Privilege Escalation");
        mitrePrivilage.put("T1484","Group Policy Modification");
        mitrePrivilage.put("T1574","Hijack Execution Flow");
        mitrePrivilage.put("T1055","Process Injection");
        mitrePrivilage.put("T1055.001","Process Injection: Dynamic-link Library Injection");
        mitrePrivilage.put("T1053","Scheduled Task/Job");
        mitrePrivilage.put("T1078","Valid Accounts");
        mitrePrivilage.put("T1093","Process Injection: Process Hollowing");


        mitreDefense.put("T1548","Abuse Elevation Control Mechanism");
        mitreDefense.put("T1134","Access Token Manipulation");
        mitreDefense.put("T1197","BITS Jobs");
        mitreDefense.put("T1140","Deobfuscate/Decode Files or Information");
        mitreDefense.put("T1006","Direct Volume Access");
        mitreDefense.put("T1480","Execution Guardrails");
        mitreDefense.put("T1211","Exploitation for Defense Evasion");
        mitreDefense.put("T1222","File and Directory Permissions Modification");
        mitreDefense.put("T1222.001","File and Directory Permissions Modification: Windows File and Directory Permissions Modification");
        mitreDefense.put("T1484","Group Policy Modification");
        mitreDefense.put("T1564","Hide Artifacts");
        mitreDefense.put("T1564.004","Hide Artifacts: NTFS File Attributes");
        mitreDefense.put("T1574","Hijack Execution Flow");
        mitreDefense.put("T1574.002","Hijack Execution Flow: DLL Side-Loading");
        mitreDefense.put("T1562","Impair Defenses");
        mitreDefense.put("T1562.004","Impair Defenses: Disable or Modify System Firewall");
        mitreDefense.put("T1070","Indicator Removal on Host");
        mitreDefense.put("T1070.001","Indicator Removal on Host: Clear Windows Event Logs");
        mitreDefense.put("T1070.004","Indicator Removal on Host: File Deletion");
        mitreDefense.put("T1202","Indirect Command Execution");
        mitreDefense.put("T1036","Masquerading");
        mitreDefense.put("T1036.003","Masquerading: Rename System Utilities");
        mitreDefense.put("T1036.005","Masquerading: Match Legitimate Name or Location");
        mitreDefense.put("T1088","Abuse Elevation Control Mechanism: Bypass User Account Control");
        mitreDefense.put("T1556","Modify Authentication Process");
        mitreDefense.put("T1578","Modify Cloud Compute Infrastructure");
        mitreDefense.put("T1112","Modify Registry");
        mitreDefense.put("T1027","Obfuscated Files or Information");
        mitreDefense.put("T1027.004","Obfuscated Files or Information: Compile After Delivery");
        mitreDefense.put("T1027.005","Obfuscated Files or Information: Indicator Removal from Tools");
        mitreDefense.put("T1542","Pre-OS Boot");
        mitreDefense.put("T1055","Process Injection");
        mitreDefense.put("T1207","Rogue Domain Controller");
        mitreDefense.put("T1014","Rootkit");
        mitreDefense.put("T1218","Signed Binary Proxy Execution");
        mitreDefense.put("T1218.001","Signed Binary Proxy Execution: Compiled HTML File");
        mitreDefense.put("T1218.002","Signed Binary Proxy Execution: Control Panel");
        mitreDefense.put("T1218.003","Signed Binary Proxy Execution: CMSTP");
        mitreDefense.put("T1218.004","Signed Binary Proxy Execution: InstallUtil");
        mitreDefense.put("T1218.007","Signed Binary Proxy Execution: Msiexec");
        mitreDefense.put("T1218.009","Signed Binary Proxy Execution: Regsvcs/Regasm");
        mitreDefense.put("T1218.010","Signed Binary Proxy Execution: Regsvr32");
        mitreDefense.put("T1218.011","Signed Binary Proxy Execution: Rundll32");
        mitreDefense.put("T1216","Signed Script Proxy Execution");
        mitreDefense.put("T1553","Subvert Trust Controls");
        mitreDefense.put("T1221","Template Injection");
        mitreDefense.put("T1205","Traffic Signaling");
        mitreDefense.put("T1127","Trusted Developer Utilities Proxy Execution");
        mitreDefense.put("T1127.001","Trusted Developer Utilities Proxy Execution: MSBuild");
        mitreDefense.put("T1535","Unused/Unsupported Cloud Regions");
        mitreDefense.put("T1550","Use Alternate Authentication Material");
        mitreDefense.put("T1550.002","Use Alternate Authentication Material: Pass the Hash");
        mitreDefense.put("T1550.003","Use Alternate Authentication Material: Pass the Ticket");
        mitreDefense.put("T1550.004","Use Alternate Authentication Material: Web Session Cookie");
        mitreDefense.put("T1093","Process Injection: Process Hollowing");

        mitreDefense.put("T1078","Valid Accounts");
        mitreDefense.put("T1078.003","Valid Accounts: Local Accounts");
        mitreDefense.put("T1497","Virtualization/Sandbox Evasion");
        mitreDefense.put("T1220","XSL Script Processing");
        mitreDefense.put("T1085", "Signed Binary Proxy Execution: Rundll32");

        mitreCredentialAccess.put("T1110","Brute Force");
        mitreCredentialAccess.put("T1555","Credentials from Password Stores");
        mitreCredentialAccess.put("T1212","Exploitation for Credential Access");
        mitreCredentialAccess.put("T1187","Forced Authentication");
        mitreCredentialAccess.put("T1056","Input Capture");
        mitreCredentialAccess.put("T1557","Man-in-the-Middle");
        mitreCredentialAccess.put("T1556","Modify Authentication Process");
        mitreCredentialAccess.put("T1040","Network Sniffing");
        mitreCredentialAccess.put("T1003","OS Credential Dumping");
        mitreCredentialAccess.put("T1003.006","OS Credential Dumping: DCSync");
        mitreCredentialAccess.put("T1528","Steal Application Access Token");
        mitreCredentialAccess.put("T1558","Steal or Forge Kerberos Tickets");
        mitreCredentialAccess.put("T1558.003","Steal or Forge Kerberos Tickets: Kerberoasting");
        mitreCredentialAccess.put("T1539","Steal Web Session Cookie");
        mitreCredentialAccess.put("T1111","Two-Factor Authentication Interception");
        mitreCredentialAccess.put("T1552","Unsecured Credentials");


        mitreDiscovery.put("T1087","Account Discovery");
        mitreDiscovery.put("T1087.001","Account Discovery: Local Account");
        mitreDiscovery.put("T1087.002","Account Discovery: Domain Account");
        mitreDiscovery.put("T1010","Application Window Discovery");
        mitreDiscovery.put("T1217","Browser Bookmark Discovery");
        mitreDiscovery.put("T1538","Cloud Service Dashboard");
        mitreDiscovery.put("T1526","Cloud Service Discovery");
        mitreDiscovery.put("T1482","Domain Trust Discovery");
        mitreDiscovery.put("T1083","File and Directory Discovery");
        mitreDiscovery.put("T1046","Network Service Scanning");
        mitreDiscovery.put("T1135","Network Share Discovery");
        mitreDiscovery.put("T1040","Network Sniffing");
        mitreDiscovery.put("T1201","Password Policy Discovery");
        mitreDiscovery.put("T1120","Peripheral Device Discovery");
        mitreDiscovery.put("T1069","Permission Groups Discovery");
        mitreDiscovery.put("T1057","Process Discovery");
        mitreDiscovery.put("T1012","Query Registry");
        mitreDiscovery.put("T1018","Remote System Discovery");
        mitreDiscovery.put("T1518","Software Discovery");
        mitreDiscovery.put("T1082","System Information Discovery");
        mitreDiscovery.put("T1016","System Network Configuration Discovery");
        mitreDiscovery.put("T1049","System Network Connections Discovery");
        mitreDiscovery.put("T1033","System Owner/User Discovery");
        mitreDiscovery.put("T1007","System Service Discovery");
        mitreDiscovery.put("T1124","System Time Discovery");
        mitreDiscovery.put("T1497","Virtualization/Sandbox Evasion");


        mitreLateralMovement.put("T1210","Exploitation of Remote Services");
        mitreLateralMovement.put("T1534","Internal Spearphishing");
        mitreLateralMovement.put("T1570","Lateral Tool Transfer");
        mitreLateralMovement.put("T1563","Remote Service Session Hijacking");
        mitreLateralMovement.put("T1021","Remote Services");
        mitreLateralMovement.put("T1021.001","Remote Services: Remote Desktop Protocol");
        mitreLateralMovement.put("T1021.002","Remote Services: SMB/Windows Admin Shares");

        mitreLateralMovement.put("T1091","Replication Through Removable Media");
        mitreLateralMovement.put("T1072","Software Deployment Tools");
        mitreLateralMovement.put("T1080","Taint Shared Content");
        mitreLateralMovement.put("T1550","Use Alternate Authentication Material");

        mitreCollection.put("T1560","Archive Collected Data");
        mitreCollection.put("T1123","Audio Capture");
        mitreCollection.put("T1119","Automated Collection");
        mitreCollection.put("T1115","Clipboard Data");
        mitreCollection.put("T1530","Data from Cloud Storage Object");
        mitreCollection.put("T1213","Data from Information Repositories");
        mitreCollection.put("T1005","Data from Local System");
        mitreCollection.put("T1039","Data from Network Shared Drive");
        mitreCollection.put("T1025","Data from Removable Media");
        mitreCollection.put("T1074","Data Staged");
        mitreCollection.put("T1114","Email Collection");
        mitreCollection.put("T1114.003","Email Collection: Email Forwarding Rule");
        mitreCollection.put("T1056","Input Capture");
        mitreCollection.put("T1185","Man in the Browser");
        mitreCollection.put("T1557","Man-in-the-Middle");
        mitreCollection.put("T1113","Screen Capture");
        mitreCollection.put("T1125","Video Capture");

        mitreCommandControl.put("T1071","Application Layer Protocol");
        mitreCommandControl.put("T1071.001","Application Layer Protocol: Web Protocols");
        mitreCommandControl.put("T1071.002","Application Layer Protocol: File Transfer Protocols");
        mitreCommandControl.put("T1071.004","Application Layer Protocol: DNS");

        mitreCommandControl.put("T1092","Communication Through Removable Media");
        mitreCommandControl.put("T1132","Data Encoding");
        mitreCommandControl.put("T1132.001","Data Encoding: Standard Encoding");
        mitreCommandControl.put("T1001","Data Obfuscation");
        mitreCommandControl.put("T1568","Dynamic Resolution");
        mitreCommandControl.put("T1568.002","Dynamic Resolution: Domain Generation Algorithms");
        mitreCommandControl.put("T1573","Encrypted Channel");
        mitreCommandControl.put("T1008","Fallback Channels");
        mitreCommandControl.put("T1105","Ingress Tool Transfer");
        mitreCommandControl.put("T1104","Multi-Stage Channels");
        mitreCommandControl.put("T1095","Non-Application Layer Protocol");
        mitreCommandControl.put("T1571","Non-Standard Port");
        mitreCommandControl.put("T1572","Protocol Tunneling");
        mitreCommandControl.put("T1090","Proxy");
        mitreCommandControl.put("T1090.002","Proxy: External Proxy");
        mitreCommandControl.put("T1090.003","Proxy: Multi-hop Proxy");
        mitreCommandControl.put("T1219","Remote Access Software");
        mitreCommandControl.put("T1205","Traffic Signaling");
        mitreCommandControl.put("T1102","Web Service");
        mitreCommandControl.put("T1043","Commonly Used Port");


        mitreExfiltration.put("T1020","Automated Exfiltration");
        mitreExfiltration.put("T1030","Data Transfer Size Limits");
        mitreExfiltration.put("T1048","Exfiltration Over Alternative Protocol");
        mitreExfiltration.put("T1048.003","Exfiltration Over Alternative Protocol: Exfiltration Over Unencrypted/Obfuscated Non-C2 Protocol");
        mitreExfiltration.put("T1041","Exfiltration Over C2 Channel");
        mitreExfiltration.put("T1011","Exfiltration Over Other Network Medium");
        mitreExfiltration.put("T1052","Exfiltration Over Physical Medium");
        mitreExfiltration.put("T1052.001","Exfiltration Over Physical Medium: Exfiltration over USB");
        mitreExfiltration.put("T1567","Exfiltration Over Web Service");
        mitreExfiltration.put("T1567.002","Exfiltration Over Web Service: Exfiltration to Cloud Storage");
        mitreExfiltration.put("T1029","Scheduled Transfer");
        mitreExfiltration.put("T1537","Transfer Data to Cloud Account");

        mitreImpact.put("T1531","Account Access Removal");
        mitreImpact.put("T1485","Data Destruction");
        mitreImpact.put("T1486","Data Encrypted for Impact");
        mitreImpact.put("T1565","Data Manipulation");
        mitreImpact.put("T1491","Defacement");
        mitreImpact.put("T1561","Disk Wipe");
        mitreImpact.put("T1499","Endpoint Denial of Service");
        mitreImpact.put("T1495","Firmware Corruption");
        mitreImpact.put("T1490","Inhibit System Recovery");
        mitreImpact.put("T1498","Network Denial of Service");
        mitreImpact.put("T1496","Resource Hijacking");
        mitreImpact.put("T1489","Service Stop");
        mitreImpact.put("T1529","System Shutdown/Reboot");
    }


    private static List<DataSource> getVendorsEvents(String content) {
        return eventSplitter(content);
    }

    private static List<DataSource> eventSplitter(String content) {

        //    String patternString = "}].*?}";
        content=content.replaceAll("type ,","type,").replaceAll("type  ,","type,");
       //content=content.replaceAll("expression = \"type =.*?expression","InList\\(type,.*?expression)");

        String patternString = "InList\\(type,.*?input-message";
        patternString+= "|expression = \"type =.*?input-message";

        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(content);

        List<DataSource> vendorsEventList = new ArrayList<>();
        while (matcher.find()) {
            String tokenGroup = matcher.group();
            if(tokenGroup.contains("dell-powerprotect-str-app-authentication-success-auditrest"))
                logger.info("dell-powerprotect-str-app-authentication-success-auditrest");
            getVendorEvent(tokenGroup, content, vendorsEventList);

        }

        return vendorsEventList;
    }

    private static List<DataSource> getVendorEvent(String token, String content, List<DataSource> vendorsEventList) {

            int fromIndex=0;
            String eventTypeName="";

            eventTypeName=GetEventTypeNameFromOutputType(token,content);

            while (token.toLowerCase().indexOf("inlist(type,", fromIndex) > -1)
            {
                int inListPlace = token.toLowerCase().indexOf("inlist(type,", fromIndex);
                int closeInListPlase = token.indexOf(")", inListPlace);
                String parsersList = token.substring(inListPlace + 13, closeInListPlase)
                        .trim()
                        .replaceAll("'", "");
                Set<String> parsers = getParsersValues(parsersList);

                fromIndex = token.toLowerCase().indexOf("inlist(type,", fromIndex) + 14;


                for (String parser : parsers) {

                    Set<String> vndParsers = new HashSet<String>();
                    DataSource vndevnt = new DataSource();


                    vndevnt.setEventType(eventTypeName);

                    vndParsers.add(parser);
                    SetVendorProductByParser(parser, vndevnt);
                    //TODO: TAKE CARE OF --
                    if (vndevnt.getSource() == null)
                        vndevnt.setSource("--");
                    if (vndevnt.getVendor() == null)
                        vndevnt.setVendor("--");

                    vndevnt.setParsers(vndParsers);
                    vendorsEventList.add(vndevnt);

                }



            }
            if(token.contains("expression = \"type"))
            {
                    int parserStart=token.indexOf("'");
                    int parserEnd=token.indexOf("'",parserStart+1);
                    String parser=token.substring(parserStart+1,parserEnd);
                    Set<String> vndParsers = new HashSet<String>();
                    DataSource vndevnt = new DataSource();


                    vndevnt.setEventType(eventTypeName);
                    vndParsers.add(parser);
                    SetVendorProductByParser(parser, vndevnt);
                    //TODO: TAKE CARE OF --
                    if (vndevnt.getSource() == null)
                        vndevnt.setSource("--");
                    if (vndevnt.getVendor() == null)
                        vndevnt.setVendor("--");

                    vndevnt.setParsers(vndParsers);
                    vendorsEventList.add(vndevnt);

            }

        return vendorsEventList;
    }

    private static String GetEventTypeNameFromOutputType(String token, String content)
    {
        int tokenPlace = content.indexOf(token,0);
        //int outputTypePlace = content.indexOf("legacy-output-type = ", tokenPlace);
        int outputTypePlace = token.indexOf("legacy-output-type = ", 0);

        String eventTypeName = "";

        if (outputTypePlace > -1)
        {
           // eventTypeName = content.substring(outputTypePlace + 21, content.indexOf("\n", outputTypePlace + 22)).trim();


            eventTypeName = token.substring(outputTypePlace + 21, token.indexOf("\n", outputTypePlace + 22)).trim();
        }
        else
        {
           // outputTypePlace = content.lastIndexOf("legacy-output-type = ", content.indexOf(token));
           // eventTypeName = content.substring(outputTypePlace + 21, content.indexOf("\n", outputTypePlace + 22)).trim();
            logger.warning("legacy-output-type not found in token" + token);
        }
        eventTypeName = eventTypeName.replaceAll("\"","");
        //eventTypeName = ReplaceToLegacyName(eventTypeName);
        return eventTypeName;
    }

    private static String ReplaceToLegacyName(String eventTypeName) {

        String returnValue = eventTypeName;

            JSONObject legacyEventType = (JSONObject) jsonAdaptationObject.get(eventTypeName.replace("\"","") );
            if(legacyEventType!=null) {
                JSONArray mappings = (JSONArray) legacyEventType.get("mappings");
                logger.info(eventTypeName);
                if(mappings!=null) {
                    for (int i = 0; i < mappings.size(); i++) {
                        JSONObject mapItem = (JSONObject) mappings.get(i);
                        returnValue = mapItem.get("legacyEventType").toString();
                        return returnValue;
                    }
                }
                else
                    logger.info("No mappings exist for this event type in adaptation layer:" + eventTypeName);

            }
            else
                logger.info("EventType not found in adaptation layer:" + eventTypeName);

            //for (String key : new TreeSet<String>(rules.keySet())) {
            //    returnValue++;
            // }
            return  "\"" + returnValue + "\"";



    }

    private static void ensureParsersCacheLoaded() {
        if (allParsersContent != null) return;
        StringBuilder sb = new StringBuilder();
        try (Stream<Path> walk = Files.walk(Paths.get(inputParsersPath))) {
            List<String> parsersFilesList = walk
                    .map(Path::toString)
                    .filter(f -> f.startsWith(inputParsersPath + "parsers_"))
                    .collect(Collectors.toList());
            for (String parserFileName : parsersFilesList) {
                sb.append(readInputStreamFile(parserFileName));
            }
        } catch (IOException e) {
            logger.warning("ERROR loading parsers cache: " + e);
            throw new RuntimeException("Couldn't read parsers", e);
        }
        // Normalize once here instead of on every parser lookup
        allParsersContent = sb.toString()
                .replace("Name =  ", "Name = ")
                .replace("Name=", "Name =")
                .replace("={", "= {");
    }

    private static void SetVendorProductByParser(String parserName, DataSource vndevnt) {
        ensureParsersCacheLoaded();
        int parserNameStart = allParsersContent.indexOf("\"" + parserName + "\"\n");
        if (parserNameStart == -1)
            parserNameStart = allParsersContent.indexOf(" " + parserName + "\n");
        if (parserNameStart > -1) {
            String parserCut = getParserContentWithTemplate(allParsersContent, parserName);
            if (parserCut != null && !parserCut.isEmpty())
                SetVendorProductByParserCut(parserCut, vndevnt);
            else
                logger.info("parser not found in files: " + parserName);
        }
    }

    private static  boolean SetVendorProductByParserCut(String parserCut, DataSource vndevnt) {
        boolean ret;


        int productStart = parserCut.indexOf("Product = ");
        int vendorStart = parserCut.indexOf("Vendor = ");




        if (productStart > -1) {
            vndevnt.setSource(parserCut.substring(productStart + 9, parserCut.indexOf("\n", productStart)).trim().replace("\"", ""));
            //if(vndevnt.getSource().equals("Forcepoint Next-Gen Firewall"))
              //  logger.info("Forcepoint Next-Gen Firewall");
            if(productToVendorMap_NextGen.get(vndevnt.getSource().toLowerCase())!=null) {
                vndevnt.setVendor(productToVendorMap_NextGen.get(vndevnt.getSource().toLowerCase()).trim());
            }
            else
                {
                logger.info("vendor not found in vendor_product for product:" + vndevnt.getSource());


                if (vendorStart > -1) {
                    vndevnt.setVendor(parserCut.substring(vendorStart + 9, parserCut.indexOf("\n", vendorStart)).trim().replace("\"", ""));
                }
                else
                {
                    vndevnt.setVendor(vndevnt.getSource());
                }
            }
        }
        else
        {
            vndevnt.setSource("");
            vndevnt.setVendor("");
        }

        return true;
    }


    private static Set<String> getParsersValues(String parsersStringList) {
        Set<String> returnSet = new HashSet<>();
        if (parsersStringList.contains("xml-5140"))
            parsersStringList+="";
        for (String parser : parsersStringList.split(",")) {
            returnSet.add(parser.trim());
        }
        return returnSet;
    }

    private static String prepareMainDataSourcesPage(List<DataSource> vndList) {

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Exabeam Data Sources", 1))
                        .append("\n")
                        .append("\n")

                        .append(
                                new Text(
                                        "This table lists the out-of-the-box supported Data Sources. This list is auto-generated by Vendor/Product fields."))
                        .append("\n")
                        .append("\n")
                        .append(getListOfLinkedSources(vndList));

        return sb.toString();
    }
    private static String prepareParserContent(String content) {

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Parser Content", 4))
                        .append("\n")
                        .append(new CodeBlock(content, "Java"));

        return sb.toString();
    }
    private static StringBuilder getListOfLinkedSources(List<DataSource> vndList) {

        productToVendorMap = new HashMap<>();

        // Single pass: group unique vendor_source keys by vendor (case-insensitive)
        Map<String, TreeSet<String>> vendorToSourcesMap = new LinkedHashMap<>();
        Set<String> seenVendorsLC = new LinkedHashSet<>();
        for (DataSource item : vndList) {
            if (item.getVendor() == null || "--".equals(item.getVendor())) continue;
            String vendorLC = item.getVendor().toLowerCase().trim();
            String vendorKey = item.getVendor().trim();
            String sourceKey = vendorKey + "_" + item.getSource();
            String sourceKeyLC = vendorLC + "_" + item.getSource().toLowerCase();
            if (!seenVendorsLC.contains(vendorLC)) {
                seenVendorsLC.add(vendorLC);
                vendorToSourcesMap.put(vendorKey, new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
            }
            String canonicalVendor = vendorToSourcesMap.keySet().stream()
                    .filter(v -> v.equalsIgnoreCase(vendorKey)).findFirst().orElse(vendorKey);
            vendorToSourcesMap.get(canonicalVendor).add(sourceKey);
            productToVendorMap.putIfAbsent(item.getSource().toLowerCase(), item.getVendor());
        }

        List<String> vList = new ArrayList<>(vendorToSourcesMap.keySet());
        vList.sort(String.CASE_INSENSITIVE_ORDER);

        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("Vendor", "Product");

        for (String itemVendor : vList) {
            StringBuilder listOfProducts = new StringBuilder();
            for (String item : vendorToSourcesMap.get(itemVendor)) {
                listOfProducts.append(new Link(item.substring(item.indexOf("_") + 1), "DS/" + getVendorDirectory(item) + "/" + getDataSourceFileName(item))).append("<br>");
            }
            tableBuilder.addRow(itemVendor, listOfProducts);
        }

        StringBuilder returnSB = new StringBuilder();
        returnSB.append(tableBuilder.build());

        return returnSB;


    }

    private static String getDataSourceFileName(String dataSourceName) {
        return dataSourceFileNameCache.computeIfAbsent(dataSourceName, n ->
                "ds_" + n.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase() + ".md");
    }
    private static String getRulesModulesFileName(String dataSourceName,String useCase) {
        return "r_m_"
                + dataSourceName.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase()
                +"_"+useCase.replaceAll(" ","_")
                + ".md";
    }
    private static String getCorrelationRulesFileName(String dataSourceName,String useCase) {
        return "c_r_"
                + dataSourceName.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase()
                +"_"+useCase.replaceAll(" ","_")
                + ".md";
    }

    private static String getVendorDirectory(String dataSourceName) {
        return vendorDirectoryCache.computeIfAbsent(dataSourceName, n -> {
            String dirPath = n.substring(0, n.indexOf("_")) + "/" + n.substring(n.indexOf("_") + 1).toLowerCase();
            return dirPath.replace(" ", "_");
        });
    }
    private static String getUseCaseFileName(String useCaseName) {
        return useCaseFileNameCache.computeIfAbsent(useCaseName, n ->
                "uc_" + n.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase() + ".md");
    }

    private static void castVendorListToVendorMap(List<DataSource> vel) {
        vendorEventsMap = new HashMap<>();
        for (DataSource item : vel) {
            if (item.getSource() != null) {
                String key = item.getVendor() + "_" + item.getSource();
                vendorEventsMap.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
            }
        }
    }

    private static void createDataSourcePages() throws IOException {
        List<Exception> errors = Collections.synchronizedList(new ArrayList<>());
        vendorEventsMap.keySet().parallelStream().forEach(vendorEvent -> {
            if(!vendorEvent.contains("--")) { try {
                StringBuilder sourceEventTypePage2Builder=new StringBuilder();
                String sourceEventTypePage =
                        prepareDataSourcePage(vendorEvent, vendorEventsMap.get(vendorEvent),sourceEventTypePage2Builder);
                if (sourceEventTypePage.length() > 0) {

                    File directory = new File(outputDataSourcesPath + getVendorDirectory(vendorEvent));
                    if (!directory.exists()) {
                        boolean successfull = directory.mkdirs();
                        if (!successfull)
                            logger.warning("directory creation failed");
                    }
                    if(sourceEventTypePage2Builder.toString().length()==0) {
                        sourceEventTypePage+= "\n";
                        sourceEventTypePage+= "\n";
                        sourceEventTypePage+=  (new Heading("MITRE ATT&CK® Framework for Enterprise", 2));
                        sourceEventTypePage+= "\n" + (getDataSourceMitreTable(vendorEvent, vendorEventsMap.get(vendorEvent)));
                        Files.deleteIfExists(Paths.get(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + getDataSourceFileName(vendorEvent)));
                        FileWriter myWriter =
                                new FileWriter(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + getDataSourceFileName(vendorEvent), false);
                        myWriter.write(sourceEventTypePage);
                        myWriter.close();
                    }
                    else
                    {
                        String pathToSecondFile;
                        pathToSecondFile=outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + "2_" + getDataSourceFileName(vendorEvent);
                        String pathToFirstFile;
                        pathToFirstFile=outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" +  getDataSourceFileName(vendorEvent);

                        String firstPart=sourceEventTypePage + "\n"+ (new Link("Next Page -->>", "2_" + getDataSourceFileName(vendorEvent)));
                        String secondPart= sourceEventTypePage2Builder.toString();

                        firstPart+= "\n";
                        firstPart+= "\n";
                        firstPart+=  (new Heading("MITRE ATT&CK® Framework for Enterprise", 2));
                        firstPart+= "\n" + (getDataSourceMitreTable(vendorEvent, vendorEventsMap.get(vendorEvent)));

                        Files.deleteIfExists(Paths.get(pathToFirstFile));
                        Files.deleteIfExists(Paths.get(pathToSecondFile));
                        FileWriter myWriter =
                                new FileWriter(pathToFirstFile,false);
                        myWriter.write(firstPart);
                        myWriter.close();

                         myWriter =
                                new FileWriter(pathToSecondFile, false);


                        myWriter.write(secondPart);
                        myWriter.close();

                    }

                } else {
                    logger.warning("No DATA found for DataSource:" + vendorEvent + ". Page will not be created");
                }
            } catch (Exception e) { errors.add(e); } }
        });
        if (!errors.isEmpty()) throw new IOException(errors.get(0));
    }
    private static void createRulesModelsPages() throws IOException {
        List<Exception> errors = Collections.synchronizedList(new ArrayList<>());
        vendorEventsMap.keySet().parallelStream().forEach(vendorEvent -> {
            if (!vendorEvent.contains("--")) { try {
                List<DataSource> vndList = vendorEventsMap.get(vendorEvent);
                Set<String> setItems = new HashSet<>();
                for (DataSource item : vndList) {
                    if (eventTypeMap.get(item.getEventType()) != null
                            && eventTypeMap.get(item.getEventType()).getUseCases() != null) {
                        for (UseCase uc : eventTypeMap.get(item.getEventType()).getUseCases()) {
                            if (!setItems.contains(uc.getUseCaseName().trim()))
                                setItems.add(uc.getUseCaseName().trim());
                        }
                    }
                }
                List<String> ucList = new ArrayList<>(setItems);
                Collections.sort(ucList);
                if (ucList.isEmpty()) {
                    ucList.add("Enrichment");
                }
                for (String item : ucList) {

                    String useCase = item;
                    String sourceRulesModulesPage =
                            prepareRulesModelsPage(vendorEvent, useCase, vendorEventsMap.get(vendorEvent));
                    String sourceCorrelationRulesPage =
                            prepareCorrelationRulesPage(vendorEvent, useCase, vendorEventsMap.get(vendorEvent));
                    if (sourceRulesModulesPage.length() > 0) {
                        File directory = new File(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + outputRulesModulesPath);
                        if (!directory.exists()) {
                            boolean successfull = directory.mkdirs();
                            if (!successfull)
                                logger.warning("directory creation failed");
                        }
                        Files.deleteIfExists(Paths.get(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + outputRulesModulesPath + "/" + getRulesModulesFileName(vendorEvent, useCase)));
                        FileWriter myWriter =
                                new FileWriter(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + outputRulesModulesPath + "/" + getRulesModulesFileName(vendorEvent, useCase), false);
                        myWriter.write(sourceRulesModulesPage);
                        myWriter.close();
                    } else {
                        logger.warning("No DATA found for DataSource:" + vendorEvent + ". RulesModules will not be created");
                    }
                    if (sourceCorrelationRulesPage.length() > 0) {
                        File directory = new File(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + outputCorrelationRulesPath);
                        if (!directory.exists()) {
                            boolean successfull = directory.mkdirs();
                            if (!successfull)
                                logger.warning("directory creation failed");
                        }
                        Files.deleteIfExists(Paths.get(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + outputCorrelationRulesPath + "/" + getCorrelationRulesFileName(vendorEvent, useCase)));
                        FileWriter myWriter =
                                new FileWriter(outputDataSourcesPath + getVendorDirectory(vendorEvent) + "/" + outputCorrelationRulesPath + "/" + getCorrelationRulesFileName(vendorEvent, useCase), false);
                        myWriter.write(sourceCorrelationRulesPage);
                        myWriter.close();
                    } else {
                        logger.warning("No DATA found for DataSource:" + vendorEvent + ". Correlation Page will not be created");
                    }
                }
            } catch (Exception e) { errors.add(e); } }
        });
        if (!errors.isEmpty()) throw new IOException(errors.get(0));
    }
    private static void castUseCaseListToUseCaseMap(List<UseCase> useCasesList) {
        if (useCaseMap == null) {
            useCaseMap =  new HashMap<>();
        }
        for (UseCase item : useCasesList) {
            Set<String> set = new HashSet<>();
            for (String i : item.getUseCaseEventTypes()) {
                set.add(i);
            }
            if (useCaseMap.get(item.getUseCaseName()) != null) {
                useCaseMap.get(item.getUseCaseName()).addAll(set);
            } else {
                useCaseMap.put(item.getUseCaseName(), set);
            }
        }
    }


    private static StringBuilder getDataSourceMitreTable(String dataSource, List<DataSource> vndList) {

        CountersHolder counters=new CountersHolder();
        Table.Builder tableBuilder=new Table.Builder();
        StringBuilder returnSB = new StringBuilder();
        String mitreTPP = getMitreListByDataSources(vndList,counters).toString();
        if (mitreTPP.length()>0) {
            tableBuilder = new Table.Builder()
                    .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                    //.withRowLimit(7)
                    .addRow("Initial Access", "Execution", "Persistence", "Privilege Escalation", "Defense Evasion", "Credential Access", "Discovery", "Lateral Movement", "Collection", "Command and Control", "Exfiltration", "Impact");


            tableBuilder.addRow(getMitreGroupList(mitreInitialA, mitreTPP), getMitreGroupList(mitreExecution, mitreTPP), getMitreGroupList(mitrePersistence, mitreTPP), getMitreGroupList(mitrePrivilage, mitreTPP),
                    getMitreGroupList(mitreDefense, mitreTPP), getMitreGroupList(mitreCredentialAccess, mitreTPP), getMitreGroupList(mitreDiscovery, mitreTPP), getMitreGroupList(mitreLateralMovement, mitreTPP),
                    getMitreGroupList(mitreCollection, mitreTPP), getMitreGroupList(mitreCommandControl, mitreTPP), getMitreGroupList(mitreExfiltration, mitreTPP), getMitreGroupList(mitreImpact, mitreTPP));


            returnSB.append(tableBuilder.build());

        }
        return returnSB;
    }
    private static String getMitreGroupList(Map<String, String> mitreGroup,String mitreTPP)
    {
        String retList="";
        for(String item : mitreGroup.keySet())
        {
            if(mitreTPP.contains(item))
                retList+=new Link(mitreGroup.get(item), "https://attack.mitre.org/techniques/"+item.replace(".","/"))+"<br><br>";
        }
        return  retList;
    }
    private static String getMitreName(String MitreTTPCode)
    {
        String ret=MitreTTPCode.trim();

        if (mitreInitialA.get(MitreTTPCode)!=null)
            ret = mitreInitialA.get(MitreTTPCode);
        if (mitreExecution.get(MitreTTPCode)!=null)
            ret = mitreExecution.get(MitreTTPCode);
        if (mitrePersistence.get(MitreTTPCode)!=null)
            ret = mitrePersistence.get(MitreTTPCode);
        if (mitrePrivilage.get(MitreTTPCode)!=null)
            ret = mitrePrivilage.get(MitreTTPCode);
        if (mitreDefense.get(MitreTTPCode)!=null)
            ret = mitreDefense.get(MitreTTPCode);
        if (mitreCredentialAccess.get(MitreTTPCode)!=null)
            ret = mitreCredentialAccess.get(MitreTTPCode);
        if (mitreInitialA.get(MitreTTPCode)!=null)
            ret = mitreInitialA.get(MitreTTPCode);
        if (mitreDiscovery.get(MitreTTPCode)!=null)
            ret = mitreDiscovery.get(MitreTTPCode);
        if (mitreExfiltration.get(MitreTTPCode)!=null)
            ret = mitreExfiltration.get(MitreTTPCode);
        if (mitreLateralMovement.get(MitreTTPCode)!=null)
            ret = mitreLateralMovement.get(MitreTTPCode);
        if (mitreCollection.get(MitreTTPCode)!=null)
            ret = mitreCollection.get(MitreTTPCode);
        if (mitreCommandControl.get(MitreTTPCode)!=null)
            ret = mitreCommandControl.get(MitreTTPCode);
        if (mitreCommandControl.get(MitreTTPCode)!=null)
            ret = mitreCommandControl.get(MitreTTPCode);
        if (mitreImpact.get(MitreTTPCode)!=null)
            ret = mitreImpact.get(MitreTTPCode);
        return ret;
    }
    private static StringBuilder getUseCasesTableListByDataSource(String dataSource, List<DataSource> vndList, StringBuilder sumaryTable,String selectedUseCase,StringBuilder sourceEventTypePage2Builder) {

        String eventTypes=" ";
        String parsers=" ";
        String mitreTPP=" ";
        String content="";
        String categories="";

        int rulesCount=0;
        int modelsCount=0;
        int totalRulesCount=0;
        int totalModelsCount=0;
        CountersHolder countersHolder;
        int totalMitreCount=0;
        int totalEventTypesCount=0;
        int totalParsersCount=0;

        StringBuilder totalRulesList=new StringBuilder();
        StringBuilder totalModelsList=new StringBuilder();
        StringBuilder totalEventTypesList=new StringBuilder();
        StringBuilder totalParsersList=new StringBuilder();
        StringBuilder totalMitreList=new StringBuilder();

        //sourceEventTypePage2Builder.append("NextPage Content");

        Table.Builder tableBuilder=new Table.Builder();
        Table.Builder tableBuilder2=new Table.Builder();

        StringBuilder returnSB = new StringBuilder();


                    tableBuilder = new Table.Builder()
                            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                            //.withRowLimit(7)
                            .addRow("Use-Case",  "Activity Types/Parsers", "MITRE ATT&CK® TTP", "Content");

                   tableBuilder2 = new Table.Builder()
                .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                //.withRowLimit(7)
                .addRow("Use-Case",  "Activity Types/Parsers", "MITRE ATT&CK® TTP", "Content");

        Set<String> setItems = new HashSet<>();
        for (DataSource item : vndList) {
            if (eventTypeMap.get(item.getEventType()) != null
                    && eventTypeMap.get(item.getEventType()).getUseCases() != null) {
                for (UseCase uc : eventTypeMap.get(item.getEventType()).getUseCases()) {
                    if(!setItems.contains(uc.getUseCaseName().trim()))
                        setItems.add(uc.getUseCaseName().trim());
                }
            }
        }
        List<String> ucList = new ArrayList<>(setItems);
        Collections.sort(ucList);
        if(ucList.isEmpty()) {
            ucList.add("Enrichment");
        }
        if (vndList != null && !vndList.isEmpty())
            vndList.sort(Comparator.comparing(DataSource::getEventType));

        int rowsCounter=0;
        boolean tablebuilder2Initiated=false;
        countersHolder = new CountersHolder();

        for (String item : ucList) {
            if(selectedUseCase.length()==0 || item.equals(selectedUseCase)) {
                //eventTypes = getListOfeventTypes(vndList).toString().replaceAll("\n", "<br>");
                countersHolder = new CountersHolder();
                //parsers = getListOfParsers(vndList).toString().replaceAll("\n", "<br>");
                rulesCount = getRulesCount(item, vndList, totalRulesList);
                modelsCount = getModelsCount(item, vndList, totalModelsList);


                //categories = "<ul>" + getStringBuilderRuleCategories(item, vndList).toString().replaceAll("\n", "</li>").replaceAll("- ", "<li>") + "</li></ul>";
                mitreTPP = getStringBuilderMitre(item, vndList, countersHolder, totalMitreList).toString().replaceAll("\n", "<br>");


                if (rulesCount > 0)
                    content = "<ul><li>" + rulesCount + " Rules" + "</li></ul>";

                if (modelsCount > 0)
                    content += "<ul><li>" + modelsCount + " Models" + "</li></ul>";

                String usecase = "Enrichment";

                if (!item.equals("Enrichment"))
                    usecase = new Link(item, relativeUseCasesPath + getUseCaseFileName(item)).toString();
                String eventTypesParsers = getListOfEventsWithParsers(vndList,item, countersHolder, totalEventTypesList, totalParsersList).toString().replaceAll("\n", "<br>");

                //totalEventTypesParsers+=eventTypesParsers;
                if (rowsCounter>1 && eventTypesParsers.length()>500)
                {
                    tablebuilder2Initiated = true;
                    tableBuilder2.addRow(usecase, eventTypesParsers, mitreTPP, new Link(content, outputRulesModulesPath + getRulesModulesFileName(dataSource, item)));
                }
                else
                    {
                        rowsCounter++;
                        tableBuilder.addRow(usecase, eventTypesParsers, mitreTPP, new Link(content, outputRulesModulesPath + getRulesModulesFileName(dataSource, item)));
                    }



                //returnSB.append(new Link(item, relativeUseCasesPath + getUseCaseFileName(item))).append("\n");
            }
        }

        //TODO: Remove page if no rules and models
        //if (totalRulesCount==0 && totalModelCount==0)
        //    return returnSB;

        returnSB.append(tableBuilder.build().toString().replaceAll("(.)\\1{10,}", "$1$1$1$1"));

        if (tablebuilder2Initiated)
            sourceEventTypePage2Builder.append(tableBuilder2.build().toString().replaceAll("(.)\\1{10,}", "$1$1$1$1"));

        tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER, Table.ALIGN_CENTER)
                //.withRowLimit(7)
                .addRow("Rules","Models","MITRE ATT&CK® TTPs","Activity Types","Parsers");
        if(totalRulesList.length()>0) {
            totalRulesCount = totalRulesList.toString().split(",").length;
            if (totalRulesCount > 1)
                totalRulesCount--;
        }
        if(totalModelsList.length()>0) {
            totalModelsCount = totalModelsList.toString().split(",").length;
            if (totalModelsCount > 1)
                totalModelsCount--;
        }
        if(totalEventTypesList.length()>0) {
            totalEventTypesCount = totalEventTypesList.toString().split(",").length;
            if (totalEventTypesCount > 1)
                totalEventTypesCount--;
        }
        if(totalParsersList.length()>0) {
            totalParsersCount = totalParsersList.toString().split(",").length;
            if (totalParsersCount > 1)
                totalParsersCount--;
        }
        if(totalMitreList.length()>0) {
            totalMitreCount = totalMitreList.toString().split(",").length;
            if (totalMitreCount > 1)
                totalMitreCount--;
        }
        tableBuilder.addRow(totalRulesCount, totalModelsCount, totalMitreCount, totalEventTypesCount, totalParsersCount);
        sumaryTable.append(tableBuilder.build());


        return returnSB;
    }

    private static String prepareDataSourcePage(String dataSource, List<DataSource> dataSources ,StringBuilder sourceEventTypePage2Builder) {
        String dataSourceName;
        String vendorName;
        String ret;
        StringBuilder sumaryTable=new StringBuilder();
        vendorName=dataSource.substring(0,dataSource.indexOf("_"));
        dataSourceName=dataSource.substring(dataSource.indexOf("_")+1);

        StringBuilder useCases=getUseCasesTableListByDataSource(dataSource, dataSources, sumaryTable,"",sourceEventTypePage2Builder);

        if (useCases.toString().length()==0)
            return "";

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Vendor: " + vendorName, 1))
                        .append("\n")
                        .append(new Heading("Product: " +dataSourceName , 2))
                        .append("\n")
                        .append(sumaryTable)
                        .append("\n")
                        .append("\n")
                        .append(useCases);


       // if(sb.toString().length()>5000) {
         //   logger.info("5000");
            ret=sb.toString();


       // }
       // else
       //     ret=sb.toString();
        return ret;
    }
        private static String prepareRulesModelsPage(String dataSource,String useCase, List<DataSource> dataSources) {
        String dataSourceName;
        String vendorName;
        StringBuilder sumaryTable=new StringBuilder();
        StringBuilder sourceEventTypePage2Builder=new StringBuilder();
        vendorName=dataSource.substring(0,dataSource.indexOf("_"));
        dataSourceName=dataSource.substring(dataSource.indexOf("_")+1);
        if(dataSourceName.equals("AWS CloudTrail") && useCase.equals("Malware Detection"))
            logger.info("Debug");
        StringBuilder useCases=getUseCasesTableListByDataSource(dataSource, dataSources, sumaryTable,useCase,sourceEventTypePage2Builder);
        StringBuilder rulesModelsTable = getRulesModelesTable(dataSource, useCase, dataSources);

            if (useCases.toString().length()==0)
            return "";

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Rules by Product and UseCase" , 1))
                        .append("\n")
                        .append(new Heading("Vendor: " + vendorName, 2))
                        .append("\n")
                        .append(new Heading("Product: "  , 3))
                        .append(new Link(dataSourceName, "../" + getDataSourceFileName(dataSource)))
                        .append("\n")
                        .append(new Heading("Use-Case: ", 3))
                        .append(new Link(useCase, "../../../../UseCases/" + getUseCaseFileName(useCase)))
                        .append("\n")
                        .append("\n")
                        .append(sumaryTable)
                        .append("\n")
                        .append("\n")
                        .append(rulesModelsTable);



        return sb.toString();
    }
    private static String prepareCorrelationRulesPage(String dataSource,String useCase, List<DataSource> dataSources) {
        String dataSourceName;
        String vendorName;

        StringBuilder sourceEventTypePage2Builder=new StringBuilder();
        vendorName=dataSource.substring(0,dataSource.indexOf("_"));
        dataSourceName=dataSource.substring(dataSource.indexOf("_")+1);


        StringBuilder correlationRulesTable = getCorrelationRulesTable(dataSource, useCase, dataSources);

        if (correlationRulesTable.length()==0)
            return "";

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Correlation Rules by Product and UseCase" , 1))
                        .append("\n")
                        .append(new Heading("Vendor: " + vendorName, 2))
                        .append("\n")
                        .append(new Heading("Product: "  , 3))
                        .append(new Link(dataSourceName, "../" + getDataSourceFileName(dataSource)))
                        .append("\n")
                        .append(new Heading("Use-Case: ", 3))
                        .append(new Link(useCase, "../../../../UseCases/" + getUseCaseFileName(useCase)))
                        .append("\n")
                        .append("\n")
                        .append(correlationRulesTable);



        return sb.toString();
    }

    private static StringBuilder getRulesModelesTable(String dataSource, String useCase, List<DataSource> vndList) {
        StringBuilder returnSB = new StringBuilder();
        Table.Builder tableBuilder;
        String eventTypeList=",";
        tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)

                .addRow("Event Type", "Rules", "Models");
        for (DataSource item : vndList) {
            if (eventTypeMap.get(item.getEventType()) != null
                    && eventTypeMap.get(item.getEventType()).getUseCases() != null
                    && eventTypeMap.get(item.getEventType()).getUseCases().stream().anyMatch(x -> x.getUseCaseName().contains(useCase)))
            {
                if (!eventTypeList.contains(","+item.getEventType().trim()+",") )  {
                    eventTypeList+=item.getEventType().trim()+",";
                    String mitreWithRules = GetEventTypeMitreWithRules(item.getEventType(), useCase);
                    String modelsList = GetEventTypeModelsDesc(item.getEventType(), useCase);
                    if (mitreWithRules.length() > 0 || modelsList.length() > 0)
                        tableBuilder.addRow(item.getEventType(), mitreWithRules, modelsList);
                }
            }


        }
        returnSB.append(tableBuilder.build().toString().replaceAll("(.)\\1{10,}", "$1$1$1$1"));
        return returnSB;
    }
    private static StringBuilder getCorrelationRulesTable(String dataSource, String useCase, List<DataSource> vndList) {
        StringBuilder returnSB = new StringBuilder();
        Table.Builder tableBuilder;
        String eventTypeList=",";
        Integer crCount=0;
        tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT,Table.ALIGN_LEFT)

                .addRow("Name", "Description", "Activity Type","MITRE Tactic", "Severity", "Overlap with AA");

        for (DataSource item : vndList) {
            if (eventTypeMap.get(item.getEventType()) != null
                    && eventTypeMap.get(item.getEventType()).getUseCases() != null
                    && eventTypeMap.get(item.getEventType()).getUseCases().stream().anyMatch(x -> x.getUseCaseName().contains(useCase)))
            {
                if(correlationRuleByUseCaseMap.containsKey(useCase.replace(" ","_").toLowerCase()+"_"+item.getEventType()))
                {
                    CorrelationRule correlationRule = correlationRuleByUseCaseMap.get(useCase.replace(" ","_").toLowerCase()+"_"+item.getEventType());
                    tableBuilder.addRow(correlationRule.getCrName(),correlationRule.getCrDescription(),item.getEventType(),correlationRule.getMitreTactic(),correlationRule.getCrSeverity(),correlationRule.getOverlap_with_AA());
                    crCount++;
                }
            }


        }
        if (crCount==0)
            returnSB.setLength(0);
        else
            returnSB.append(tableBuilder.build().toString().replaceAll("(.)\\1{10,}", "$1$1$1$1"));

        return returnSB;
    }

    private static String GetEventTypeMitreWithRules(String eventType,String useCase) {

        if (!rulesMap.containsKey(useCase))
            return "";
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        String ruleList="";


            for (UseCase ucItem:ucList){
                if(ucItem.getUseCaseEventTypes().contains(eventType)) {
                    if(!ruleList.contains(ucItem.getRuleId()) && !ucItem.getRuleId().contains("EXCEPTON:")) {

                        String mitreList= ucItem.getRuleMitreLabels();
                        Set<String> tmpset= new HashSet<>();
                        if(mitreList.length()>0)  {
                            for (String mitreCode : mitreList.replaceAll("\"", "").split(",")) {
                                tmpset.add(mitreCode.trim());
                            }
                        }

                    StringBuilder mitreNames=new StringBuilder();
                    List<String> vList = new ArrayList<>(tmpset);
                    Collections.sort(vList);
                    for (String setItem : vList) {

                        //returnSB.append(new Link(setItem,"https://attack.mitre.org/techniques/"+setItem));
                        mitreNames.append("<b>"+setItem +" - " + getMitreName(setItem)+"</b>");
                        setItem="<b>"+setItem +" - " + getMitreName(setItem)+"</b>";



                       // if(ucItem.getRuleId().contains("A-AL-DhU-A"))
                       //     logger.info("A-AL-DhU-A");

                        if (ruleList.contains(setItem))
                        {

                            int placeOf = ruleList.indexOf(setItem);
                            String half = ruleList.substring(0,placeOf+setItem.length());
                            String secondHalf = ruleList.substring(placeOf+setItem.length());
                            ruleList=half+"<br> ↳ <b>" + ucItem.getRuleId()+"</b>: "+ucItem.getRuleName();
                            if (secondHalf.length()>4 && secondHalf.substring(0,4).contains("<br>"))
                                ruleList+=secondHalf;
                            else
                                ruleList+="<br><br>"+secondHalf;

                        }
                        else {
                            if(ruleList.length()>0)
                                ruleList += "<br><br>";
                            ruleList +=  setItem + "<br> ↳ <b>" + ucItem.getRuleId() + "</b>: " + ucItem.getRuleName();
                        }
                    }
                    }
                }
            }



        return ruleList;
    }

    private static String GetEventTypeModelsDesc(String eventType,String useCase) {


        if (!rulesMap.containsKey(useCase))
            return "";
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        String modelsList="";


        for (UseCase ucItem:ucList){
            if(ucItem.getUseCaseEventTypes().contains(eventType)) {
                if (modelsMap.get(ucItem.getModelId()) != null && !modelsList.contains(ucItem.getModelId())  && !ucItem.getModelId().contains("EXCEPTON:"))
                {
                    if(modelsList.length()>0)
                        modelsList+="<br>";
                    modelsList +=  " • <b>"+ ucItem.getModelId()+"</b>: " + modelsMap.get(ucItem.getModelId()).getModelTemplate();

                }
                else
                {
                    logger.info("Model id not found:"+ucItem.getModelId());
                }
            }
        }


        return modelsList;
    }

    private static StringBuilder getListOfeventTypes(List<DataSource> vndList) {
        StringBuilder returnSB = new StringBuilder();
        Set<String> vList = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (DataSource item : vndList) {
            if(item.getEventType()!=null)
                vList.add(item.getEventType());
        }
        List<String> eventList = new ArrayList<>(vList);

        Collections.sort(eventList);

        if(!eventList.isEmpty())
           returnSB.append(new UnorderedList<>(eventList)).append("\n");
        if (returnSB.toString().contains("UnorderedListItem"))
            logger.info("UnorderedListItem");
        return returnSB;
    }

    private static StringBuilder getListOfParsers(List<DataSource> vndList) {
        StringBuilder returnSB = new StringBuilder();
        Set<String> vList = new HashSet<>();
        //String parsersPath="";
        for (DataSource item : vndList) {
            if(item.getParsers()!=null) {
                //parsersPath = getOutputParsersPath(item.getVendor(),item.getSource());
                for (String parser : item.getParsers()) {
                    vList.add(parser);
                }
            }

        }
        List<String> parsersList = new ArrayList<>(vList);
        Collections.sort(parsersList);
        for (String item : parsersList) {
            returnSB.append("* ");
            returnSB.append(new Link(item, outputParsersPath + getParserContentFileName(item))).append("\n");
        }
        // returnSB.append(new UnorderedList<>(parsersList)).append("\n");

        return returnSB;
    }
    private static StringBuilder getListOfEventsWithParsers(List<DataSource> vndList, String useCase, CountersHolder countersHolder, StringBuilder eventTypesList, StringBuilder parsersList) {
        StringBuilder returnSB = new StringBuilder();

        for (DataSource item : vndList)
        {
            String parsers = "";
            if(item.getEventType().trim().length()>0
                        && eventTypeMap.get(item.getEventType()).getUseCases() != null
                        &&  eventTypeMap.get(item.getEventType()).getUseCases().stream().anyMatch(x -> x.getUseCaseName().contains(useCase)) )

            {
                for (String parser : item.getParsers()) {

                    String parserLink = new Link(parser, outputParsersPath + getParserContentFileName(parser)).toString() + "\n";

                    if (returnSB.indexOf(" " + item.getEventType() + "\n") == -1) {
                        countersHolder.setEventTypesCount(countersHolder.getEventTypesCount() + 1);
                        if (eventTypesList.indexOf(item.getEventType()) == -1)
                            eventTypesList.append(",").append(item.getEventType());
                        if (returnSB.length() > 0)
                            returnSB.append("\n");
                        returnSB.append(" ").append(item.getEventType()).append("\n").append(" ↳").append(parserLink);
                        // count this first parser for the event type
                        countersHolder.setParsersCount(countersHolder.getParsersCount() + 1);
                        if (parsersList.indexOf(parser.trim()) == -1)
                            parsersList.append(",").append(parser.trim());
                    } else {
                        if (!parsers.contains(parser.trim())) {
                            returnSB.append(" ↳").append(parserLink);
                            countersHolder.setParsersCount(countersHolder.getParsersCount() + 1);
                            if (parsersList.indexOf(parser.trim()) == -1)
                                parsersList.append(",").append(parser.trim());
                        }
                    }
                    parsers += "," + parser.trim();
                }

            }
        }
        return returnSB;
    }
    private static String getParserFileName(String parserName) {
        return "parser_"
                + parserName.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase()
                + ".md";
    }

    private static StringBuilder getListOfUseCases(List<DataSource> vndList) {
        StringBuilder returnSB = new StringBuilder();
        Set<String> setItems = new HashSet<>();
        for (DataSource item : vndList) {
            if (eventTypeMap.get(item.getEventType()) != null
                    && eventTypeMap.get(item.getEventType()).getUseCases() != null) {
                for (UseCase uc : eventTypeMap.get(item.getEventType()).getUseCases()) {
                    setItems.add(uc.getUseCaseName());
                }
            }
        }
        List<String> ucList = new ArrayList<>(setItems);
        Collections.sort(ucList);
        for (String item : ucList) {
            returnSB.append("* ");
            returnSB.append(new Link(item, relativeUseCasesPath + getUseCaseFileName(item))).append("\n");
        }
        return returnSB;
    }

    private static StringBuilder getListOfDataSources(Set<String> vndList) {
        StringBuilder returnSB = new StringBuilder();
        Set<String> vSet = new HashSet<>();
        for (String item : vndList) {
            if (eventTypeMap.get(item) != null && eventTypeMap.get(item).getDataSources() != null) {
                for (DataSource ds : eventTypeMap.get(item).getDataSources()) {
                    vSet.add(ds.getSource()+"_"+ds.getVendor());
                }
            }
        }
        List<String> vList = new ArrayList<>(vSet);
        Collections.sort(vList);
        for (String setItem : vList) {
            if(!setItem.contains("--")) {
                //returnSB.append("* ");
               //TODO: TAKE CARE OF TEMPLATES
                // returnSB.append(new Link(setItem.substring(0, setItem.indexOf("_")), "../DataSources/" + getDataSourceFileName(setItem)));
                //returnSB.append("\n");
                //"Vendor: " +setItem.substring(0,setItem.indexOf("_"))+ "  Product: "+
            }
        }
        return returnSB;
    }
    private static StringBuilder getListOfTablesByDataSources(String useCase, Set<String> vndList) {

        String categories=" ";
        String eventTypes=" ";
        String parsers=" ";
        String mitreTPP=" ";
        String content="";

        int rulesCount=0;
        int modelsCount=0;

        StringBuilder emptySB=new StringBuilder();



        StringBuilder returnSB = new StringBuilder();
        Set<String> vSet = new HashSet<>();
        for (String item : vndList) {
            if (eventTypeMap.get(item) != null && eventTypeMap.get(item).getDataSources() != null) {
                for (DataSource ds : eventTypeMap.get(item).getDataSources()) {
                    vSet.add(ds.getVendor()+"_"+ds.getSource());
                }
            }
        }
        List<String> vList = new ArrayList<>(vSet);
        Collections.sort(vList);

        String CurrentVendorName="";
        String PreviousVendorName="";
        Table.Builder tableBuilder=new Table.Builder();

        for (String setItem : vList) {
            if(!setItem.contains("--")) {
                CurrentVendorName=setItem.substring(0, setItem.indexOf("_")).trim();
                if(!PreviousVendorName.equals(CurrentVendorName) && PreviousVendorName.length()>0)
                {
                    returnSB.append(tableBuilder.build());
                    returnSB.append("\n");

                }
                if(!PreviousVendorName.equals(CurrentVendorName)) {
                    returnSB.append(new Heading("Vendor: " + CurrentVendorName, 3));
                    returnSB.append("\n");
                    tableBuilder = new Table.Builder()
                            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                            //.withRowLimit(7)
                            .addRow("Product",   "MITRE ATT&CK® TTP", "Content");


                }
                PreviousVendorName=CurrentVendorName;




                if( vendorEventsMap.get(setItem)!=null) {

                   // eventTypes = "<ul>"+getListOfeventTypes(vendorEventsMap.get(setItem)).toString().replaceAll("\n", "</li>").replaceAll("- ","<li>")+"</li></ul>";
                    //parsers    = getListOfParsers(vendorEventsMap.get(setItem)).toString().replaceAll("\n", "<br>");
                    rulesCount=getRulesCount(useCase,vendorEventsMap.get(setItem),emptySB);
                    modelsCount=getModelsCount(useCase, vendorEventsMap.get(setItem),emptySB);
                    //categories = "<ul>"+getStringBuilderRuleCategories(useCase, vendorEventsMap.get(setItem)).toString().replaceAll("\n", "</li>").replaceAll("- ","<li>")+"</li></ul>";
                    CountersHolder countersHolder=new CountersHolder();
                    mitreTPP=getStringBuilderMitre(useCase,vendorEventsMap.get(setItem),countersHolder,emptySB).toString().replaceAll("\n", "<br>");

                }
                else
                {
                    logger.info("setItem: " + setItem);
                }

                if (rulesCount>0)
                    content="<ul><li>"+rulesCount+" Rules" + "</li></ul>";
                if(modelsCount>0)
                  content+= "<ul><li>" + modelsCount+" Models"+ "</li></ul>";

                tableBuilder.addRow(new Link(setItem.substring(setItem.indexOf("_")+1), "../DS/" +getVendorDirectory(setItem) +"/"+ getDataSourceFileName(setItem)), mitreTPP,new Link(content, "../DS/" +getVendorDirectory(setItem) +"/"+ outputRulesModulesPath + getRulesModulesFileName(setItem,useCase)));



            }
        }
        returnSB.append(tableBuilder.build());

        return returnSB;
    }
    private static StringBuilder getStringBuilderventTypes(Set<String> vndList) {
        StringBuilder returnSB = new StringBuilder();
        List<String> vList = new ArrayList<>(vndList);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList));

        return returnSB;
    }
   /* private static StringBuilder getStringBuilderRuleCategories(Set<String> vndList) {
        StringBuilder returnSB = new StringBuilder();
        List<String> vList = new ArrayList<>(vndList);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList)).append("\n");

        return returnSB;
    }*/
    private static StringBuilder getStringBuilderRules(String useCase) {
        StringBuilder returnSB = new StringBuilder();
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (UseCase item:ucList){
            tmpset.add(item.getRuleId()+" : "+item.getRuleCategory());
        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList)).append("\n");
        return returnSB;
    }

    private static StringBuilder getStringBuilderRuleCategories(String useCase, List<DataSource> vndList) {
        StringBuilder returnSB = new StringBuilder();
        if (!rulesMap.containsKey(useCase))
            return returnSB;
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (DataSource item : vndList) {
            for (UseCase ucItem:ucList) {
                if (ucItem.getUseCaseEventTypes().contains(item.getEventType()))
                    tmpset.add(ucItem.getRuleCategory());
            }
        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList));
        return returnSB;
    }
    private static StringBuilder getStringBuilderRuleCategories(String useCase) {
        StringBuilder returnSB = new StringBuilder();
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (UseCase item:ucList){
            tmpset.add(item.getRuleCategory());
        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList));
        return returnSB;
    }
    private static StringBuilder getStringBuilderModels(String useCase) {
        StringBuilder returnSB = new StringBuilder();
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (UseCase item:ucList){
            if (modelsMap.get(item.getModelId()) != null) {
                tmpset.add(item.getModelId() + " : " + modelsMap.get(item.getModelId()).getModelTemplate());
            }else {
                tmpset.add(item.getModelId() + " : ");
            }
        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList)).append("\n");
        return returnSB;
    }
    private static int getRulesCount(String useCase, List<DataSource> vndList, StringBuilder totalRulesList) {
        int count=0;
        if (!rulesMap.containsKey(useCase))
            return count;
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> seenRules = new HashSet<>();
        for (DataSource item : vndList) {
            for (UseCase ucItem : ucList) {
                if (ucItem.getUseCaseEventTypes().contains(item.getEventType())) {
                    if (seenRules.add(ucItem.getRuleId()) && !ucItem.getRuleId().contains("EXCEPTON:")) {
                        if (totalRulesList.indexOf(ucItem.getRuleId()) == -1)
                            totalRulesList.append(",").append(ucItem.getRuleId());
                        String counterKey = item.getVendor() + "_" + item.getSource() + "_" + useCase;
                        String ruleDetailsKey = item.getVendor() + "_" + item.getSource() + "_" + useCase + "_" + ucItem.getRuleId() + "_" + ucItem.getModelId();
                        if (!vendor_product_usecase_RuleName.containsKey(ruleDetailsKey))
                            vendor_product_usecase_RuleName.put(ruleDetailsKey, ucItem.getRuleName());
                        vendor_product_usecase_RulesCount.merge(counterKey, 1, Integer::sum);
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private static int getModelsCount(String useCase, List<DataSource> vndList, StringBuilder totalModelsList) {
        int count=0;
        if (!rulesMap.containsKey(useCase))
            return count;
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> seenModels = new HashSet<>();
        for (DataSource item : vndList) {
            for (UseCase ucItem : ucList) {
                if (ucItem.getUseCaseEventTypes().contains(item.getEventType())) {
                    if (modelsMap.get(ucItem.getModelId()) != null
                            && seenModels.add(ucItem.getModelId())
                            && !ucItem.getModelId().contains("EXCEPTON:")
                            && !ucItem.getModelId().equals("FACT")) {
                        if (totalModelsList.indexOf(ucItem.getModelId()) == -1)
                            totalModelsList.append(",").append(ucItem.getModelId());
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static StringBuilder getStringBuilderMitre(String useCase) {
        StringBuilder returnSB = new StringBuilder();
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (UseCase item:ucList){
            String mitreList=item.getRuleMitreLabels();
            if(mitreList.length()>0)  {
                for (String mitreCode : mitreList.replaceAll("\"", "").split(",")) {
                    tmpset.add(mitreCode.trim());
                }
            }

        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        for (String setItem : vList) {

                //returnSB.append(new Link(setItem,"https://attack.mitre.org/techniques/"+setItem));
                returnSB.append(setItem +" - " + getMitreName(setItem)+"\n");

        }
        //returnSB.append(new UnorderedList<>(vList)).append("\n");
        return returnSB;
    }
    private static StringBuilder getStringBuilderMitre(String useCase,List<DataSource> vndList,CountersHolder countersHolder,StringBuilder totalMitreList) {
        StringBuilder returnSB = new StringBuilder();
        if (!rulesMap.containsKey(useCase))
            return returnSB;
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (DataSource item : vndList) {
            for (UseCase ucItem:ucList) {
                if (ucItem.getUseCaseEventTypes().contains(item.getEventType())) {
                    String mitreList = ucItem.getRuleMitreLabels();
                    if (mitreList.length() > 0) {
                        for (String mitreCode : mitreList.replaceAll("\"", "").split(",")) {
                            tmpset.add(mitreCode.trim());
                        }
                    }
                }
            }
        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        for (String setItem : vList) {

            //returnSB.append(new Link(setItem,"https://attack.mitre.org/techniques/"+setItem));
            if(returnSB.indexOf(setItem +" - " + getMitreName(setItem)+"\n")==-1) {
                countersHolder.setMitreCount(countersHolder.getMitreCount()+1);
                if(totalMitreList.indexOf(setItem)==-1)
                    totalMitreList.append(","+setItem);
            }
            returnSB.append(setItem +" - " + getMitreName(setItem)+"\n");
            //returnSB.append(setItem +"\n");

        }
        //returnSB.append(new UnorderedList<>(vList)).append("\n");
        return returnSB;
    }
    private static StringBuilder getMitreListByDataSources(List<DataSource> vndList, CountersHolder countersHolder) {
        StringBuilder returnSB = new StringBuilder();
        Set<String> tmpset= new HashSet<>();

        for (String useCase: useCaseMap.keySet()) {
            List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
            for (DataSource item : vndList) {
                for (UseCase ucItem:ucList) {
                    if (ucItem.getUseCaseEventTypes().contains(item.getEventType())) {
                        String mitreList = ucItem.getRuleMitreLabels();


                        if (mitreList.length() > 0) {
                            for (String mitreCode : mitreList.replaceAll("\"", "").split(",")) {

                                mitreCode=mitreCode.trim();

                                tmpset.add(mitreCode);

                                if(mitreRuleCounter.containsKey(mitreCode)) {
                                    if (!mitreRuleList.get(mitreCode).contains(ucItem.getRuleId())) {
                                        mitreRuleCounter.put(mitreCode, mitreRuleCounter.get(mitreCode).intValue() + 1);
                                        mitreRuleList.put(mitreCode,mitreRuleList.get(mitreCode)+","+ucItem.getRuleId());
                                    }
                                }
                                else {
                                    //if(mitreCode.contains("."))
                                    //    countersHolder.setMitreSubCount(countersHolder.getMitreSubCount()+1);
                                    //else
                                    //    countersHolder.setMitreCount(countersHolder.getMitreCount()+1);

                                    mitreRuleCounter.put(mitreCode, 1);
                                    mitreRuleList.put(mitreCode,mitreRuleList.get(mitreCode)+","+ucItem.getRuleId());
                                }
                            }
                        }
                    }
                }
            }
        }

        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList,new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareToIgnoreCase(o2);
                    }
                });
        for (String setItem : vList) {

            //returnSB.append(new Link(setItem,"https://attack.mitre.org/techniques/"+setItem));
            returnSB.append(setItem+"\n");

        }
        //returnSB.append(new UnorderedList<>(vList)).append("\n");
        return returnSB;
    }
    private static StringBuilder getStringBuilderPicassoLabels(String useCase) {
        StringBuilder returnSB = new StringBuilder();
        List<UseCase> ucList = new ArrayList<>(rulesMap.get(useCase));
        Set<String> tmpset= new HashSet<>();
        for (UseCase item:ucList){
                if(item.getRulePicassoLabels()!=null && item.getRulePicassoLabels().length()>0)
                    tmpset.add(CastPicassoLabels(item.getRulePicassoLabels()));

        }
        List<String> vList = new ArrayList<>(tmpset);
        Collections.sort(vList);
        returnSB.append(new UnorderedList<>(vList)).append("\n");
        return returnSB;
    }

    private static String CastPicassoLabels(String picassoLabels)    {
        String activityList=new String();
        for (String item : picassoLabels.replaceAll("\"", "").split(",")) {
            if(item.length()>0)
                activityList+="* "+ item +"\n";
        }

        return activityList;
    }
    private static String getEventTypeFileName(String eventTypeName) {
        return "eventtype_"
                + eventTypeName.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase()
                + ".md";
    }

    private static List<UseCase> ruleEventSplitter(String content) {

        String patternString ="";
        patternString +=      "([A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[0-9]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30} \\{\n  RuleName.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}\\{\n  RuleName.*?scenario =.*?}";

        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}\\{.*?\n\n";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}\\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}\\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}\\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}\\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[0-9]{1,30}\\{.*?scenario =.*?}";
        patternString +=      "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}\\{.*?scenario =.*?})";
        //patternString +=      "^(?!Aggregation$).*$";

        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(content);
        List<UseCase> vendorsEventList = new ArrayList<>();
        while (matcher.find()) {

            String tokenGroup = matcher.group();
            if (tokenGroup.substring(0,5).equals("Rules") || tokenGroup.substring(0,11).equals("Aggregation"))
                tokenGroup=tokenGroup.substring(tokenGroup.indexOf("{")+1).trim();

            String picassoUseCases;
            if (tokenGroup.contains("scenario = [") && tokenGroup.contains("RuleCategory = \"") ) // valid token group
            {
                if (!tokenGroup.contains("Score = \"0.0") && !tokenGroup.contains("Deprecated = \"TRUE")) {
                    picassoUseCases = tokenGroup.substring(tokenGroup.indexOf("scenario = [") + 12, tokenGroup.indexOf("]", tokenGroup.indexOf("scenario = [") + 12)).trim();
                    //for each picasso label create usecase event type
                    //-------------------------------------------------

                    for (String item : picassoUseCases.replaceAll("\"", "").split(",")) {
                        if (item.length() > 0) {
                            UseCase vEvnt = new UseCase();
                            vEvnt.setModelId("");
                            vEvnt.setRuleMitreLabels("");

                            String useCaseName = item.trim();
                            if (maliciousInsiderCat.containsKey(item.trim()))
                                useCaseName = maliciousInsiderCat.get(item.trim()).split(",")[1].trim();
                            if (externalThreatsCat.containsKey(item.trim()))
                                useCaseName = externalThreatsCat.get(item.trim()).split(",")[1].trim();
                            if (compromisedInsiderCat.containsKey(item.trim()))
                                useCaseName = compromisedInsiderCat.get(item.trim()).split(",")[1].trim();
                            vEvnt.setUseCaseName(useCaseName);

                            vEvnt.setUseCaseEventTypes(getUseCaseEventTypeList(tokenGroup));

                            int ruleNameLocation = tokenGroup.indexOf("RuleName = \"");
                            int ruleIDEndPlace = tokenGroup.lastIndexOf("{", ruleNameLocation);
                            int ruleIDStartPlace = tokenGroup.lastIndexOf("\n", ruleIDEndPlace);
                            if (ruleIDStartPlace == -1)
                                ruleIDStartPlace = 0;
                            String ruleID;
                            if (ruleIDStartPlace > -1 && ruleIDEndPlace > -1 && ruleIDEndPlace > ruleIDStartPlace)
                                ruleID = tokenGroup.substring(ruleIDStartPlace, ruleIDEndPlace).trim();
                            else
                                ruleID = "";

                            vEvnt.setRuleId(ruleID);

                            vEvnt.setRuleName(tokenGroup.substring(ruleNameLocation + 12, tokenGroup.indexOf("\"", ruleNameLocation + 12)).trim());
                            vEvnt.setRuleCategory(tokenGroup.substring(tokenGroup.indexOf("RuleCategory = \"") + 16, tokenGroup.indexOf("\"", tokenGroup.indexOf("RuleCategory = \"") + 16)).trim());


                            if (tokenGroup.contains("Model = \"")) {
                                String modelIDName = tokenGroup.substring(tokenGroup.indexOf("Model = \"") + 9, tokenGroup.indexOf("\"", tokenGroup.indexOf("Model = \"") + 9)).trim();
                                vEvnt.setModelId(modelIDName);
                            }
                            else
                                vEvnt.setModelId("UNDEFINED");
                            if (tokenGroup.contains("mitre = [")) {
                                vEvnt.setRuleMitreLabels(tokenGroup.substring(tokenGroup.indexOf("mitre = [") + 9, tokenGroup.indexOf("]", tokenGroup.indexOf("mitre = [") + 9)));
                            }
                            vEvnt.setRulePicassoLabels(picassoUseCases);
                            vendorsEventList.add(vEvnt);
                        }
                    }
                }
                else
                    logger.info("disabled rule:"+tokenGroup);
            }
            else
                logger.warning("not valid toke rule group:" + tokenGroup);


        }
        return vendorsEventList;
    }

    private static List<Model> modelsSplitter(String content) {

        String patternString ="([A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?HistogramEventTypes";
        patternString+=        "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?HistogramEventTypes";
        patternString+=       "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?HistogramEventTypes" ;
        patternString+=       "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?HistogramEventTypes" ;
        patternString+=       "|[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30}-[A-Z0-9a-z]{1,30} \\{.*?HistogramEventTypes";
        patternString+=       "|[A-Z0-9a-z]{1,30} \\{\n  ModelTemplate.*?HistogramEventTypes)";

        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL| Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(content);

        List<Model> modelsList = new ArrayList<>();
        int count=0;
        while (matcher.find()) {
            count++;
            String tokenGroup = matcher.group();
            Model model = new Model();

            model.setModelId(tokenGroup.substring(0, tokenGroup.indexOf(" {")).trim());
            model.setModelTemplate(tokenGroup.substring(tokenGroup.indexOf("ModelTemplate = \"")+17, tokenGroup.indexOf("\"", tokenGroup.indexOf("ModelTemplate = \"")+17)).trim());

            modelsList.add(model);
        }
        logger.info("Models found total:" + count);
        return modelsList;
    }
    private static String prepareUseCasesMainPage(List<UseCase> vndList) {

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Exabeam Supported Use Cases", 1))
                        .append("\n")
                        .append("\n")

                        .append(getRuleEventsListOfLinkedSources(vndList));

        return sb.toString();
    }

    private static List<String> getUseCaseEventTypeList(String content) {
        List<String> RT = new ArrayList<>();
        int startRuleEventTypes=content.indexOf("RuleEventTypes = [");
        int endRuleEventTypes=content.indexOf("]",startRuleEventTypes);
        if(endRuleEventTypes>-1 && startRuleEventTypes>-1 && startRuleEventTypes + 18<endRuleEventTypes) {
            String eventTypesString =
                    content.substring(startRuleEventTypes + 18, endRuleEventTypes);
                    content.substring(startRuleEventTypes + 18, endRuleEventTypes);

            for (String item : eventTypesString.replaceAll("\n| |,", "").split("\"")) {
                if (!item.trim().equals("")) {
                    RT.add(item);
                }
            }
        }
        else
            logger.info("RuleEventTypes not found in: " + content.substring(0, Math.min(100, content.length())));
        return RT;
    }

    private static StringBuilder getRuleEventsListOfLinkedSources(List<UseCase> vndList) {
        StringBuilder returnSB = new StringBuilder();
        StringBuilder firstColumn=new StringBuilder();
        StringBuilder secondColumn=new StringBuilder();
        StringBuilder thirdColumn=new StringBuilder();
        StringBuilder forthColumn=new StringBuilder();

        List<String> vList = new ArrayList<>();
        for (UseCase item : vndList) {

            if (item !=null  && !vList.contains(item.getUseCaseName())) {
                vList.add(item.getUseCaseName());
            }


        }
        Collections.sort(vList);
        Table.Builder tableBuilder;



        for (String item : vList) {


                AddUseCaseByLookup(item,  firstColumn,secondColumn,thirdColumn,forthColumn);

        }
        tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT);
        if(forthColumn.length()>0) {
            tableBuilder.addRow("Compromised Insiders", "Malicious Insiders", "External Threats", " ");
            tableBuilder.addRow(firstColumn, secondColumn, thirdColumn, forthColumn);
        }
        else {

            tableBuilder.addRow("Compromised Insiders", "Malicious Insiders", "External Threats");
            tableBuilder.addRow(firstColumn,secondColumn,thirdColumn);
        }


        return returnSB.append(tableBuilder.build());
    }

    private static void AddUseCaseByLookup(String item,  StringBuilder firstColumn, StringBuilder secondColumn, StringBuilder thirdColumn, StringBuilder forthColumn) {

        Link usecaseLink;
        String useCaseName=item;

        if (compromisedInsiderUseCase.containsKey(item.trim())) {
           // useCaseName=compromisedInsiderCat.get(item.trim()).split(",")[1];
            usecaseLink = new Link(useCaseName, "UseCases/" + getUseCaseFileName(useCaseName));
            firstColumn.append(usecaseLink + "<br>");
        }
        if (maliciousInsiderUseCase.containsKey(item.trim())) {
           // useCaseName=maliciousInsiderCat.get(item.trim()).split(",")[1];
            usecaseLink = new Link(useCaseName, "UseCases/" + getUseCaseFileName(useCaseName));
            secondColumn.append(usecaseLink + "<br>");
        }

        if (externalThreatsUseCase.containsKey(item.trim())) {
           // useCaseName=externalThreatsCat.get(item.trim()).split(",")[1];
            usecaseLink = new Link(useCaseName, "UseCases/" + getUseCaseFileName(useCaseName));
            thirdColumn.append(usecaseLink + "<br>");
        }


        if (!compromisedInsiderUseCase.containsKey(item.trim()) && !maliciousInsiderUseCase.containsKey(item.trim()) && !externalThreatsUseCase.containsKey(item.trim())) {

            usecaseLink = new Link(item, "UseCases/" + getUseCaseFileName(item));
            forthColumn.append(usecaseLink + "<br>");
        }
    }

    private static void createUseCasePages() throws IOException {
        for (String useCase : useCaseMap.keySet()) {
            String useCaseEventTypePage = prepareUseCasePage(useCase, useCaseMap.get(useCase));

            File directory = new File(outputUseCasesPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Files.deleteIfExists(Paths.get(outputUseCasesPath + getUseCaseFileName(useCase)));
            FileWriter myWriter = new FileWriter(outputUseCasesPath + getUseCaseFileName(useCase), false);
            myWriter.write(useCaseEventTypePage);
            myWriter.close();
        }
    }

    private static String prepareUseCasePage(String useCase, Set<String> dataSources) {



        StringBuilder sb =

                new StringBuilder()
                        .append("\n")
                        .append(new Heading("Use Case: " + useCase, 1))
                        .append("\n")
                        .append("\n")
                        .append(getListOfTablesByDataSources(useCase, dataSources));

       return sb.toString();


    }

    private static void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileName))) {
            myWriter.write(content);
        }
    }

    private static String readInputStreamFile(String srcFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(srcFile)));
    }

    private static void setDatasourcesToEventTypsMap(List<DataSource> vendorList) {
        eventTypeMap = new HashMap<>();
        for (DataSource ds : vendorList) {

            List<DataSource> tmpDs = new ArrayList<>();
            EventType eventType = new EventType();

            if (eventTypeMap.get(ds.getEventType()) == null) {

                eventType.setEventTypeName(ds.getEventType());

                tmpDs.add(ds);
                eventType.setDataSources(tmpDs);
                eventTypeMap.put(ds.getEventType(), eventType);
            } else {
                tmpDs.add(ds);
                tmpDs.addAll(eventTypeMap.get(ds.getEventType()).getDataSources());
                eventTypeMap.get(ds.getEventType()).setDataSources(tmpDs);
            }

        }
    }

    private static void setUseCasesToEventTypsMap(List<UseCase> useCasesList) {
        List<UseCase> tmpUc = new ArrayList<>();
        for (UseCase uc : useCasesList) {
            if (uc!=null && uc.getUseCaseEventTypes()!=null) {
                for (String et : uc.getUseCaseEventTypes()) {
                    if (eventTypeMap.get(et) == null) {
                        tmpUc = new ArrayList<>();
                        tmpUc.add(uc);
                        EventType eventType = new EventType();
                        eventType.setUseCases(tmpUc);
                        eventType.setEventTypeName(et);
                        eventTypeMap.put(et, eventType);
                    } else if (eventTypeMap.get(et).getUseCases() == null) {
                        tmpUc = new ArrayList<>();
                        tmpUc.add(uc);
                        eventTypeMap.get(et).setUseCases(tmpUc);
                    } else {

                        tmpUc = new ArrayList<>();
                        if (!eventTypeMap.get(et).getUseCases().stream()
                                .anyMatch(x -> x.getUseCaseName().contains(uc.getUseCaseName()))) {
                            tmpUc.add(uc);
                            tmpUc.addAll(eventTypeMap.get(et).getUseCases());
                            eventTypeMap.get(et).setUseCases(tmpUc);
                        }
                    }
                }
            }
        }
    }

    private static void setModelsMap(List<Model> modelsList) {
        for (Model model : modelsList) {

            modelsMap.put(model.getModelId(), model);
        }
    }
    private static void setUseCasesToRulesMap(List<UseCase> useCasesList) {
        List<UseCase> tmpUc = new ArrayList<>();
        for (UseCase uc : useCasesList) {
            if (rulesMap.get(uc.getUseCaseName()) == null) {
                tmpUc = new ArrayList<>();
                tmpUc.add(uc);
                rulesMap.put(uc.getUseCaseName(), tmpUc);
            } else if (rulesMap.get(uc.getUseCaseName()).isEmpty()) {
                tmpUc = new ArrayList<>();
                tmpUc.add(uc);
                rulesMap.put(uc.getUseCaseName(), tmpUc);
            } else {
                tmpUc = new ArrayList<>();
                tmpUc.add(uc);
                tmpUc.addAll(rulesMap.get(uc.getUseCaseName()));
                rulesMap.put(uc.getUseCaseName(), tmpUc);
            }
        }
    }
    private static void createParsersFiles(String parserFileContent, List<DataSource> dsList)
            throws IOException {
        for (DataSource item : dsList) {
            if (!item.getVendor().contains("--")) {

                if (item.getParsers() != null) {
                    for (String parser : item.getParsers()) {


                        String content = getParserContentWithTemplate(parserFileContent, parser);

                        if (content != null) {


                            content = prepareParserContent(content);

                            File directory = new File(getOutputParsersPath(item.getVendor(), item.getSource()));
                            if (!directory.exists()) {
                                directory.mkdirs();
                            }
                            Files.deleteIfExists(Paths.get(getOutputParsersPath(item.getVendor(), item.getSource()) + getParserContentFileName(parser)));

                            writeToFile(getOutputParsersPath(item.getVendor(), item.getSource()) + getParserContentFileName(parser), content);

                        }


                    }
                } else
                    logger.warning("NO PARSERS EXIST FOR THIS VENDOR: " + item.getVendor());
            }
        }
    }
    private static String getOutputParsersPath(String vendor,String source)
    {
        return outputDataSourcesPath+vendor.replaceAll(" ","_")+"/"+source.toLowerCase().replaceAll(" ","_")+"/"+outputParsersPath+"/";
    }


    private static String getParserContentWithTemplate(String parserFileContent, String parserName) {
            String returnParserContent = GetParserMainCut(parserFileContent, parserName);
            if (returnParserContent == null)
                return null;

            returnParserContent += "\n" + GetTemplateParserContent(parserFileContent, parserName, returnParserContent);

            return "{\n" + returnParserContent + "\n}";
    }

    private static String GetParserMainCut(String parserFileContent, String parserName)
    {
        int startingParserContent = parserFileContent.indexOf("Name = " + parserName.trim()+"\n");
        if (startingParserContent ==-1)
            startingParserContent=parserFileContent.indexOf("Name = \"" + parserName.trim()+"\"\n");
        if (startingParserContent == -1) {
            //check template parser
            startingParserContent = parserFileContent.indexOf(parserName + " = {" );
            if (startingParserContent == -1) {

                return null;
            }

        }


        int endingParserContent1 = parserFileContent.indexOf("}\n", startingParserContent);
        int endingParserContent2 = parserFileContent.indexOf("},\n", startingParserContent);
        int endingParserContent3 = parserFileContent.indexOf("(\"]\\n\" +\"  }\\n\" + \"},\"", startingParserContent);
        int endingParser=-1;

        if (endingParserContent1 == -1 && endingParserContent2 == -1 && endingParserContent3==-1) {
            logger.warning("Parser content not found!!!" + parserName);
            return null;
        }
        if(endingParserContent3>-1)
            endingParser=endingParserContent3;
        if(endingParserContent2>-1 && (endingParserContent2<endingParserContent3 || endingParserContent3==-1))
            endingParser=endingParserContent2;
        if(endingParserContent1>-1 && (endingParserContent1<endingParserContent2 || endingParserContent2==-1))
            endingParser=endingParserContent1;


        String returnParserContent =
                parserFileContent.substring(startingParserContent, endingParser);

        return  returnParserContent;

    }

    private static String GetTemplateParserContent(String parserFileContent, String parserName, String content) {
        if (content.contains("Templates."))
        {
            //GetTemplate
            int startTemplateName = content.indexOf("Templates.")+10;
            int endTemplateName = content.indexOf(".",startTemplateName);
            String templateName="";

            if (endTemplateName<startTemplateName) {
                endTemplateName = content.indexOf("}",startTemplateName);

            }
            templateName = content.substring(startTemplateName,endTemplateName);

            if(parserFileContent.contains(templateName+" = {") || parserFileContent.contains(templateName+" {") || parserFileContent.contains(templateName+"{"))
            {

                int startTemplateContent=parserFileContent.indexOf(templateName+" = {");
                if (startTemplateContent==-1)
                    startTemplateContent=parserFileContent.indexOf(templateName+" {");
                if (startTemplateContent==-1)
                    startTemplateContent=parserFileContent.indexOf(templateName+"{");
                int endTemplateContent=GetEndTemplatePLace(parserFileContent,startTemplateContent);


                return parserFileContent.substring(startTemplateContent, endTemplateContent);

            }
            else
                logger.info(templateName);

        }
        else
        {
            if(!content.contains("Fields")) {
                int templatenamePlaceStart = parserFileContent.lastIndexOf("${",  parserFileContent.indexOf(content));
                int templatenamePlaceEnd = parserFileContent.indexOf("}", templatenamePlaceStart);
                int afterdotName = parserFileContent.indexOf(".", templatenamePlaceStart);
                if (afterdotName == -1 || afterdotName > templatenamePlaceEnd)
                    return "";
                String templateName = parserFileContent.substring(afterdotName + 1, templatenamePlaceEnd);

                int startTemplateContent = parserFileContent.indexOf(templateName);
                int endTemplateContent=GetEndTemplatePLace(parserFileContent,startTemplateContent);

                String templateCut="";
                if(startTemplateContent>-1 && endTemplateContent>-1)
                     templateCut = parserFileContent.substring(startTemplateContent, endTemplateContent);
               //add template from other parsers files
                templateCut += getTemplateByTemplateName(templateName);
                return templateCut;
            }
        }
        return "";
    }
    public static String getTemplateByTemplateName(String templateName) {
        ensureParsersCacheLoaded();
        String template = findTemplate(allParsersContent, templateName);
        return template != null ? template : "";
    }

    /**
     * Helper method to find a template within a string content.
     *
     * @param content      The string content of a file.
     * @param templateName The name of the template to find.
     * @return The template block as a string, or null if not found.
     */
    private static String findTemplate(String content, String templateName) {
        Pattern pattern = templatePatternCache.computeIfAbsent(templateName, n ->
                Pattern.compile("\\b" + Pattern.quote(n) + "\\b\\s*=\\s*\\{"));
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            int templateStartIndex = matcher.start();
            int braceStartIndex = content.indexOf('{', templateStartIndex);
            if (braceStartIndex == -1) {
                return null; // Should not happen if regex matches, but for safety
            }

            int braceCount = 1;
            int currentIndex = braceStartIndex + 1;

            while (currentIndex < content.length() && braceCount > 0) {
                char c = content.charAt(currentIndex);
                if (c == '{') {
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;
                }
                currentIndex++;
            }

            if (braceCount == 0) {
                return content.substring(templateStartIndex, currentIndex);
            }
        }

        return null; // Template not found in this content
    }
    private static int GetEndTemplatePLace(String parserFileContent, int startTemplateContent)
    {
        int endTemplateContent=-1;
        int endTemplateContent1=parserFileContent.indexOf("]\n}",startTemplateContent);
        int endTemplateContent2=parserFileContent.indexOf("}\n}",startTemplateContent);
        int endTemplateContent3=parserFileContent.indexOf("]\n  }",startTemplateContent);

        if (endTemplateContent1>-1)
            endTemplateContent=endTemplateContent1;
        if(endTemplateContent2>-1 && (endTemplateContent2<endTemplateContent1 | endTemplateContent1==-1))
            endTemplateContent=endTemplateContent2;
        if (endTemplateContent3>-1 && (endTemplateContent3<endTemplateContent | endTemplateContent==-1))
            endTemplateContent=endTemplateContent3;
        return endTemplateContent;
    }

    private static String getParserContentFileName(String parserName) {
        return parserFileNameCache.computeIfAbsent(parserName, n ->
                "pC_" + n.replaceAll(FILENAMESPECIALCHRSREPLACEMENTPATTERN, "_").toLowerCase().replace("-", "") + ".md");
    }
}
