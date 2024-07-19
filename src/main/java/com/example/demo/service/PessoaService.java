package com.example.demo.service;


import com.example.demo.model.Pessoa;
import com.example.demo.model.Produto;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PessoaService {

    Logger logger = Logger.getLogger(PessoaService.class.getName());
    private Produto produto;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Pessoa> getAllVendas(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getPessoById(Long id){
        return pessoaRepository.findById(id);
    }

    @Transactional
    public Pessoa createVenda(Pessoa pessoa){
        int qtdVendida = pessoa.getQuantidade();
        Produto produto = produtoRepository.findById(pessoa.getProduto().getId()).
                orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        int qtdEstoque = produto.getQuantidade();

        produto.setQuantidade(qtdEstoque-qtdVendida);
        produtoRepository.saveAndFlush(produto);
        pessoa.setProduto(produto);

        return  pessoaRepository.saveAndFlush(pessoa);
    }

    public Pessoa deletePessoa(Long id){
        Pessoa pessoa = pessoaRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Pessoa não encontrado"));
        pessoaRepository.deleteById(id);
        return pessoa;
    }

    public List<Pessoa> findByCpf_cnpj(String cpfCnpj){
        return pessoaRepository.findBycpfCnpj(cpfCnpj);
    }

}
