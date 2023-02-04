package ru.Kuznetsov.GUI;
import ru.Kuznetsov.Converter.Export;
import ru.Kuznetsov.repository.CurrencyExchangeRepository;
import ru.Kuznetsov.repository.CurrencyExchangeRepositorySqlitelmpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
public class Window extends JFrame {
    private JPanel panel;
    private CurrencyExchangeRepository repository;
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, export;
    private JMenuItem JSON, CSV, updateTable;
    private Export exportFile;
    private JTable table;
    private JScrollPane scrollPane;
    private Table actionTable;

    public Window() {
        panel = new JPanel();

        menuBar = new JMenuBar();
        menuFile = new JMenu("Файл");
        export = new JMenu("экспорт");
        CSV = new JMenuItem("CSV");
        CSV.addActionListener(this::actionCVS);
        JSON = new JMenuItem("JSON");
        JSON.addActionListener(this::actionJSON);
        export.add(CSV);
        export.add(JSON);
        menuEdit = new JMenu("Редактировать");
        updateTable = new JMenuItem("обновить таблицу");
        updateTable.addActionListener(this::actionUpdateTable);
        menuEdit.add(updateTable);
        menuFile.add(export);
        menuBar.add(menuFile);
        menuBar.add(menuEdit);


        actionTable = new Table();
        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setModel(actionTable.createTable());
        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        GridLayout layout = new GridLayout();
        panel.setLayout(layout);

        repository = new CurrencyExchangeRepositorySqlitelmpl();
        this.setTitle("Курс валют ("+  repository.findAll().get(0).getDate().toString() + ")");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setJMenuBar(menuBar);
        this.setContentPane(panel);
        this.setSize(500, 650);
        this.setResizable(false);
        this.setVisible(true);

    }
    public void actionJSON(ActionEvent e){
        exportFile = new Export();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("currencyExchange.json"));
        int t = fileChooser.showSaveDialog(this);
        if(t==JFileChooser.APPROVE_OPTION){
            exportFile.toJSON(fileChooser.getSelectedFile());
        }
    }
    public void actionCVS(ActionEvent e){
        exportFile = new Export();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("currencyExchange.csv"));
        int t = fileChooser.showSaveDialog(this);
        if(t==JFileChooser.APPROVE_OPTION){
            exportFile.toCSV(fileChooser.getSelectedFile());
        }
    }
    public void actionUpdateTable(ActionEvent e){
        table.setModel(actionTable.updateTable());
        this.setTitle("Курс валют ("+  repository.findAll().get(0).getDate().toString() + ")");
    }
}