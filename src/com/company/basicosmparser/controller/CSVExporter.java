/*
	Copyright 2014 Adrien PAVIE
	
	This file is part of BasicOSMParser.
	
	BasicOSMParser is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	
	BasicOSMParser is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with BasicOSMParser. If not, see <http://www.gnu.org/licenses/>.
 */

package com.company.basicosmparser.controller;

import com.company.basicosmparser.model.Element;
import com.company.basicosmparser.model.Node;

import java.text.SimpleDateFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * This class allows you to export a Map of {@link Element}s as several CSV files.
 * One CSV file is created :  nodesxx.xx.xx.csv
 *
 * Nodes.csv contains nodes coordinates.
 * @author Adrien PAVIE
 */
public class CSVExporter {
//OTHER METHODS
	/**
	 * Exports a map of Elements as CSV files.
	 * @param elements The element objects to export
	 * @param outputFolder The folder where CSV files will be written
	 * @throws IOException If an error occurs during CSV writing
	 */
	public void export(Map<String,Element> elements, File outputFolder) throws IOException {
		Element currentElem = null;
		
		//Create output
		StringBuilder csvNodesBuild = new StringBuilder("ID;latitude;longitude;tags");


		//Create CSV entries for each element
		for(String id : elements.keySet()) {
			currentElem = elements.get(id);

			/*
			 * CSV content (depends of object type)
			 */
			if(currentElem instanceof Node) {
				//Node element
				Node currentNode = (Node) currentElem;
				//add ID
				addID(csvNodesBuild, currentElem);
				//add coordinates
				csvNodesBuild.append(";"+currentNode.getLat()+";"+currentNode.getLon()+";");
				//add tags
				addTags(csvNodesBuild, currentElem);
			}

		}
		
		//Write CSV
		File csvNodes = new File("src/com/company/output/nodes.csv");
		//+new SimpleDateFormat("HH.mm.ss").format(new java.util.Date())+

		writeTextFile(csvNodes, csvNodesBuild.toString());

	}
	
	/**
	 * TODO add only name tags
	 * Adds the tags of an Element in the given StringBuilder
	 * @param sb The string builder
	 * @param elem The element
	 */
	private void addTags(StringBuilder sb, Element elem) {
		//Start tags array
		sb.append("[");

		boolean firstTag = true;

		//Add each tag
		for(String key : elem.getTags().keySet()) {

			//add only if tag==name
			if (key.equalsIgnoreCase("name"))
			{
				if (!firstTag) {
					sb.append(",");
				} else {
					firstTag = false;
				}

				sb.append(key + "=" + elem.getTags().get(key));
			}
		}
		
		//End array
		sb.append("]");
	}
	
	/**
	 * Adds ID in a StringBuilder
	 * @param sb The string builder
	 * @param elem The element
	 */
	private void addID(StringBuilder sb, Element elem) {
		sb.append('\n'+elem.getId()

		);
	}
	
	/**
	 * Writes a text file.
	 * @param output The file to write in
	 * @param text The text to write
	 * @throws IOException If an error occurs during writing
	 */
	private void writeTextFile(File output, String text) throws IOException {
		Writer w = new OutputStreamWriter(new FileOutputStream(output));
		w.write(text);
		w.close();
	}
}
