package com.transformpattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLSerializer {
    public static <T> String serialize(T t) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
        StringWriter stringWriter = new StringWriter();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(t, stringWriter);
        return stringWriter.toString();

    }

}
