package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Result add(Currency currency) {

        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("Such currency exits", false);

        Currency newCurrency = new Currency();
        newCurrency.setName(currency.getName());
        newCurrency.setActive(currency.isActive());
        currencyRepository.save(newCurrency);

        return new Result("Currency added", true);
    }

    public List<Currency> get() {
        return currencyRepository.findAll();
    }

    public Currency getById(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElse(null);
    }

    public Result delete(Integer id) {

        boolean exists = currencyRepository.existsById(id);
        if (!exists)
            return new Result("Such currency not found", false);
        currencyRepository.deleteById(id);
        return new Result("Currency deleted", true);
    }

    public Result edit(Integer id, Currency currency) {

        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            Currency editing = optionalCurrency.get();
            editing.setName(currency.getName());
            editing.setActive(currency.isActive());
            currencyRepository.save(editing);
            return new Result("Currency edited", false);
        }
        return new Result("Such currency not found", true);
    }
}
