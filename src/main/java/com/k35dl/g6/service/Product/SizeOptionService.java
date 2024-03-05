package com.k35dl.g6.service.Product;

import java.util.List;

import com.k35dl.g6.exceptions.SizeOptionException;
import com.k35dl.g6.models.Product.SizeOption;

public interface SizeOptionService {
    public SizeOption createSizeOption(SizeOption sizeOption) throws SizeOptionException;

    public SizeOption updateSizeOption(SizeOption sizeOption, Long sizeOptionId) throws SizeOptionException;

    public String deleteSizeOption(Long sizeOptionId) throws SizeOptionException;

    public List<SizeOption> getAllSizeOptions();

    public SizeOption findSizeOptionById(Long sizeOptionId) throws SizeOptionException;
}
