package com.carlos.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import com.carlos.curso.springboot.app.springbootcrud.entities.Product;

//en este caso la interfaz funciona definiendo los metodos que van a existir
//revisando la interfaz podemos ver las cosas que se pueden realizar en el crud (en este caso crear,borrar,actualizar y buscar)
//la interfaz se extiende a ProductServiceImp(de implementacion) donde se desarrollaran los metodos que hemos definido aqui
public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);

    

    

}
