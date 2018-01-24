package ru.otus.dpopkov.study.xslt;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;

/* For this example to work XML and XSL files must be in "resources" folder. */
public class XSLTransformMain {
    public static void main(String[] args) throws TransformerException {
        String xslLocation = "xml/cb.xsl";
        String xmlLocation = "xml/cb.xml";

        ClassLoader ctxClassLoader = Thread.currentThread().getContextClassLoader();
        XSLTransformer transformer = new XSLTransformer(
                new StreamSource(ctxClassLoader.getResourceAsStream(xslLocation)));
        transformer.transform(
                new StreamSource(ctxClassLoader.getResourceAsStream(xmlLocation)), System.out);
    }
}
