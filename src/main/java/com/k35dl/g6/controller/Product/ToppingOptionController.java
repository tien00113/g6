package com.k35dl.g6.controller.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.models.Product.ToppingOption;
import com.k35dl.g6.service.Product.ToppingOptionService;

@RestController
public class ToppingOptionController {
    @Autowired
    private ToppingOptionService toppingOptionService;

    @PostMapping("/api/products/toppings")
    public ToppingOption creatToppingOption(@RequestBody ToppingOption toppingOption){

        ToppingOption createdToppingOption = toppingOptionService.createToppingOption(toppingOption);

        return createdToppingOption;
    }

    @PutMapping("/api/products/toppings/{toppingOptionId}")
    public ToppingOption updateToppingOption(@RequestBody ToppingOption toppingOption, @PathVariable Long toppingOptionId) throws Exception{

        ToppingOption updatedToppingOption = toppingOptionService.updateToppingOption(toppingOption, toppingOptionId);

        return updatedToppingOption;
    }

    @DeleteMapping("/api/products/toppings/{toppingOptionId}")
    public String deleteToppingOption(@PathVariable Long toppingOptionId) throws Exception{
        
        String messsage = toppingOptionService.deleteToppingOption(toppingOptionId);

        return messsage;
    }
}
