package com.k35dl.g6.service.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.exceptions.SizeOptionException;
import com.k35dl.g6.models.Product.SizeOption;
import com.k35dl.g6.repository.Product.SizeOptionRepository;

@Service
public class SizeOptionServiceImplement implements SizeOptionService {

    @Autowired
    private SizeOptionRepository sizeOptionRepository;

    @Override
    public SizeOption createSizeOption(SizeOption sizeOption) throws SizeOptionException {

        SizeOption newSizeOption = new SizeOption();

        newSizeOption.setName(sizeOption.getName());
        newSizeOption.setPrice(sizeOption.getPrice());

        return sizeOptionRepository.save(newSizeOption);

    }

    @Override
    public SizeOption updateSizeOption(SizeOption sizeOption, Long sizeOptionId) throws SizeOptionException {
        Optional<SizeOption> sizeOption1 =  sizeOptionRepository.findById(sizeOptionId);

        if (sizeOption1.isEmpty()) {
            throw new SizeOptionException("không tìm thấy size có id "+sizeOptionId);
        }

        SizeOption oldSizeOption = sizeOption1.get();

        if(sizeOption.getName()!= null){
            oldSizeOption.setName(sizeOption.getName());
        }

        if(sizeOption.getPrice()!=0){
            oldSizeOption.setPrice(sizeOption.getPrice());
        }

        SizeOption updatedSizeOption = sizeOptionRepository.save(oldSizeOption);

        return updatedSizeOption;

    }

    @Override
    public String deleteSizeOption(Long sizeOptionId) throws SizeOptionException {
        
        SizeOption sizeOption = findSizeOptionById(sizeOptionId);

        sizeOptionRepository.delete(sizeOption);

        return "Đã xóa thành công";
    }

    @Override
    public List<SizeOption> getAllSizeOptions() {
        
        return sizeOptionRepository.findAll();
    }

    @Override
    public SizeOption findSizeOptionById(Long sizeOptionId) throws SizeOptionException {

        Optional<SizeOption> opt = sizeOptionRepository.findById(sizeOptionId);

        if (opt.isEmpty()) {
            throw new SizeOptionException("Không tìm thấy size có id" + sizeOptionId);
        }

        return opt.get();

    }

}
