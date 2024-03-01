package com.example.esercizioSpringResponseEntity.servicies;

import com.example.esercizioSpringResponseEntity.entities.ProductEntity;
import com.example.esercizioSpringResponseEntity.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public ProductEntity addProduct(ProductEntity product){
        return repo.save(product);
    }

    public Optional<ProductEntity> getProductById(Long id){
        Optional<ProductEntity> productEntityOptional = repo.findById(id);
        if(productEntityOptional.isPresent()){
            return productEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public List<ProductEntity> getAllProduct(){
        List<ProductEntity> productEntityList = repo.findAll();
        return productEntityList;
    }
    public Optional<ProductEntity> updateProduct(Long id,ProductEntity productEntity){
        Optional<ProductEntity> productEntityOptional = repo.findById(id);
        if(productEntityOptional.isPresent()){
            productEntityOptional.get().setNome(productEntity.getNome());
            productEntityOptional.get().setDescrizione(productEntity.getDescrizione());
            productEntityOptional.get().setPrezzo(productEntity.getPrezzo());
            repo.save(productEntityOptional.get());
            return productEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public ProductEntity deleteProduct(ProductEntity product){
        repo.delete(product);
        return product;
    }

    public Optional<ProductEntity> findProductByNome(String nome){
        Optional<ProductEntity> productEntityOptional = repo.findByNome(nome);
        if(productEntityOptional.isPresent()){
            return productEntityOptional;
        }else {
            return Optional.empty();
        }
    }


}
