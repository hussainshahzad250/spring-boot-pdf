package com.sas.converter;

import com.sas.response.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemAssembler {

    public List<Item> getItems() {
        List<Item> quoteItems = new ArrayList<>();
        Item quoteItem1 = new Item();
        quoteItem1.setDescription("Test Quote Item 1");
        quoteItem1.setQuantity(1);
        quoteItem1.setUnitPrice(100.0);
        quoteItem1.setTotal(100.0);
        quoteItems.add(quoteItem1);

        Item quoteItem2 = new Item();
        quoteItem2.setDescription("Test Quote Item 2");
        quoteItem2.setQuantity(4);
        quoteItem2.setUnitPrice(500.0);
        quoteItem2.setTotal(2000.0);
        quoteItems.add(quoteItem2);

        Item quoteItem3 = new Item();
        quoteItem3.setDescription("Test Quote Item 3");
        quoteItem3.setQuantity(2);
        quoteItem3.setUnitPrice(200.0);
        quoteItem3.setTotal(400.0);
        quoteItems.add(quoteItem3);
        return quoteItems;
    }
}
