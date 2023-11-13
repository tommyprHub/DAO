package com.tpr;

public interface ProductoDao {
    public void insert(Producto product);
    public void update(Producto product);
    public boolean delete(Integer id);
    public Producto read(Integer id);
}
