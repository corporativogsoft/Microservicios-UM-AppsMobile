package com.gsoft.univer.controller;

import com.gsoft.univer.entity.ProductoEntity;
import com.gsoft.univer.model.request.ProductoRequest;
import com.gsoft.univer.model.response.ProductoResponse;
import com.gsoft.univer.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/api/products")
public class ProductosContoller {

    @Autowired
    private ProductoRepository repository;

    @GetMapping("/producto/{id}")
    public ResponseEntity producutos (@PathVariable Integer id) {

//        Optional<ProductoEntity> producto = repository.findById(id);
//
//        if (producto.isPresent()){
//            return producto.get();
//        }else {
//            return null;
//        }
        Optional<ProductoEntity> productoEntity = repository.findById(id);
        if (productoEntity.isPresent()) {
            ProductoResponse response = new ProductoResponse(
                    productoEntity.get().getNombre(),
                    productoEntity.get().getSku(),
                    productoEntity.get().getCantidad()
            );
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity("No hay informacion ligada al ID", HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/save/product")
    public ResponseEntity<String> save (@RequestBody ProductoRequest request) {
        ProductoEntity entity = new ProductoEntity(
                request.getName(),
                request.getCantidad(),
                request.getSku()
        );

        repository.save(entity);

        return ResponseEntity.ok("todo bien");

    }
}
