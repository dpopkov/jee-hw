package ru.otus.dpopkov.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class ValidatorUsage {
    public static void main(String[] args) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL schemaFile = ValidatorUsage.class.getResource("/xml/schema1.xsd");

        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(ValidatorUsage.class.getResourceAsStream("/xml/user-tmp.xml")));
            System.out.println("Validator finished without exceptions.");
        } catch (SAXException  e) {
            System.err.println("Validation error: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
