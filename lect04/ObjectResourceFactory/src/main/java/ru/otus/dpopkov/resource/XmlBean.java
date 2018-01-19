package ru.otus.dpopkov.resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlBean {
    private Path xmlPath;

    public Path getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(Path xmlPath) {
        this.xmlPath = xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = Paths.get(xmlPath);
    }

    /**
     * Performs marshalling of the specified object and saves XML to path specified
     * in <code>xmlPath</code> attribute of this <code>XmlBean</code>.
     * @param persistObject object to be marshalled
     */
    public void process(Object persistObject) {
        try {
            // creating the JAXB context
            JAXBContext jaxbContext = JAXBContext.newInstance(persistObject.getClass());
            // creating the marshaller object
            Marshaller marshaller = jaxbContext.createMarshaller();
            // setting the property to show xml format output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // calling the marshall method
            marshaller.marshal(persistObject, Files.newOutputStream(xmlPath));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
