package com.exabeam.DocPortalSanityTool;

import net.steppschuh.markdowngenerator.text.heading.Heading;
import net.steppschuh.markdowngenerator.table.Table;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


import java.util.logging.FileHandler;
import java.util.logging.Logger;


import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;


public class DocPortalSanity {

    private static String logFile = "DocumentationQualityReport.log";
    private static String outputPath ="OutputMD/";

    private static String inputPath="Input/dist/private/";

    private static Logger logger;



    public static void main(String[] args) throws IOException {

        FileHandler handler = new FileHandler(logFile, true);

        logger = Logger.getLogger("Exabeam Documentation Site");
        logger.addHandler(handler);
        logger.info("Start running Doc Portal Sanity Tool");

        try {
            if (!init()) {
                logger.warning("Exit with ERROR. Process not finished");
            } else
                logger.info("Complete running Doc Portal Sanity Tool - Please check OutputMD folder for created report.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean init() throws Exception {


         //PaligoApiClient.GetDocumentById();
        try {
          // String ownerFile=PaligoApiClient.getDocumentOwnerByFilename("121612-bitglass-cloud-connector.html");
           // String ownerID=PaligoApiClient.getFolderOwnerIdByFolderName("Advanced Analytics");
            //String fullName=PaligoApiClient.getUserFullNameByOwnerId(ownerID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Creating output path:" + outputPath);
        File directory = new File(outputPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String[] args=new String[2];
        args[0]=inputPath;
        args[1]=outputPath ;
        LinkChecker linkChecker=new LinkChecker();
        linkChecker.CheckLinks(args);



        return true;
    }


}
