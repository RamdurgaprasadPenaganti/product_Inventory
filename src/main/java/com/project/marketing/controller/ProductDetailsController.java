package com.project.marketing.controller;

import com.project.marketing.entity.PDEntity;
import com.project.marketing.service.PDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class ProductDetailsController {

    @Autowired
    private PDService pdService;

    @GetMapping("/pd")
    public Page<PDEntity> getAllExpenses(Pageable page) {
        return pdService.getAllDetails(page);
    }

    @PostMapping("/kk")
    public ResponseEntity<String> addFile(@RequestParam("file") File file) {

        return new ResponseEntity<>(pdService.addFile(file), HttpStatus.OK);

    }


    @GetMapping("/pd/{name}")
    public ResponseEntity<Page<PDEntity>> stockAvailable(@PathVariable String name,Pageable page) {

        return new ResponseEntity<>(pdService.getStock(name,page),HttpStatus.OK);
    }

    @GetMapping("/pd/notexp/{name}")
    public ResponseEntity<List<PDEntity>> notExpProducts(@PathVariable String name,Pageable page) {

        return new ResponseEntity<>(pdService.getStockByNotExp(name,page),HttpStatus.OK);
    }
}
