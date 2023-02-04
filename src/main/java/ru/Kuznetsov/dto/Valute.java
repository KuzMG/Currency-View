package ru.Kuznetsov.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.jetbrains.annotations.NotNull;
import ru.Kuznetsov.Converter.DoubleDeserializer;

public class Valute {
    @JacksonXmlProperty(localName = "ID")
    @NotNull
    private String id;

    @JacksonXmlProperty(localName = "NumCode")
    @NotNull
    private Integer numCode;

    @JacksonXmlProperty(localName = "CharCode")
    @NotNull
    private String charCode;

    @JacksonXmlProperty(localName = "Nominal")
    @NotNull
    private Integer nominal;

    @JacksonXmlProperty(localName = "Name")
    @NotNull
    private String name;

    @JacksonXmlProperty(localName = "Value")
    @JsonDeserialize(using = DoubleDeserializer.class)
    @NotNull
    private Double value;

    public Valute() {
    }

    public Valute(String id, Integer numCode, String charCode, Integer nominal, String name, Double value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() { return value;}

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Valute{");
        sb.append("id='").append(id).append('\'');
        sb.append(", numCode=").append(numCode);
        sb.append(", charCode='").append(charCode).append('\'');
        sb.append(", nominal=").append(nominal);
        sb.append(", name='").append(name).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}