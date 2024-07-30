package com.example.demo.controller;

import com.example.demo.model.Pessoa;
import com.example.demo.model.Produto;
import com.example.demo.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vendas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Lista todas as vendas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso")
            })
    @GetMapping
    public List<Pessoa> getAllVendas(){
        return pessoaService.getAllVendas();
    }

    @Operation(summary = "Lista vendas pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Json Incoretod")
            })
    @GetMapping("/{id}")
    public Optional<Pessoa> getVendasById(@PathVariable Long id){
        return pessoaService.getPessoById(id);
    }

    @Operation(summary = "Criacao de vendas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "venda criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Json Incorreto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "500", description = "venda não encontrado")
            })
    @PostMapping("/create")
    public Pessoa createPessoa(@RequestBody Pessoa pessoa){
        return pessoaService.createVenda(pessoa);
    }

    @Operation(summary = "Deletando venda pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Pessoa não encontrado")
            })
    @DeleteMapping("/delete/{id}")
    public String deletePessoa(@PathVariable Long id){
        pessoaService.deletePessoa(id);
            return "Deletado com sucesso";
    }

    @Operation(summary = "Lista todos as pessoas pelo CPF/CNPJ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso")
            })
    @GetMapping("/listaCpfCnpj")
    public List<Pessoa> getAllVendasCpfCnpj(@RequestParam String cpfCnpj){
        return pessoaService.findByCpf_cnpj(cpfCnpj);
    }

    @Operation(summary = "edicao de vendas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "editado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Json Incorreto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "500", description = "venda não encontrado")
            })
    @PostMapping("/editVenda")
    public Pessoa editVenda(@RequestBody Pessoa pessoa){
        return pessoaService.editVenda(pessoa);
    }
}
