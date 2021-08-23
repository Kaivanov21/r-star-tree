package com.company;

import com.company.basicosmparser.controller.CSVExporter;
import com.company.basicosmparser.controller.OSMParser;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;

public class fileOperations {


    /**
     *
     * Function to convert .osm file --> to.csv file which contains (id;lat;lon;name) of each point.
     *
     * @param pathToOSM
     * @param outputPath
     */
    public static void convertToCSV(String pathToOSM, String outputPath) {

        //Convert data
        OSMParser parser = new OSMParser();
        CSVExporter exporter = new CSVExporter();
        File f1 = new File(pathToOSM);  // directory for osm file
        File f2 = new File(outputPath); // directory for csv file

        try {
            exporter.export(parser.parse(f1), f2); // export data from f1 to f2
            System.out.println("Exported data to "+ f2.getPath()+" without errors.");
        } catch (IOException | SAXException e) {
            System.err.println("Error during data export.");
            e.printStackTrace();
        }

    }

    /**
     * Function to copy data from csv file to arraylist of blocks.
     */
    public static void writeDataToBlocks(ArrayList<Block> blocks, String pathToCSV, String datafilePath) {

        byte[] buffer = new byte[32768]; //32KB
        //read
        String line = "";
        String splitBy = ";";

        int index=1;
        try
        {
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(pathToCSV));
            int busyspace=0;
            new PrintWriter(datafilePath).close(); //clear datafile
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(datafilePath,true)));
            int blockId = 1;
            //dataBlock block1 = new dataBlock(blockId);
            blocks.add(new dataBlock(blockId));
            line = br.readLine(); //skip the header of csv file
            //writeHeader(out,blockId); // add id to first block
            while (( line = br.readLine()) != null)   //while not end of file
            {
                //String[] data = line.split(splitBy);    // split by separator

                if (writeRowToBlock((dataBlock) blocks.get(blockId), line) == -1) {//if block is full
                    blockId++;
                    System.out.println(blockId);
                    blocks.add(new dataBlock(blockId));
                    writeRowToBlock((dataBlock) blocks.get(blockId), line); //add line to the next block
                    //writeHeader(out,blockId);
                    //busyspace=0;
                } else{
                    //if block is not full
                    blocks.get(blockId).setFreeSpace(blocks.get(blockId).getFreeSpace() - line.getBytes().length);
                    //increase number of nodes
                    ((dataBlock) blocks.get(blockId)).setNumberOfNodes(((dataBlock) blocks.get(blockId)).getNumberOfNodes()+1);
                    //busyspace+=line.length();
                }
            }
            fillInfoBlock(blocks);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public static void fillInfoBlock (ArrayList<Block> blocks){
        int sum=0;
        for(Block b:blocks){
            blocks.get(0).addRecord(b.getId()+" "+b.getNumberOfNodes());
            sum+=b.getNumberOfNodes();
        }
        blocks.get(0).addRecord("all:"+blocks.size()+" "+sum);

    }

    /**
     *
     * @param block
     * @param rowToWrite
     * @return number of busy bytes in block
     * @throws IOException
     */
    public static int writeRowToBlock(dataBlock block, String rowToWrite) throws IOException {




       // System.out.println(sizeOfBlock+"-"+busySpace +"<"+ rowToWrite.getBytes().length);
        if(block.getFreeSpace() >= rowToWrite.getBytes().length) {
            //writeStringArrayToFile(out,rowToWrite);
            block.addRecord(rowToWrite);
            block.setFreeSpace(block.getFreeSpace()-rowToWrite.getBytes().length);
            //return busySpace+(rowToWrite).getBytes().length;
            return 1;
        } else {

            System.out.println("The block is full !");//todo add new block
            return -1;
        }
    }

    /**
     * @param blocks
     * @param outputDirectory
     * @throws IOException
     */
    public static void printBlocksToFile(ArrayList<Block> blocks, String outputDirectory ) throws IOException {
        new PrintWriter(outputDirectory).close(); // clear datafile
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputDirectory,true)));
        for (Block b:blocks){
            //System.out.println(b.getId()+"---");
            b.printBlockToFile(out);

        }
        out.flush();
        out.close();
    }


    private static void writeHeader (PrintWriter out, int blockId){

        //System.out.println(size+" "+array);
        out.println("block"+blockId);


    }

    private static void writeStringArrayToFile (PrintWriter out, String array){
        out.println(array);

        //System.out.println(23);
        out.flush();
        //out.close();
    }
    private static void writeInfoHeader(PrintWriter out, String array) {

    }
}
