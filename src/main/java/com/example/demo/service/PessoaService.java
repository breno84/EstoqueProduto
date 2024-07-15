package com.example.demo.service;


import com.example.demo.model.Pessoa;
import com.example.demo.model.Produto;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PessoaService {

    Logger logger = Logger.getLogger(PessoaService.class.getName());

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getAllProdutos(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getProdutoById(Long id){
        return pessoaRepository.findById(id);
    }

    public Pessoa createProduto(Pessoa pessoa){
        return  pessoaRepository.save(pessoa);
    }

    public Pessoa deleteProduto(Long id){
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrado"));
        pessoaRepository.deleteById(id);
        return pessoa;
    }

}
