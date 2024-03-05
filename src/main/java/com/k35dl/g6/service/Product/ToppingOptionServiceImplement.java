package com.k35dl.g6.service.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k35dl.g6.models.Product.ToppingOption;
import com.k35dl.g6.repository.Product.ToppingOptionRepository;

@Service
public class ToppingOptionServiceImplement implements ToppingOptionService {

    @Autowired
    private ToppingOptionRepository toppingOptionRepository;

    @Override
    public ToppingOption createToppingOption(ToppingOption toppingOption) {
        ToppingOption newTopping = new ToppingOption();

        newTopping.setName(toppingOption.getName());
        newTopping.setPrice(toppingOption.getPrice());

        return toppingOptionRepository.save(newTopping);
    }

    @Override
    public ToppingOption updateToppingOption(ToppingOption toppingOption, Long toppingOptionId) throws Exception {
        Optional<ToppingOption> toppingOption1 = toppingOptionRepository.findById(toppingOptionId);

        if (toppingOption1.isEmpty()) {
            throw new Exception("Không tìm thấy topping có id " + toppingOptionId);
        }

        ToppingOption oldTopping = toppingOption1.get();

        if (toppingOption.getName() != null) {
            oldTopping.setName(toppingOption.getName());
        }

        if (toppingOption.getPrice() != 0) {
            oldTopping.setPrice(toppingOption.getPrice());
        }

        ToppingOption updatedToppingOption = toppingOptionRepository.save(oldTopping);

        return updatedToppingOption;
    }

    @Override
    public String deleteToppingOption(Long toppingOptionId) throws Exception {
        ToppingOption toppingOption = findToppingOptionById(toppingOptionId);

        toppingOptionRepository.delete(toppingOption);

        return "Đã xóa thành công";
    }

    @Override
    public ToppingOption findToppingOptionById(Long toppingOptionId) throws Exception {
        Optional<ToppingOption> opt = toppingOptionRepository.findById(toppingOptionId);

        if (opt.isEmpty()) {
            throw new Exception("Không tìm thấy topping có id "+toppingOptionId);
        }

        return opt.get();

    }

    @Override
    public List<ToppingOption> getAllToppingOptions() {
        return toppingOptionRepository.findAll();
    }

}
