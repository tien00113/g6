package com.k35dl.g6.service.Product;

import java.util.List;

import com.k35dl.g6.models.Product.ToppingOption;

public interface ToppingOptionService {
    public ToppingOption createToppingOption(ToppingOption toppingOption);

    public ToppingOption updateToppingOption(ToppingOption toppingOption, Long toppingOptionId) throws Exception;

    public String deleteToppingOption(Long toppingOptionId) throws Exception;

    public ToppingOption findToppingOptionById(Long toppingOptionId) throws Exception;

    public List<ToppingOption> getAllToppingOptions();
}
