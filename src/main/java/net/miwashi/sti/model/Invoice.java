package net.miwashi.sti.model;

import java.util.Date;

public class Invoice {
    Integer id;
    Integer  customerId;
    Date invoiceDate;
    String billingAddress;
    String  billingCity;
    String billingState;
    String billingCountry;
    String billingPostalCode;
    Double total;
}
