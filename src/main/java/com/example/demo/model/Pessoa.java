package com.example.demo.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cpf_cnpj;

    private String nome;
    private Double preco;
    private Integer quantidade;
    private Date dataVenda;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;

    public Pessoa() {
    }

    public Pessoa(String cpf_cnpj, String nome, Double preco, Integer quantidade, Produto produto,Date dataVenda) {
        this.cpf_cnpj = cpf_cnpj;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.produto = produto;
        this.dataVenda = dataVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    @NotNull
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    @NotNull
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
}
