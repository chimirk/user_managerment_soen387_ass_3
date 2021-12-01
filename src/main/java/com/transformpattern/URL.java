package com.transformpattern;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class URL implements Serializable {
    @XmlAttribute
    private String href;
    @XmlValue
    private String value;

    public URL() {

    }

    public URL(String href, String value) {
        this.href = href;
        this.value = value;
    }

}
