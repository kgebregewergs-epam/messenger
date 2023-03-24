package com.epam.ld.module2.testing;

/**
 * The type Client.
 */
public class Client {
    private String addresses;

    public Client(String addresses) {
        this.addresses = addresses;
    }

    public Client() {
    }

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    public String getAddresses() {
        return addresses;
    }

    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}
