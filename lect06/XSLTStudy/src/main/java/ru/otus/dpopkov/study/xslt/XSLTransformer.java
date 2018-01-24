package ru.otus.dpopkov.study.xslt;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.OutputStream;
import java.io.PrintWriter;

public class XSLTransformer {
    private Transformer transformer;

    public XSLTransformer(StreamSource xslSource) throws TransformerConfigurationException {
        transformer = TransformerFactory.newInstance().newTransformer(xslSource);
    }

    public void transform(StreamSource xmlSource, PrintWriter printWriter) throws TransformerException {
        transformer.transform(xmlSource, new StreamResult(printWriter));
    }

    void transform(StreamSource xmlSource, OutputStream outputStream) throws TransformerException {
        transformer.transform(xmlSource, new StreamResult(outputStream));
    }
}
