package com.project.marketing.controller;

import com.project.marketing.entity.PDEntity;
import com.project.marketing.service.PDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductDetailsController {

    @Autowired
    private PDService pdService;

    @GetMapping("/product")
    public Page<PDEntity> getAllExpenses(Pageable page) {
        return pdService.getAllDetails(page);
    }

    @PostMapping("/addfile")
    public ResponseEntity<String> addFile(@RequestParam("file") MultipartFile file) {

        return new ResponseEntity<>(pdService.addFile(file), HttpStatus.OK);

    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Page<PDEntity>> productsBySupplierName(@PathVariable String name,Pageable page) {

        return new ResponseEntity<>(pdService.getStock(name,page),HttpStatus.OK);
    }

    @GetMapping("/product1/{name}")
    public ResponseEntity<List<PDEntity>> notExpiryProductsBySupplier(@PathVariable String name,Pageable page) {

        return new ResponseEntity<>(pdService.getStockByNotExp(name,page),HttpStatus.OK);
    }

    @GetMapping("/product/list/{name}")
    public ResponseEntity<List<PDEntity>> productsByName(@PathVariable String name,Pageable page) {

        return new ResponseEntity<>(pdService.getStockByProductName(name,page),HttpStatus.OK);
    }
}
