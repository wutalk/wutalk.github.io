/*
 * @(#)	2014-9-19
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.xml;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author wutalk
 */
public class XmlParser {

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		String fileName = "D:\\var\\tmp\\ocos.xml";
		
		Document doc = builder.parse(new File(fileName));
		Element element = doc.getDocumentElement();
		NodeList nodes = element.getElementsByTagName("p");
		
		int size = nodes.getLength();
		System.out.println(size);

		Set<String> tags = new TreeSet<String>();
		for (int i = 0; i < size; i++) {
			Node node = nodes.item(i);
			NamedNodeMap attributes = node.getAttributes();
			Node namedItem = attributes.getNamedItem("name");
			tags.add(namedItem.getTextContent());
		}
		for (String tag : tags) {
			System.out.println(tag);
		}
	}
}
