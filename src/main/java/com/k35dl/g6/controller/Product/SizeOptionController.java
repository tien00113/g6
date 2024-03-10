package com.k35dl.g6.controller.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.k35dl.g6.exceptions.SizeOptionException;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.service.Product.SizeOptionService;

@RestController
public class SizeOptionController {
    @Autowired
    private SizeOptionService sizeOptionService;

    @PostMapping("/api/products/sizes")
    public SizeOption createSizeOption(@RequestBody SizeOption sizeOption) throws SizeOptionException{

        SizeOption createdSizeOption = sizeOptionService.createSizeOption(sizeOption);

        return createdSizeOption;
    }

    @PutMapping("/api/products/sizes/{sizeOptionId}")
    public SizeOption updateSizeOption(@RequestBody SizeOption sizeOption,@PathVariable Long sizeOptionId) throws SizeOptionException{

        SizeOption updatedSizeOption = sizeOptionService.updateSizeOption(sizeOption, sizeOptionId);

        return updatedSizeOption;
    }

    @DeleteMapping("/api/products/sizes/{sizeOptionId}")
    public String deleteSizeOption(@PathVariable Long sizeOptionId) throws SizeOptionException{

        String message = sizeOptionService.deleteSizeOption(sizeOptionId);

        return message;
    }
}
