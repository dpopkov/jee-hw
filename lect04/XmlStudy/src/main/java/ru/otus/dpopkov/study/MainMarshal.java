package ru.otus.dpopkov.study;

import ru.otus.dpopkov.study.model.Employee;
import ru.otus.dpopkov.study.model.Position;
import ru.otus.dpopkov.study.model.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;

public class MainMarshal {
    public static void main(String[] args) {
        String location = "c:/temp/employee01.xml";

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setAge(43);
        Position position = new Position(20L, "Director");
        employee.setPosition(position);
        Role role;
        role = new Role(30L, "Read Personal Info");
        employee.getRoleSet().add(role);
        role = new Role(32L, "Write Personal Info");
        employee.getRoleSet().add(role);

        process(employee, new File(location));
//        process(employee, new File(location), "employee");
    }

    /**
     * Marshals Employee object without <code>@XmlRootElement</code> or other annotations and outputs
     * the result to console.
     * @param employee employee for converting to XML
     */
    private static void process(Employee employee, File output, String elementName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(employee.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            JAXBElement<Employee> jaxbElement = new JAXBElement<>(new QName(elementName), Employee.class, employee);
            marshaller.marshal(jaxbElement, output);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void process(Object persistObject, File output) {
        try {
            // creating the JAXB context
            JAXBContext jaxbContext = JAXBContext.newInstance(persistObject.getClass());
            // creating the marshaller object
            Marshaller marshaller = jaxbContext.createMarshaller();
            // setting the property to show xml format output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // calling the marshall method
            marshaller.marshal(persistObject, output);
            System.out.println("Object: " + persistObject);
            System.out.println("Marshalled to " + output.getAbsolutePath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
