package com.carlos.curso.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carlos.curso.springboot.app.springbootcrud.entities.Product;

public interface ProductRepositoy extends CrudRepository<Product, Long> {

}
