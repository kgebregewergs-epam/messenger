package com.epam.ld.module2.testing.template;

/**
 * The type Template.
 */
public class Template {
    private String tag;
    private String value;

    public Template() {
    }


    public Template(String tag, String value) {
        this.tag = tag;
        this.value = value;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }

}
