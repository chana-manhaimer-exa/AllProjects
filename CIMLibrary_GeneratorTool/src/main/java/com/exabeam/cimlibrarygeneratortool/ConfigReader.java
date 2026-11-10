package com.exabeam.cimlibrarygeneratortool;

import com.fasterxml.jackson.core.type.TypeReference;
import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.code.CodeBlock;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import net.steppschuh.markdowngenerator.table.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Scanner;

import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

import java.util.logging.FileHandler;
import java.util.logging.Logger;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;




public class ConfigReader {

    private static String logFile = "CIMLibraryTool.log";
    private static String outputPath ="OutputMD/";

    private static Logger logger;

    public static void main(String[] args) throws IOException {

        FileHandler handler = new FileHandler(logFile, true);

        logger = Logger.getLogger("Exabeam Documentation Site");
        logger.addHandler(handler);
        logger.info("Start running CIM Library Tool");

        if (!init()) {
            logger.warning("Exit with ERROR. Process not finished");
        } else
            logger.info("Complete running CIM Library Tool - Please check OutputMD folder for created content docs.");
    }

    public static boolean init() throws IOException {


        logger.info("Creating output path:" + outputPath);
        File directory = new File(outputPath);
        if (!directory.exists()) {
            directory.mkdir();
        }



        CreateUniversalPage();
        CreateActivityTypesPage();
        CreateSubjectsPage();
        CreateExtensionsPage();
        CreateFieldDatatypePage();

        return true;
    }

    private static void CreateActivityTypesPage() throws IOException {

        String activityTypesPath = outputPath + "/ActivityTypes/ActivityType_Interface.md";
        String content = LoadActivityTypes();
        File directory = new File(activityTypesPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Files.deleteIfExists(Paths.get(activityTypesPath));

        FileWriter myWriter = null;

        myWriter = new FileWriter(activityTypesPath);
        myWriter.write(content);
        myWriter.close();


    }
    private static void CreateExtensionsPage() throws IOException {

        String extensionsPath = outputPath + "/Extensions/Extension_Interface.md";
        String content = LoadExtensions();
        File directory = new File(extensionsPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Files.deleteIfExists(Paths.get(extensionsPath));

        FileWriter myWriter = null;

        myWriter = new FileWriter(extensionsPath);
        myWriter.write(content);
        myWriter.close();


    }
    private static void CreateSubjectsPage() throws IOException {

        String subjectsPath = outputPath + "/Subjects/Subject_Interface.md";
        String content = LoadSubjects();
        File directory = new File(subjectsPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Files.deleteIfExists(Paths.get(subjectsPath));

        FileWriter myWriter = null;

        myWriter = new FileWriter(subjectsPath);
        myWriter.write(content);
        myWriter.close();


    }

    private static String LoadActivityTypes() {

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading(" Activity Type Interface", 1))
                        .append("\n")
                        .append("\n")
                        .append(new Heading(" Description", 3))
                        .append("\n")
                        .append("\n")
                        .append(new Text("This secondary interface defines the activity type element. It describes the specific fields required for a given activity when it's performed on the subject. This interface is also minimalist by design.\n" +
                                "\n"))

                        .append("\n");

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader( "Input/CIM.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject activityTypes = (JSONObject)jsonObject.get("EventTypes");



            for(String key: new TreeSet<String>(activityTypes.keySet()))
            {

                if (key.contains(":success")) {

                    StringBuilder sbActivity =  new StringBuilder();

                    String description ="";
                    String subject="";
                    String activity="";
                    String activity_type="";
                    String pretty_name="";
                    //String legacy_event_name="";

                    JSONObject activityTypeObj = (JSONObject) activityTypes.get(key);
                    JSONObject activityTypeObjFailed = (JSONObject) activityTypes.get(key.replace("success","fail"));

                    JSONArray jaFailLegacy = new JSONArray();
                    JSONArray jaSuccessLegacy = new JSONArray();
                     if(activityTypeObj!=null && activityTypeObj.containsKey("legacy_event_name"))
                         jaSuccessLegacy=(JSONArray) activityTypeObj.get("legacy_event_name");
                    if(activityTypeObjFailed!=null && activityTypeObjFailed.containsKey("legacy_event_name"))
                        jaFailLegacy=(JSONArray) activityTypeObjFailed.get("legacy_event_name");

                    description = activityTypeObj.get("description").toString();
                    subject = activityTypeObj.get("subject").toString();
                    activity = activityTypeObj.get("activity").toString();
                    activity_type = activityTypeObj.get("activity_type").toString();
                    pretty_name = activityTypeObj.get("pretty_name").toString();
                    //legacy_event_name = activityTypeObj.get("legacy_event_name").toString();


                    JSONObject fieldsObject=(JSONObject) activityTypeObj.get("fields") ;
                    Table.Builder tableBuilderSuccess = new Table.Builder()
                            .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT)

                            .addRow("Field","Core","Detection","Informational");
                    int rowsCounter=0;
                    int rowsCounterF=0;

                    for (Iterator fieldsIterator = fieldsObject.keySet().iterator();fieldsIterator.hasNext();) {
                        String fieldkey = (String) fieldsIterator.next();
                        JSONObject fieldObject = (JSONObject) fieldsObject.get(fieldkey);
                        String core = "";
                        String detection = "";
                        String informational = "";
                        if (!(fieldObject ==null)) {
                            core = getcheckmark(fieldObject.get("core").toString());
                            detection = getcheckmark(fieldObject.get("detection").toString());
                            informational = getcheckmark(fieldObject.get("informational").toString());
                        }

                        tableBuilderSuccess.addRow(fieldkey, core, detection,informational);
                        rowsCounter++;
                    }
                    Table.Builder tableBuilderF = new Table.Builder()
                            .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT)
                            .addRow("Field","Core","Detection","Informational");

                    if(activityTypeObjFailed!=null) {
                        JSONObject fieldsObjectF=(JSONObject) activityTypeObjFailed.get("fields") ;


                        for (Iterator fieldsIterator = fieldsObjectF.keySet().iterator();fieldsIterator.hasNext();) {
                            String fieldkey = (String) fieldsIterator.next();
                            JSONObject fieldObject = (JSONObject) fieldsObjectF.get(fieldkey);
                            String core = "";
                            String detection = "";
                            String informational = "";
                            if (!(fieldObject ==null)) {
                                core = getcheckmark(fieldObject.get("core").toString());
                                detection = getcheckmark(fieldObject.get("detection").toString());
                                informational = getcheckmark(fieldObject.get("informational").toString());
                            }

                            tableBuilderF.addRow(fieldkey, core, detection,informational);
                            rowsCounterF++;
                        }


                    }


                    key=key.substring(0,key.indexOf(":"));
                    String filename=key.trim().replaceAll(" ", "_") + ".md";



                     sbActivity.append(new Heading(filename.substring(0,filename.indexOf(".md")), 1))
                                    .append("\n")
                                    .append("\n")
                                    .append(new Heading("Description", 2))
                                    .append("\n")
                                    .append(description)
                                    .append("\n")
                                    .append("\n");

                    sbActivity.append(new Heading("Parameters", 2))
                              .append("\n");
                    Table.Builder tableBuilderParameters = new Table.Builder()
                            .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                            .addRow("Parameter","Value")
                            .addRow("Subject",subject)
                            .addRow("Activity",activity)
                            .addRow("Activity Type",activity_type)
                            .addRow("Pretty Name",pretty_name);


                     sbActivity.append(tableBuilderParameters.build())
                                .append("\n\n");





                    StringBuilder failedLegacyList=new StringBuilder();
                    StringBuilder successLegacyList=new StringBuilder();

                    for (int i=0; i<jaSuccessLegacy.size(); i++) {
                        successLegacyList.append(jaSuccessLegacy.get(i)+"<br>");
                    }
                    for (int i=0; i<jaFailLegacy.size(); i++) {
                        failedLegacyList.append(jaFailLegacy.get(i)+"<br>");
                    }

                    if(failedLegacyList.length()>0 || successLegacyList.length()>0)
                    {
                        sbActivity.append(new Heading("Legacy Names", 2))
                                .append("\n");
                        Table.Builder tableBuilderLegacy = new Table.Builder()
                                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                                .addRow("Success","Fail");

                        tableBuilderLegacy.addRow(successLegacyList.toString(), failedLegacyList.toString());

                        sbActivity.append(tableBuilderLegacy.build())
                                .append("\n");
                    }
                    sbActivity.append("\n");
                    sbActivity.append(new Heading("Fields", 2))
                            .append("\n\n")
                            .append("The possible fields for this activity type will vary depending on whether the activity was a "+new Link("success","#"+key+"success")+" or a "+new Link("fail","#"+key+"fail")+".\n")
                            .append("\n")
                            .append("\n")
                             .append(new Heading(key+":success", 2))
                             .append("\n\n");

                     if (rowsCounter>0)
                        sbActivity.append(tableBuilderSuccess.build());
                     else
                         sbActivity.append("There are no fields for this activity type.\n");


                    sbActivity.append("\n");
                    sbActivity.append("\n");
                    if(activityTypeObjFailed!=null) {
                        sbActivity.append(new Heading(key + ":fail", 2))
                                .append("\n\n");
                        if (rowsCounterF > 0)
                            sbActivity.append(tableBuilderF.build());
                        else
                            sbActivity.append("There are no fields for this activity type.\n");
                    }
                    else
                        sbActivity.append("A failure activity is not currently supported for this activity-type.");

                    CreateActivityTypeFile(filename,sbActivity.toString());
                    sb.append(" * ");
                    sb.append(new Link(key, filename));
                    sb.append("\n");
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void CreateActivityTypeFile(String filename, String filecontent) throws IOException {

        String filePath = outputPath + "/ActivityTypes/"+filename;

        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Files.deleteIfExists(Paths.get(filePath));

        FileWriter myWriter = null;

        myWriter = new FileWriter(filePath);


        myWriter.write(filecontent);
        myWriter.close();
    }
    private static void CreateExtensionFile(String filename, String filecontent) throws IOException {

        String filePath = outputPath + "/Extensions/"+filename;

        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Files.deleteIfExists(Paths.get(filePath));

        FileWriter myWriter = null;

        myWriter = new FileWriter(filePath);
        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading(filename.substring(0,filename.indexOf(".md")).replace("_"," "), 1))
                        .append("\n")
                        .append("\n")
                        .append(filecontent);


        myWriter.write(sb.toString());
        myWriter.close();
    }
    private static String LoadExtensions() {

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading("Extension Interface", 1))
                        .append("\n")
                        .append("\n")
                        .append(new Heading("Description", 3))
                        .append("\n")
                        .append("\n")
                        .append(new Text("This interface defines any additional context elements that help describe the activity. The extension layer preserves fields that were not essential to describing the subject or activity, but are still represented in the log source. Extensions can contain two definitions. One contains schema fields, which are the fields required for any log from a given data source. The other definition contains activity type mapping, which includes the fields required for a specific activity type from a given data source." +
                                "\n"))
                        .append(new Heading("Data Sources", 3))
                        .append("\n");

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader( "Input/CIM.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject extensions = (JSONObject)jsonObject.get("Extensions");



            for(String key: new TreeSet<String>(extensions.keySet())) {

                    sb.append("* ");
                    String filename=key.replace(" ", "_") + ".md";
                    sb.append(new Link(key, filename));
                    sb.append("\n");

                    StringBuilder sbExtension =  new StringBuilder();

                    if(extensions.get(key)!=null) {
                        JSONObject extensionObj = (JSONObject)extensions.get(key);
                        String expression = extensionObj.get("expression").toString();

                        sbExtension.append(new Heading("Expression", 2))
                                .append("\n")
                                .append("\n")
                                .append(expression)
                                .append("\n")
                                .append("\n")
                                .append(new Heading("Fields", 2))
                                .append("\n")
                                .append("\n") ;
                        JSONObject fieldsObject=(JSONObject) extensionObj.get("fields") ;
                        Table.Builder tableBuilder = new Table.Builder()
                                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT)

                                .addRow("Field","Core","Detection","Informational");
                        int rowsCounter=0;


                        for (Iterator fieldsIterator = fieldsObject.keySet().iterator();fieldsIterator.hasNext();) {
                            String fieldkey = (String) fieldsIterator.next();
                            JSONObject fieldObject = (JSONObject) fieldsObject.get(fieldkey);
                            String core = "";
                            String detection = "";
                            String informational = "";
                            if (!(fieldObject ==null)) {
                                core = getcheckmark(fieldObject.get("core").toString());
                                detection = getcheckmark(fieldObject.get("detection").toString());
                                informational = getcheckmark(fieldObject.get("informational").toString());
                            }

                            tableBuilder.addRow(fieldkey, core, detection,informational);
                            rowsCounter++;
                        }
                        if(rowsCounter>0) {
                            sbExtension.append(tableBuilder.build());
                            sbExtension.append("\n\n");
                        }
                        else
                             sbExtension.append("There are no fields for this extension.\n\n" );


                        sbExtension.append(new Heading("Activity Types", 2))
                                .append("\n")
                                .append("\n") ;

                        JSONObject activitytypeObject=(JSONObject) extensionObj.get("activity_type") ;
                        Table.Builder tableBuilderActivities = new Table.Builder()
                                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT,Table.ALIGN_LEFT,Table.ALIGN_LEFT)

                                .addRow("Activity Type", "Field","Status","Core","Detection","Informational");
                        int rowsCounterAct=0;

                        for(String actKey: new TreeSet<String>(activitytypeObject.keySet())) {

                            JSONObject activityObject = (JSONObject) activitytypeObject.get(actKey);

                            JSONObject activityfieldsObject = (JSONObject) activityObject.get("fields");
                            String activityType=actKey;
                            int rowsCounterFieldsPerAct=0;
                            for(Iterator fieldsIterator = activityfieldsObject.keySet().iterator();fieldsIterator.hasNext();)

                            {
                                String fieldkey = (String) fieldsIterator.next();
                                JSONObject fieldObject = (JSONObject) activityfieldsObject.get(fieldkey);
                                String core = "";
                                String detection = "";
                                String informational = "";
                                String status = "";
                                if (!(fieldObject == null)) {
                                    if(fieldObject.get("Status")!=null)
                                        status = fieldObject.get("Status").toString();
                                    core = getcheckmark(fieldObject.get("core").toString());
                                    detection = getcheckmark(fieldObject.get("detection").toString());
                                    informational = getcheckmark(fieldObject.get("informational").toString());
                                }
                                tableBuilderActivities.addRow(activityType,  fieldkey, status, core, detection, informational);
                                activityType="";
                                rowsCounterAct++;
                                rowsCounterFieldsPerAct++;

                            }
                            if (rowsCounterFieldsPerAct==0)
                                tableBuilderActivities.addRow(activityType,  "", "",  "",  "",  "");



                        }

                        sbExtension.append(tableBuilderActivities.build());
                        sbExtension.append("\n\n");




                }
                    CreateExtensionFile(filename,sbExtension.toString());

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static String LoadSubjects() {

        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading(" Subject Interface", 1))
                        .append("\n")
                        .append("\n")
                        .append(new Heading(" Description", 3))
                        .append("\n")
                        .append("\n")
                        .append(new Text("This core interface defines the subject element. It details the minimum field requirements necessary to identify the subject across possible logs. By design, the subject is maintained as a minimalist interface, including only fields that are fundamental to representing the entity." +
                                "\n"))
                        .append("\n")
                        .append(new Heading(" Subjects", 3))
                        .append("\n")
                        .append("\n");

                Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT,Table.ALIGN_LEFT,Table.ALIGN_LEFT)

                .addRow("Subject", "Description", "Fields","Core","Detection","Informational");


        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader( "Input/CIM.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject subjects = (JSONObject)jsonObject.get("Subjects");


            for(String subjKey: new TreeSet<String>(subjects.keySet())) {
                if (subjKey.contains("app"))
                    logger.info("app");
                JSONObject subjectObject = (JSONObject) subjects.get(subjKey);
                String description = subjectObject.get("description").toString();
                JSONObject fieldsObject = (JSONObject) subjectObject.get("fields");
                if (((int) fieldsObject.size())>0) {
                    for (
                            Iterator fieldsIterator = fieldsObject.keySet().iterator(); fieldsIterator.hasNext(); ) {
                        String fieldkey = (String) fieldsIterator.next();
                        JSONObject fieldObject = (JSONObject) fieldsObject.get(fieldkey);
                        String core = "";
                        String detection = "";
                        String informational = "";
                        if (!(fieldObject == null)) {
                            core = getcheckmark(fieldObject.get("core").toString());
                            detection = getcheckmark(fieldObject.get("detection").toString());
                            informational = getcheckmark(fieldObject.get("informational").toString());
                        }
                        tableBuilder.addRow(subjKey, description, fieldkey, core, detection, informational);
                        subjKey = "";
                        description = "";
                    }

                }
                else
                    tableBuilder.addRow(subjKey, description, "No fields defined for this subject", "","", "");
            }

            sb.append(tableBuilder.build());

        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void CreateFieldDatatypePage() throws IOException {

        String filePath = outputPath + "Fields_Descriptions.md";
        String content = LoadFieldDatatype();

        Files.deleteIfExists(Paths.get(filePath));

        FileWriter myWriter = new FileWriter(filePath);
        myWriter.write(content);
        myWriter.close();
    }

    private static String LoadFieldDatatype() {

        StringBuilder sb = new StringBuilder()
                .append(new Heading("Fields Descriptions", 1))
                .append("\n")
                .append("\n")
                .append("This table lists the Common Information Model fields that can be used to build events and to create searches and correlation rules.")
                .append("\n")
                .append("\n");

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Input/field_datatype.json"));
            JSONArray jsonArray = (JSONArray) obj;

            List<JSONObject> fields = new ArrayList<>();
            for (Object item : jsonArray) {
                fields.add((JSONObject) item);
            }

            fields.sort((a, b) -> {
                String fa = a.get("Field").toString();
                String fb = b.get("Field").toString();
                return fa.compareToIgnoreCase(fb);
            });

            Table.Builder tableBuilder = new Table.Builder()
                    .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                    .addRow("Field", "Data Type", "Description");

            for (JSONObject field : fields) {
                String fieldName = field.get("Field").toString();
                String dataType = field.containsKey("DataType") ? field.get("DataType").toString() : "";
                String description = field.containsKey("Description") ? field.get("Description").toString() : "";
                tableBuilder.addRow(fieldName, dataType, description);
            }

            sb.append(tableBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private static String getcheckmark(String value) {
        String ret="";
        if (value.equals("1"))
            ret="&#10003;";
        return ret;
    }

    private static void CreateUniversalPage() {
        try {
            String universalPath = outputPath + "/Universal/Universal_Interface.md";

            String content = LoadUniversal();
            File directory = new File(universalPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Files.deleteIfExists(Paths.get(universalPath));

            FileWriter myWriter = null;

            myWriter = new FileWriter(universalPath);
            myWriter.write(content);
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String LoadUniversal() {
        StringBuilder sb =
                new StringBuilder()
                        .append(new Heading(" Universal Interface", 1))
                        .append("\n")
                        .append("\n")
                        .append(new Heading(" Description", 3))
                        .append("\n")
                        .append("\n")
                        .append(new Text("The universal interface defines a set of global fields. These are fields that are required for every event, regardless the type of event. The following list represents the minimum fields required to define an event. The list includes the CDI (core/detection/informational) values for each field. For more information about CDI values, see ")
                                +(new Link("Information Model Interface","https://docs.exabeam.com/en/content/all/exabeam-security-content/182296-security-content-in-the-common-information-model-structure.html#UUID-6a60b174-21d7-7d57-4a23-9a3f7a663f29")+".\n"))
                        .append("\n")
                        .append("\n")
                        .append(new Heading(" Universal fields", 3))
                        .append("\n")
                        .append("\n");

        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT,Table.ALIGN_LEFT)

                .addRow("Field","Core","Detection","Informational");


        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader( "Input/CIM.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject universal = (JSONObject)jsonObject.get("Universal");



            for(String key: new TreeSet<String>(universal.keySet())) {

                JSONObject subjectObject = (JSONObject) universal.get(key);
                String description = subjectObject.get("description") .toString();
                JSONObject fieldsObject=(JSONObject) subjectObject.get("fields") ;
                for(String fieldkey: new TreeSet<String>(fieldsObject.keySet())) {

                    JSONObject fieldObject = (JSONObject) fieldsObject.get(fieldkey);
                    String core = "";
                    String detection = "";
                    String informational = "";
                    if (!(fieldObject ==null)) {
                        core = getcheckmark(fieldObject.get("core").toString());
                        detection = getcheckmark(fieldObject.get("detection").toString());
                        informational = getcheckmark(fieldObject.get("informational").toString());
                    }
                    tableBuilder.addRow(fieldkey, core, detection,informational);

                }

            }
            sb.append(tableBuilder.build());

        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }




}
