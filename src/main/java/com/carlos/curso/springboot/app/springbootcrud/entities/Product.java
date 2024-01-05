package com.carlos.curso.springboot.app.springbootcrud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //SE AÑADEN ANOTACIONES PARA LA FUNCION DE VALIDACION
    @NotBlank(message ="{NotBlank.product.name}")
    @Size(min=3,max=20)
    private String name;

    //si le añades un argumento msg a una anotacion que pueda dar error configura el mensaje de error resultante
    @NotNull(message ="{NotNull.product.precio}")
    @Min(500)//min tambien valida que sea entero
    private Integer precio;

    @NotBlank(message ="{NotBlank.product.description}")
    private String description;

    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrecio() {
        return precio;
    }
    public void setPrice(Integer precio) {
        this.precio = precio;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    

}
