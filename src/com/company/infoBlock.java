package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;

public class infoBlock implements Block{
    int blockId;
    ArrayList<String> content;
    int blockSize;

    /**
     * Default constructor
     * @param id
     */
    infoBlock(int id){
        this.blockId = id;

        content = new ArrayList<>();
    }
    @Override
    public int getSize() {
        return blockSize;
    }

    @Override
    public void setSize(int size) {
        blockSize=size;
    }

    @Override
    public int getFreeSpace() {
        return 0;
    }

    @Override
    public void setFreeSpace(int size) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

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
        out.println("block"+getId());
        for(String s: content){
            out.println(s);
        }
    }

    @Override
    public int getNumberOfNodes() {
        return 0;
    }
}
