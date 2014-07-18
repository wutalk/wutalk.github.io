/*
 * @(#) 2014-7-10
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.xml;

import java.io.FileReader;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * @author wutalk
 */
public class MySAXApp extends DefaultHandler {

	static String path = PathConstructor.path("employees.xml");

	public MySAXApp() {
		super();
	}

	public static void main(String args[]) throws Exception {
		XMLReader xr = XMLReaderFactory.createXMLReader();
		MySAXApp handler = new MySAXApp();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);

		// Parse each file provided on the
		// command line.

		FileReader r = new FileReader(path);
		xr.parse(new InputSource(r));
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("start doc");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("end doc");
	}

	@Override
	public void startElement(String uri, String name, String qName, Attributes atts)
			throws SAXException {
		// System.out.println("uri: " + uri + ", name: " + name + ", qName: " + qName + ", atts: " +
		// atts.getLength());
		System.out.println("start ele: uri=" + uri + ", qName=" + qName);
	}

	@Override
	public void endElement(String uri, String name, String qName) throws SAXException {
		System.out.println("end ele: " + qName);
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		System.out.print("Characters:    \"");
		for (int i = start; i < start + length; i++) {
			switch (ch[i]) {
			case '\\':
				System.out.print("\\\\");
				break;
			case '"':
				System.out.print("\\\"");
				break;
			case '\n':
				System.out.print("\\n");
				break;
			case '\r':
				System.out.print("\\r");
				break;
			case '\t':
				System.out.print("\\t");
				break;
			default:
				System.out.print(ch[i]);
				break;
			}
		}
		System.out.print("\"\n");
	}

}
