package com.basry.customerservice.dao;

import com.basry.customerservice.beans.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types =
        Customer.class)
interface CustomerProjection extends Projection {

    Long getId();
    String getName();
    String getEmail();

}