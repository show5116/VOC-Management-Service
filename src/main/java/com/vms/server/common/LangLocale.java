package com.vms.server.common;

public enum LangLocale {

    KO("ko"),
    EN("en"),
    CN("cn");

    private String locale;

    LangLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
