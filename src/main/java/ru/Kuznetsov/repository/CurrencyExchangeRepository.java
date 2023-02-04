package ru.Kuznetsov.repository;

import ru.Kuznetsov.model.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeRepository {
    CurrencyExchange findByld(Integer id);
    List<CurrencyExchange> findAll();
    List<CurrencyExchange> findAllByCode(String currencyCode);
    Integer delete(Integer id);
    void deleteAll();
    Integer insert(CurrencyExchange currency);
    Integer update(CurrencyExchange currency);

}
