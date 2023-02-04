package ru.Kuznetsov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.Kuznetsov.Converter.LocalDateSerializer;

import java.time.LocalDate;

public class CurrencyExchange {
    private Integer id;
    private Double value;
    private Integer nominal;
    private String currencyName;
    private String currencyCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    public CurrencyExchange() {
    }
    public CurrencyExchange(Integer id, Double value, Integer nominal, String currencyName, String currencyCode, LocalDate date) {
        this.id = id;
        this.value = value;
        this.nominal = nominal;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.date = date;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public Integer getNominal() {
        return nominal;
    }
    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }
    public String getCurrencyName() {
        return currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String getCurrencyCode) {
        this.currencyCode = getCurrencyCode;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "id=" + id +
                ", value=" + value +
                ", nominal=" + nominal +
                ", currencyName='" + currencyName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", date=" + date +
                '}';
    }
}
