package com.company;

//import org.openstreetmap.osmosis.xml.common.CompressionMethod;
//import javax.sql.rowset.spi.XmlReader;

//import org.openstreetmap.osmosis.xml.v0_6.XmlReader;
//import org.openstreetmap.osmosis.xml.v0_6.XmlWriter;
//import crosby.binary.osmosis.OsmosisReader;


import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {


        String filepathToOSM    = "src/com/company/map2.osm";
        String outputDirectory  = "src/com/company/output";

        String datafilePath = "src/com/company/datafile";
        String pathToCSV = "src/com/company/output/nodes.csv";

        // convert osm file to csv
        fileOperations.convertToCSV(filepathToOSM, outputDirectory);
        //write infoblock to datafile
        ArrayList<Block> blocks = new ArrayList<>();
        infoBlock infoBlock = new infoBlock(0);
        blocks.add(infoBlock);
        //add all data to blocks
        fileOperations.writeDataToBlocks(blocks, pathToCSV, datafilePath);
        //print all data from blocks to datafile
        fileOperations.printBlocksToFile(blocks, datafilePath);

        }









}
