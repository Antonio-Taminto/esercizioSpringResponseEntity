package com.example.esercizioSpringResponseEntity.controllers;

import com.example.esercizioSpringResponseEntity.entities.ProductEntity;
import com.example.esercizioSpringResponseEntity.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class ProductController {
    @Autowired
    private ProductService service;
    @PostMapping("/createproduct")
    public ResponseEntity<ProductEntity> createUser(@RequestBody ProductEntity productEntity){
        service.addProduct(productEntity);
        return ResponseEntity.ok().body(productEntity);
    }
    @GetMapping("/productbyid/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id){
        Optional<ProductEntity> productEntityOptional = service.getProductById(id);
        if(productEntityOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productEntityOptional.get());
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getAllProduct(){
        List<ProductEntity> productEntityList = service.getAllProduct();
        return ResponseEntity.ok().body(productEntityList);
    }
    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<ProductEntity> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductEntity productEntity){
        Optional<ProductEntity> productEntityOptional = service.updateProduct(id,productEntity);
        if(productEntityOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productEntityOptional.get());
    }
    @DeleteMapping("/deleteproduct")
    public ResponseEntity<ProductEntity> delectProduct(@RequestBody ProductEntity productEntity){
        ProductEntity productEntityVar = service.deleteProduct(productEntity);
        return ResponseEntity.ok().body(productEntityVar);
    }
    @GetMapping("/findproductbynome")
    public ResponseEntity<ProductEntity> findProductByNome(@RequestParam String nome){
        Optional<ProductEntity> productEntityOptional = service.findProductByNome(nome);
        if(productEntityOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productEntityOptional.get());
    }

}
