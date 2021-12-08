package com.transformpattern;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import com.transformpattern.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EmailMessage implements Serializable {
    @XmlElement
    private String greeting;
    @XmlElement
    private Scope scope;
    @XmlElement
    private String message;
    @XmlElementRef
    private URL url;

    public EmailMessage(){

    }

    public EmailMessage(String greeting,
                        Scope scope,
                        String message,
                        URL url) {
        this.greeting = greeting;
        this.scope = scope;
        this.message = message;
        this.url = url;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public com.transformpattern.Scope getScope() {
        return scope;
    }

    public void setScope(com.transformpattern.Scope scope) {
        this.scope = scope;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.transformpattern.URL getUrl() {
        return url;
    }

    public void setUrl(com.transformpattern.URL url) {
        this.url = url;
    }
}
