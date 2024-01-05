package com.carlos.curso.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.curso.springboot.app.springbootcrud.entities.Product;
import com.carlos.curso.springboot.app.springbootcrud.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    //responseentity es una anotacion que se utiliza para manejar todo lo relacionado con la respuesta http
    //el path variable indica que hay que pasarle una id en el http
    //si ponemos una "?" indica que el responseentity puede ser cualqueir tipo de dato
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    //Requestbody indica que hay que pasarle los datos con los que se va a crear el producto en este caso
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    //binding result tiene que estar al lado del objeto que se valida y se utiliza para los mensajes de error
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,@PathVariable Long id) {
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Product> optionalproduct = service.update(id, product);
        if(optionalproduct.isPresent()){
             return ResponseEntity.status(HttpStatus.CREATED).body(optionalproduct.orElseThrow());
        }
                return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

     private ResponseEntity<?> validation(BindingResult result) {
        //el mapa funciona como un diccionario
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        //bad request es lo mismo que poner status 404 la dos lineas de abajo hacen lo mismo
        //return ResponseEntity.status(404).body(errors);
        return ResponseEntity.badRequest().body(errors);
    }
}
