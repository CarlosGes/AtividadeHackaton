package com.example.technova.controller;

import com.example.technova.dto.ProdutoDto;
import com.example.technova.entity.Produto;
import com.example.technova.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/post")
    public ResponseEntity<ProdutoDto> create(@RequestBody ProdutoDto produtoDto){
        ProdutoDto produtoDtoSave = produtoService.createProduto(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDtoSave);
    }

    @GetMapping
    public List<Produto> getAll(){
        return produtoService.getAllProduto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getById(@PathVariable Long id){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.getById(id);
        if (produtoDtoOptional.isPresent()){
            return ResponseEntity.ok(produtoDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody ProdutoDto produtoDto){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.updateProduto(id, produtoDto);
        if (produtoDtoOptional.isPresent()){
            return ResponseEntity.ok(produtoDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (produtoService.delete(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
