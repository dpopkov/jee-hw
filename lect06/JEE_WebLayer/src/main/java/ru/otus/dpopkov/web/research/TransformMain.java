package ru.otus.dpopkov.web.research;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class TransformMain {
    public static void main(String[] args) throws FileNotFoundException, TransformerException, UnsupportedEncodingException {
        String xmlLocation = "tmp/cb2.xml";
        String xslLocation = "tmp/cb.xsl";

        StreamSource xmlSource = new StreamSource(new InputStreamReader(
                new FileInputStream(xmlLocation), "utf-8"));
        StreamSource xslSource = new StreamSource(new InputStreamReader(
                new FileInputStream(xslLocation)));

        Transformer transformer = TransformerFactory.newInstance().newTransformer(xslSource);

        transformer.transform(xmlSource, new StreamResult(System.out));
    }
}
