package com.basry.billingservice;

import com.basry.billingservice.entities.Bill;
import com.basry.billingservice.entities.ProductItem;
import com.basry.billingservice.feign.CustomerRestClient;
import com.basry.billingservice.feign.InventoryRestClient;
import com.basry.billingservice.models.Customer;
import com.basry.billingservice.models.Product;
import com.basry.billingservice.repositories.BillRepository;
import com.basry.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, InventoryRestClient inventoryRestClient) {
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            Bill bill1 = billRepository.save(new Bill(null, new Date(),null,null,customer.getId()));
            PagedModel<Product> productPagedModel = inventoryRestClient.pageProducts(0,2);
            productPagedModel.forEach(p->{
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setProductID(p.getId());
                productItem.setQuantity(1L+ new Long(new Random().nextInt(100)));
                productItem.setBill(bill1);
                productItemRepository.save(productItem);
            });

        };
    }
}
