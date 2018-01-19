package ru.otus.dpopkov.resource;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import java.util.Enumeration;
import java.util.Hashtable;

public class XmlResourceFactory implements ObjectFactory {
    public Object getObjectInstance(Object obj, Name name2, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        // Acquire an instance of our specified bean class
        XmlBean xmlBean = new XmlBean();

        // Customize the bean properties from our attributes
        Reference ref = (Reference) obj;
        Enumeration addresses = ref.getAll();
        while (addresses.hasMoreElements()) {
            RefAddr address = (RefAddr) addresses.nextElement();
            String name = address.getType();
            String content = (String) address.getContent();
            if (name.equals("path")) {  // path - attribute of Resource element in context.xml
                xmlBean.setXmlPath(content);
            }
        }

        // Return the customized instance
        return xmlBean;
    }
}
