package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class which represent one block of data
 *
 */
public class dataBlock implements Block {

    int blockId;
    ArrayList<String> content;
    byte[] contents ;//todo add byte array
    int blockSize;
    int numberOfNodes;
    int freeSpace;

    /**
     * Default constructor
     * @param id
     */
    dataBlock(int id){
        this.blockId = id;
        setSize(32768); //Size of block is 32 Kbytes
        setFreeSpace(getSize());
        setNumberOfNodes(0);
        contents = new byte[getSize()];
        content = new ArrayList<>();
    }
    
private static int sizeOfStringArray (String array){

    //System.out.println(size+" "+array);
        return array.getBytes().length;
}

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    @Override
    public int getSize() {
        return blockSize;
    }

    @Override
    public void setSize(int size) {
        blockSize = size;
    }

    @Override
    public int getFreeSpace() {
        return freeSpace;
    }

    @Override
    public void setFreeSpace(int size) {
        freeSpace = size;
    }

    @Override
    public int getId() {
        return blockId;
    }

    @Override
    public void setId(int id) {
        blockId = id;
    }

    @Override
    public void setContent(ArrayList<String> content) {
        this.content =new ArrayList<String>(content);
    }

    @Override
    public void addRecord(String record) {
        content.add(record);
    }

    @Override
    public void printBlockToFile(PrintWriter out) {
        out.println("block"+getId()+" "+getNumberOfNodes());
        for(String s: content){
            //if(getId()==9||getId()==10)System.out.println(s);
            out.println(s);
        }
    }
}
