package com.carlos.curso.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.curso.springboot.app.springbootcrud.entities.Product;
import com.carlos.curso.springboot.app.springbootcrud.repositories.ProductRepositoy;

//service es un componente que le indica que es una clase que utiliza logica de negocio
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoy productRepositoy;

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productoptional = productRepositoy.findById(id);
        productoptional.ifPresent(productdb -> {
            productRepositoy.delete(productdb);
        });
        return productoptional;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepositoy.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepositoy.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepositoy.save(product);
    }
    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
      Optional<Product> productoptional = productRepositoy.findById(id);
       if(productoptional.isPresent()){
            Product productdb = productoptional.orElseThrow();
            productdb.setName(product.getName());
            productdb.setPrice(product.getPrecio());
            productdb.setDescription(product.getDescription());
            return Optional.of(productRepositoy.save(productdb));
        }
        return productoptional;
        
    }

}
