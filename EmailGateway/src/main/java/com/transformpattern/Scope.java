package com.transformpattern;

import java.io.Serializable;

public class Scope implements Serializable {
    private String scopeMessage;
    private String scopePurpose;

    public String getScopeMessage() {
        return scopeMessage;
    }

    public void setScopeMessage(String scopeMessage) {
        this.scopeMessage = scopeMessage;
    }

    public String getScopePurpose() {
        return scopePurpose;
    }

    public void setScopePurpose(String scopePurpose) {
        this.scopePurpose = scopePurpose;
    }

    public Scope(String scopeMessage, String scopePurpose) {
        this.scopeMessage = scopeMessage;
        this.scopePurpose = scopePurpose;


    }
}
