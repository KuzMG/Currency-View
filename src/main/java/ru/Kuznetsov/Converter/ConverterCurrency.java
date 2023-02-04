package ru.Kuznetsov.Converter;

import ru.Kuznetsov.dto.ValCurs;
import ru.Kuznetsov.model.CurrencyExchange;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ConverterCurrency {
    public List<CurrencyExchange> converterInCurrency(ValCurs valCurs){
        List<CurrencyExchange> currencyExchange = new ArrayList<CurrencyExchange>();
        for (int i=0;i<valCurs.getValuteList().size();i++)
            currencyExchange.add(new CurrencyExchange(i+1,valCurs.getValuteList().get(i).getValue(),valCurs.getValuteList().get(i).getNominal(),valCurs.getValuteList().get(i).getName(), valCurs.getValuteList().get(i).getCharCode(), valCurs.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        return currencyExchange;
    }
    public String[] ConvertToString(List<CurrencyExchange> currencyExchanges, int i){
         return new String[]{currencyExchanges.get(i).getId().toString(),
                 currencyExchanges.get(i).getValue().toString(),
                 currencyExchanges.get(i).getNominal().toString(),
                 currencyExchanges.get(i).getCurrencyName(),
                 currencyExchanges.get(i).getCurrencyCode()};
    }
}























