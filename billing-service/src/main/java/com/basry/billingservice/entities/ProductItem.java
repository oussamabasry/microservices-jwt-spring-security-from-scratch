package com.basry.billingservice.entities;

import com.basry.billingservice.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class ProductItem {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private Long quantity;
 private Double price;
 private Long productID;
 @Transient
 private Product product;
 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
 @ManyToOne
 private Bill bill;

}
