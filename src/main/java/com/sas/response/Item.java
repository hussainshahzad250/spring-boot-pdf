package com.sas.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double total;
}