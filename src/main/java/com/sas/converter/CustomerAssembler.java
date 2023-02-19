package com.sas.converter;

import com.sas.response.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssembler {

    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setCompanyName("Sas Tech Studio Pvt Ltd");
        customer.setContactName("Shahzad Hussain");
        customer.setAddress("2nd Floor");
        customer.setEmail("test@gmail.com");
        customer.setPhone("1234567890");
        return customer;
    }
}
