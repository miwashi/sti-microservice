package net.miwashi.sti.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Customer {
    Integer id;
    String lastName;
    String firstName;
    String company;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String phone;
    String fax;
    String email;
    Integer supportedRepId;
}
