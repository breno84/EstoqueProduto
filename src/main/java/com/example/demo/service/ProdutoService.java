package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProdutoService {

    Logger logger = Logger.getLogger(ProdutoService.class.getName());

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id){
        return produtoRepository.findById(id);
    }

    public Produto createProduto(Produto produto){
        return  produtoRepository.save(produto);
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Produto subtrairQtdProduot(Long id, Integer qtd){
        Produto produto1 = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        logger.info("Subtraindo quantidade do produto: " + produto1.getNome());
        Integer qtdProd = produto1.getQuantidade();

        if(qtd<=qtdProd){
            qtdProd -= qtd;
            produto1.setQuantidade(qtdProd);
            produtoRepository.saveAndFlush(produto1);
            logger.info("Quantidade retirada do estoque: " + qtd);
            return produto1;
        }else{
        logger.info("Quantidade maior que total no estoque: " + produto1.getQuantidade());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantiade invalida");
        }
}
    public Produto aumentarQtdProduot(Long id, Integer qtd){
        Produto produto1 = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        logger.info("Subtraindo quantidade do produto: " + produto1.getNome());
        Integer qtdProd = produto1.getQuantidade();

        if(qtd>0){
            qtdProd += qtd;
            produto1.setQuantidade(qtdProd);
            produtoRepository.saveAndFlush(produto1);
            logger.info("Quantidade retirada do estoque: " + qtd);
            return produto1;
        }else{
        logger.info("Quantidade maior que total no estoque: " + produto1.getQuantidade());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantiade invalida");
        }
}
}
