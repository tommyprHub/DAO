package com.tpr;

public class ProductManager {
    public static void main(String[] args) {
        ProductoDao product = new ProductoDaoImpl();

        // agregar nuevo producto
        product.insert(new Producto(100, "ARROZ", 1.50));

        // obtener el producto con ID = 100
        Producto p = product.read(100);
        System.out.println(p);

        // eliminar el producto con ID = 100
        if(product.delete(13) == true){
            System.out.println("se ha eliminado la fila indicada");
        }else{
            System.out.println("No se ha eliminado la fila indicada");
        }

        // hago el update
        product.update(new Producto(10,"SALCHIPAPA",345.543));
    }
}