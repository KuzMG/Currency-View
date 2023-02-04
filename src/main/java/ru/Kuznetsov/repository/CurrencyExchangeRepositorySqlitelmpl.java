package ru.Kuznetsov.repository;

import javafx.util.converter.LocalDateStringConverter;
import org.jetbrains.annotations.NotNull;
import ru.Kuznetsov.Database.Database;
import ru.Kuznetsov.model.CurrencyExchange;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CurrencyExchangeRepositorySqlitelmpl implements  CurrencyExchangeRepository {
    private final Database database;
    public CurrencyExchangeRepositorySqlitelmpl() {
        database = Database.getInstance();
    }
    @Override
    public CurrencyExchange findByld(Integer id) {
        String sql = "SELECT id, value, nominal, currency_name, currency_code, date " +
                "FROM currencyExchange WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CurrencyExchange currencyExchanges = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs    = preparedStatement.executeQuery();
            currencyExchanges = new CurrencyExchange(
                    rs.getInt("id"),
                    rs.getDouble("value"),
                    rs.getInt("nominal"),
                    rs.getString("currency_name"),
                    rs.getString("currency_code"),
                    LocalDate.parse(rs.getString("date")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currencyExchanges;
    }
    @Override
    public List<CurrencyExchange> findAll() {
        String sql = "SELECT id, value, nominal, currency_name, currency_code, date " +
                "FROM currencyExchange";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<CurrencyExchange> currencyExchanges = new ArrayList<>();
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs    = preparedStatement.executeQuery();
            while(rs.next())
            currencyExchanges.add(new CurrencyExchange(
                    rs.getInt("id"),
                    rs.getDouble("value"),
                    rs.getInt("nominal"),
                    rs.getString("currency_name"),
                    rs.getString("currency_code"),
                    LocalDate.parse(rs.getString("date"))));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currencyExchanges;
    }
    @Override
    public List<CurrencyExchange> findAllByCode(String currencyCode) {
        String sql = "SELECT id, value, nominal, currency_name, currency_code, date " +
                "FROM currencyExchange WHERE currency_code=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<CurrencyExchange> currencyExchanges = new ArrayList<>();
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,currencyCode);
            ResultSet rs    = preparedStatement.executeQuery();
            currencyExchanges.add(new CurrencyExchange(
                            rs.getInt("id"),
                            rs.getDouble("value"),
                            rs.getInt("nominal"),
                            rs.getString("currency_name"),
                            rs.getString("currency_code"),
                            LocalDate.parse(rs.getString("date"))));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currencyExchanges;
    }
    @Override
    public Integer delete(Integer id) {
        String sql = "DELETE FROM currencyExchange" +
                "WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer numberChanges = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id.toString());
            numberChanges = preparedStatement.executeUpdate();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numberChanges;
    }
    @Override
    public void deleteAll() {
        String sql = "DELETE FROM currencyExchange;";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Integer insert(CurrencyExchange currency) {
        String sql = "INSERT INTO currencyExchange (id,value,nominal,currency_name,currency_code,date) VALUES (?,?,?,?,?,?)";
        Integer numberChanges = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,currency.getId());
            preparedStatement.setDouble(2,currency.getValue());
            preparedStatement.setInt(3,currency.getNominal());
            preparedStatement.setString(4,currency.getCurrencyName());
            preparedStatement.setString(5,currency.getCurrencyCode());
            preparedStatement.setString(6,currency.getDate().toString());
            numberChanges=preparedStatement.executeUpdate();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numberChanges;
    }
    @Override
    public Integer update(CurrencyExchange currency) {
        String sql = "UPDATE currencyExchange SET " +
                "value=?," +
                "nominal=?," +
                "currency_name=?," +
                "currency_code=?," +
                "date=?" +
                "WHERE id=?";
        Integer numberChanges = null;
        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,currency.getValue());
            preparedStatement.setInt(2,currency.getNominal());
            preparedStatement.setString(3,currency.getCurrencyName());
            preparedStatement.setString(4,currency.getCurrencyCode());
            preparedStatement.setString(5,currency.getDate().toString());
            preparedStatement.setInt(6,currency.getId());
            numberChanges=preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        database.closeConnection();
        return numberChanges;
    }
}
