package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id){
        return produtoRepository.findById(id);
    }

    public Produto saveProduto(Produto produto){
        return  produtoRepository.save(produto);
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }


}
