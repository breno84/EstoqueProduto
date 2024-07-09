package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos(){
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> getProdutoById(@PathVariable Long id){
        return produtoService.getProdutoById(id);
    }

    @PostMapping("/create")
    public Produto createProduto(@RequestBody Produto produto){
        return produtoService.createProduto(produto);
    }

    @GetMapping("delete/{id}")
    public void deleteProduto(@PathVariable Long id){
            produtoService.deleteProduto(id);
    }
}
