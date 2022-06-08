package com.example.t2008m_restful_webservice.model;

import com.example.t2008m_restful_webservice.entity.Product;
import com.example.t2008m_restful_webservice.util.ConnectionHelper;
import com.example.t2008m_restful_webservice.util.SQLConfig;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryProductModel implements ProductModel{
//    private static List<Product> products = new ArrayList<>();

    @Override
    public boolean save(Product obj) {
        try
        {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLConfig.DATABASE_PRODUCT_INSERT);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setDouble(2, obj.getPrice());
            preparedStatement.execute();
            return true;
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
//        products.add(product);
//        return product;
    }

    @Override
    public boolean update(Product updateObj, int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.DATABASE_PRODUCT_UPDATE);
            statement.setString(1, updateObj.getName());
            statement.setDouble(2, updateObj.getPrice());
            statement.setInt(3, id);
            statement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
//        int foundIndex = -1;
//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getId() == id){
//                foundIndex = i;
//            }
//        }
//        if (foundIndex != -1){
//            products.get(foundIndex).setPrice(product.getPrice());
//            products.get(foundIndex).setName(product.getName());
//        }
//        return  products.get(foundIndex);
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.DATABASE_PRODUCT_DELETE);
            statement.setInt(1,id);
            statement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
//        int foundIndex = -1;
//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getId() == id){
//                foundIndex = i;
//            }
//        }
//        if (foundIndex != -1){
//            products.remove(foundIndex);
//            return true;
//        }
//        return false;
    }

    @Override
    public List<Product> findAll() {
        List<Product> listObj = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = SQLConfig.DATABASE_PRODUCT_SELECT;
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Product obj = new Product(id, name, price);
                listObj.add(obj);
            }
        }catch (Exception e){

        }
        return listObj;
//        return products;
    }

    @Override
    public Product findById(int id) {
        Product obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQLConfig.DATABASE_PRODUCT_SELECT2);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                obj = new Product(id, name, price);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return obj;
//        int foundIndex = -1;
//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getId() == id){
//                foundIndex = i;
//            }
//        }
//        if (foundIndex != -1){
//            return  products.get(foundIndex);
//        }
//        return null;
    }
}
