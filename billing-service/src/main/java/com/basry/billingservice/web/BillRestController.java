package com.basry.billingservice.web;

import com.basry.billingservice.entities.Bill;
import com.basry.billingservice.feign.CustomerRestClient;
import com.basry.billingservice.feign.InventoryRestClient;
import com.basry.billingservice.repositories.BillRepository;
import com.basry.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BillRestController {
    private  BillRepository billRepository;
    private  ProductItemRepository productItemRepository;
    private  CustomerRestClient customerRestClient;
    private  InventoryRestClient inventoryRestClient;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, InventoryRestClient inventoryRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping(path = "/bills/full/{id}")
    Bill getBill(@PathVariable(name = "id") Long id) {
        System.out.println("id" + id);
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(inventoryRestClient.getProductById(pi.getProductID()));
        });
        return bill;
    }
}
