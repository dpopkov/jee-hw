package ru.otus.dpopkov.xmljsonhw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class XPathUsage {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        String location = "C:/temp/employees.xml";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(location));
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName() + ":");

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression xPathExpression;
        Object result;

        xPathExpression = xPath.compile("sum(//employee/salary)");
        result = xPathExpression.evaluate(document, XPathConstants.NUMBER);
        double sum = (double) result;
        System.out.println("Sum: " + sum);

        xPathExpression = xPath.compile("count(//employee/salary)");
        result = xPathExpression.evaluate(document, XPathConstants.NUMBER);
        double count = (double) result;
        System.out.println("Count: " + count);

        double averageSalary = sum / count;
        String averageSalaryStr = String.format(Locale.ENGLISH, "%.2f", averageSalary);
        System.out.println("Average salary: " + averageSalaryStr);

        String expression = "//employee[salary>" + averageSalaryStr + "]";
        System.out.println("expression: " + expression);
        xPathExpression = xPath.compile(expression);
        result = xPathExpression.evaluate(document, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;

        printNodes(nodeList);
    }

    private static void printNodes(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println();
                printSubElement(element, "id");
                printSubElement(element, "fullName");
                printSubElement(element, "login");
                printSubElement(element, "salary");
            }
        }
    }

    private static void printSubElement(Element element, String tagName) {
        System.out.println(tagName + ": " + element.getElementsByTagName(tagName).item(0).getTextContent());
    }
}
