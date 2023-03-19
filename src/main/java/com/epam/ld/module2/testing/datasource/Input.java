package com.epam.ld.module2.testing.datasource;

/**
 * The type Input.
 */
public class Input {
    String tag;
    String message;
    String address;

    /**
     * Instantiates a new Input type.
     *
     * @param tag     the given tag
     * @param message the given message we get from input
     * @param address the given address
     */
    public Input(String tag, String message, String address) {
        this.tag = tag;
        this.message = message;
        this.address = address;
    }

    public String getTag() {
        return tag;
    }

    public String getMessage() {
        return message;
    }

    public String getAddress() {
        return address;
    }
}
