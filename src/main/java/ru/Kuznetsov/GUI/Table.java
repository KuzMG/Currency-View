package ru.Kuznetsov.GUI;

import ru.Kuznetsov.Converter.ConverterCurrency;
import ru.Kuznetsov.dto.RequestInCB;
import ru.Kuznetsov.model.CurrencyExchange;
import ru.Kuznetsov.repository.CurrencyExchangeRepositorySqlitelmpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Table extends JPanel {
    private CurrencyExchangeRepositorySqlitelmpl repository;
    private JTable table;
    private ConverterCurrency converter;
    private DefaultTableModel modelTable;
    private RequestInCB requestInCB;
    private JScrollPane sp;
    private String[] tblheader = {"№", "Курс", "Номинал", "Валюта","Букв. код"};
    public Table() { }
    public DefaultTableModel createTable(){
        repository = new CurrencyExchangeRepositorySqlitelmpl();
        converter = new ConverterCurrency();
        modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(tblheader);
        for(int i=0;i<repository.findAll().size();i++)
            modelTable.addRow(converter.ConvertToString(repository.findAll(), i));
        return  modelTable;
    }
   public DefaultTableModel updateTable(){
       requestInCB = new RequestInCB();
       repository = new CurrencyExchangeRepositorySqlitelmpl();
       List<CurrencyExchange> currencyExchange = requestInCB.request();
       for(int i=0;i<currencyExchange.size();i++)
           repository.update(currencyExchange.get(i));
       for(int i=0;i<repository.findAll().size();i++) {
           modelTable.removeRow(0);
           modelTable.addRow(converter.ConvertToString(repository.findAll(), i));
       }
       return modelTable;
   }
}
