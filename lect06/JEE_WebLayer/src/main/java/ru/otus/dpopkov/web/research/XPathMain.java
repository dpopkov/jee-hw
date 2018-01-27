package ru.otus.dpopkov.web.research;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.FileInputStream;
import java.io.IOException;

public class XPathMain {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        String xmlLocation = "tmp/cb2.xml";
        FileInputStream fis = new FileInputStream(xmlLocation);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fis);

        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression = "/ValCurs/Valute[CharCode='AZN' or CharCode='GBP' or CharCode='KRW']";
        XPathExpression pathExpression = xPath.compile(expression);
        NodeList nodeList = (NodeList) pathExpression.evaluate(doc, XPathConstants.NODESET);

        System.out.println("NodeList(length=" + nodeList.getLength() + "):");
        print(nodeList);
    }

    private static void print(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(i + " : " + node.getNodeName() + " (" + node.getTextContent() + ")");
        }
    }
}
