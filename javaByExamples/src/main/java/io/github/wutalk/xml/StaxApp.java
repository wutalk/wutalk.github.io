/*
 * @(#)	2014-7-23
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * refer: http://www.xml.com/pub/a/2003/09/17/stax.html
 * 
 * @author wutalk
 */
public class StaxApp {
	static String path = PathConstructor.path("employees.xml");

	public static void main(String[] args) throws XMLStreamException, IOException {
		InputStream fis = new FileInputStream(path);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		// cursorMove(fis, factory);

		// XMLEventReader er = factory.createXMLEventReader(fis);
		// while(er.hasNext()) {
		// XMLEvent event = er.nextEvent();
		// System.out.println(event);
		// }

		fis.close();

		if (args.length != 1) {
			System.out.println("Usage: java DuplicateMo cm_upload_file_path");
			System.exit(0);
		}
		testCmUploadFile(args[0]);
	}

	private static void testCmUploadFile(String path) throws IOException, XMLStreamException {
		// InputStream fis = new
		// FileInputStream(PathConstructor.path("cm_upload_file.xml"));
//		InputStream fis = new FileInputStream("D:\\var\\tmp\\cm_upload_file.xml");
		InputStream fis = new FileInputStream(path);

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory.createXMLStreamReader(fis);
		String tagName = "";
		String moTagName = "managedObject";
		Set<String> dns = new HashSet<String>();
		boolean duplicated = false;
		while (!duplicated && parser.hasNext()) {
			int event = parser.next();
			int et = parser.getEventType();
			if (event != et) {
				System.out.println("oh no!");
			}
			switch (event) {
			case XMLStreamConstants.END_DOCUMENT:
				parser.close();
				break;
			case XMLStreamConstants.START_ELEMENT:
				tagName = parser.getLocalName();
				// System.out.println(tagName + "=(start)");
				if (moTagName.equals(tagName)) {
					String dn = parser.getAttributeValue(2);
					// System.out.println("val=" + dn);
					if (dns.contains(dn)) {
						System.out.println("found duplicate DN: " + dn);
						System.out.println("stop checking");
						duplicated = true;
					} else {
						dns.add(dn);
					}
				}
				break;
			default:
				// System.out.println("event type: " + event);
			}
		}
		fis.close();

		System.out.println("dn count:\t" + dns.size());
//		System.out.println("dn list:\t" + dns);
	}

	private static void cursorMove(InputStream fis, XMLInputFactory factory)
			throws XMLStreamException {
		XMLStreamReader parser = factory.createXMLStreamReader(fis);
		String lastName = "";
		while (parser.hasNext()) {
			int event = parser.next();
			int et = parser.getEventType();
			if (event != et) {
				System.out.println("oh no!");
			}
			switch (event) {
			case XMLStreamConstants.END_DOCUMENT:
				parser.close();
				break;
			case XMLStreamConstants.START_ELEMENT:
				lastName = parser.getLocalName();
				if ("Employee".equals(lastName)) {
					String val = parser.getAttributeValue(0);
					System.out.println("val=" + val);
				}
				System.out.println(lastName + "=(start)");
				break;
			case XMLStreamConstants.END_ELEMENT:
				lastName = parser.getLocalName();
				System.out.println(lastName + "=(end)");
				lastName = null;
				break;
			case XMLStreamConstants.CHARACTERS:
				if ("Name".equals(lastName)) {
					System.out.println("\t" + parser.getText());
				}
				// System.out.println("CHARACTERS: [" + parser.getText() + "]");
				break;
			default:
				System.out.println("event type: " + event);
			}
		}
	}

}
