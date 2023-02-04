package ru.Kuznetsov.dto;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.jetbrains.annotations.NotNull;

public class ValCurs {
    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @NotNull
    private Date date;

    @JacksonXmlProperty(isAttribute = true,localName = "name")
    @NotNull
    private String name;

    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NotNull
    private List<Valute> valuteList;

    public ValCurs() {
    }

    public ValCurs(Date date, String name, List<Valute> valuteList) {
        this.date = date;
        this.name = name;
        this.valuteList = valuteList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Valute> getValuteList() {
        return valuteList;
    }

    public void setValuteList(List<Valute> valuteList) {
        this.valuteList = valuteList;
    }
}