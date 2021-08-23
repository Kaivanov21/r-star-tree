package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;

public interface Block {
    int getSize();
    void setSize(int size);
    int getFreeSpace();
    void setFreeSpace(int size);
    int getId();
    void setId(int id);
    void setContent(ArrayList<String> content);
    void addRecord(String record);
    void printBlockToFile(PrintWriter out);

    int getNumberOfNodes();
}
