package com.tpr;

import java.sql.*;

public class ProductoDaoImpl implements ProductoDao {


    public void insert(Producto product) {

        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","root","root");
            PreparedStatement preparedStatement = conn.prepareStatement("insert into productos values (?,?,?,?,?,?)")){

            int id = product.getId();
            String desc = product.getNombre();
            double precio = product.getPrecio();

            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,60);
            preparedStatement.setInt(3,600);
            preparedStatement.setString(4,desc);
            preparedStatement.setDouble(5,precio);
            preparedStatement.setInt(6,100);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto read(Integer id) {
        Connection conn = null;
        Producto product = null;
        try {

            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","root","root");

            try (PreparedStatement ps = conn.prepareStatement(
                    "select * from productos where productoid = ?")) {

                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {

                        product = new Producto(id, rs.getString("descripcion"),rs.getDouble("preciounit"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }

    public void update (Producto product){
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","root","root");
            PreparedStatement preparedStatement = connection.prepareStatement("update productos set preciounit = ?, descripcion = ? where productoid = ?") ){

            double precio = product.getPrecio();
            int id = product.getId();
            String nombre = product.getNombre();

            preparedStatement.setDouble(1,precio);
            preparedStatement.setString(2,nombre);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean delete(Integer id){
        boolean eliminado = false;

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","root","root");
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from productos where productoid = ?")){

            //comparo el id con los ids de la tabla
            ResultSet resultSet = statement.executeQuery("select * from productos");
            while(resultSet.next()){

                int idTable = resultSet.getInt(1);

                if (idTable == id){
                    //si son iguales, eliminaré
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                    eliminado = true;
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return eliminado;
    }
}