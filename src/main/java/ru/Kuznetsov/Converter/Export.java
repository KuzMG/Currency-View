package ru.Kuznetsov.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import ru.Kuznetsov.model.CurrencyExchange;
import ru.Kuznetsov.repository.CurrencyExchangeRepositorySqlitelmpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Export {
    private CurrencyExchangeRepositorySqlitelmpl repository;
    private ObjectMapper objectMapper;
    public void toJSON(File file){
        objectMapper = new ObjectMapper();
        repository = new CurrencyExchangeRepositorySqlitelmpl();
        try {
            objectMapper.writeValue(file, repository.findAll());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public void toCSV(File file){
        repository = new CurrencyExchangeRepositorySqlitelmpl();
        List<CurrencyExchange> currencyExchange = repository.findAll();
        StringWriter writer = new StringWriter();
        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            printer.printRecord("id", "value", "nominal", "currency_name", "currency_code", "date");//
            for (CurrencyExchange cur : currencyExchange) {
                printer.printRecord(
                        cur.getId(),
                        cur.getValue(),
                        cur.getNominal(),
                        cur.getCurrencyName(),
                        cur.getCurrencyCode(),
                        cur.getDate()
                );
            }
            String str = writer.toString().replaceAll("\\r\\n?", "\n");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
