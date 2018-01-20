package ru.otus.dpopkov.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class UnmarshallerUsage {
    public static void main(String[] args) {
        String resource = "/xml/user-tmp2.xml";
        InputStream stream = UnmarshallerUsage.class.getResourceAsStream(resource);
        try {
            Customer customer = unmarshal(stream, Customer.class);
            System.out.println(customer);
            System.out.println("Unmarshalling finished.");
        } catch (JAXBException e) {
            System.err.println("Unmarshalling failed!");
            e.printStackTrace();
        }
    }

    private static <T> T unmarshal(InputStream inputStream, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object object = unmarshaller.unmarshal(inputStream);
        return clazz.cast(object);
    }
}
