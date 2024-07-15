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

    @Operation(summary = "Lista todos os produtos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso")
            })
    @GetMapping
    public List<Pessoa> getAllProdutos(){
        return pessoaService.getAllProdutos();
    }

    @Operation(summary = "Lista produto pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Json Incoretod")
            })
    @GetMapping("/{id}")
    public Optional<Pessoa> getProdutoById(@PathVariable Long id){
        return pessoaService.getProdutoById(id);
    }

    @Operation(summary = "Criacao de produto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "produto criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Json Incorreto",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "500", description = "Produto não encontrado")
            })
    @PostMapping("/create")
    public Pessoa createProduto(@RequestBody Pessoa pessoa){
        return pessoaService.createProduto(pessoa);
    }

    @Operation(summary = "Deletando produto pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Pessoa não encontrado")
            })
    @DeleteMapping("delete/{id}")
    public String deleteProduto(@PathVariable Long id){
        pessoaService.deleteProduto(id);
            return "Deletado com sucesso";
    }


}
