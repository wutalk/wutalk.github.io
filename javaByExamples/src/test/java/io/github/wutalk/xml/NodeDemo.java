package io.github.wutalk.xml;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class NodeDemo {

    private static final Logger logger = LoggerFactory.getLogger(NodeDemo.class);

    public static void main(String[] args) {
        logger.info("start node creation {}", System.currentTimeMillis());
        NodeDemo xml = new NodeDemo();
        logger.debug("processing...");
        xml.makeFile();
        logger.info("end.");
    }

    public void makeFile() {
        Document xmlDoc = new DocumentImpl();

        Element fromEle = xmlDoc.createElement("fromEvent");
        fromEle.setAttribute("attributeName", "matchedWs");

        Element attrEle = xmlDoc.createElement("attribute");
        attrEle.setAttribute("id", "nbi3gcInternalReservedWorkingsetNames");

        attrEle.appendChild(fromEle);

        xmlDoc.appendChild(attrEle);

        try {
            Source source = new DOMSource(xmlDoc);
            File xmlFile = new File("C:\\Temp\\yourFile.xml");
            StreamResult result = new StreamResult(new OutputStreamWriter(
                    new FileOutputStream(xmlFile), "utf-8"));
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
