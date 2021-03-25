package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency) {
        return currencyService.add(currency); // currencyService  add  method  returns  info  in  Result  type
    }

    @GetMapping
    public List<Currency> getCurrencies() {
        return currencyService.get();
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id) {
        return currencyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return currencyService.delete(id);
    }

    @PutMapping("/{id}")
    public Result editById(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.edit(id, currency);
    }
}
