package ru.Kuznetsov.dto;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import ru.Kuznetsov.Converter.ConverterCurrency;
import ru.Kuznetsov.model.CurrencyExchange;
import ru.Kuznetsov.repository.CurrencyExchangeRepositorySqlitelmpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestInCB {
    private RestAdapter retrofit;
    private RequestServer requestServer;
    private ValCurs valCurs;
    private ConverterCurrency converterCurrency;
    private CurrencyExchangeRepositorySqlitelmpl repository;
    public List<CurrencyExchange> request(){
        retrofit = new RestAdapter.Builder()
                .setEndpoint("https://cbr.ru/")
                .setConverter(new JacksonConverter(new XmlMapper()))
                .build();
        requestServer = retrofit.create(RequestServer.class);
        valCurs = requestServer.getExchange(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        converterCurrency = new ConverterCurrency();
        return converterCurrency.converterInCurrency(valCurs);
    }
}
