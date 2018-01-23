package ru.otus.dpopkov.study;

import ru.otus.dpopkov.study.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MainUnmarshal {
    public static void main(String[] args) {
        String location = "c:/temp/employee01.xml";
        File file = new File(location);

        Employee employee = unmarshal(file, Employee.class);
        System.out.println("Unmarshalled from: " + file.getAbsolutePath());
        System.out.println(employee);
    }

    private static <T> T unmarshal(File file, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object object = unmarshaller.unmarshal(file);
            return clazz.cast(object);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
