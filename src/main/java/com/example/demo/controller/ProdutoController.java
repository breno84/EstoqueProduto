package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Lista todos os produtos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso")
            })
    @GetMapping
    public List<Produto> getAllProdutos(){
        return produtoService.getAllProdutos();
    }

    @Operation(summary = "Lista produto pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Json Incoretod")
            })
    @GetMapping("/{id}")
    public Optional<Produto> getProdutoById(@PathVariable Long id){
        return produtoService.getProdutoById(id);
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
    public Produto createProduto(@RequestBody Produto produto){
        return produtoService.createProduto(produto);
    }

    @Operation(summary = "Deletando produto pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Produto não encontrado")
            })
    @DeleteMapping("delete/{id}")
    public String deleteProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
            return "Deletado com sucesso";
    }

    @Operation(summary = "Diminuir produto estoque",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Quantindo maior que no estoque"),
                    @ApiResponse(responseCode = "500", description = "Produto não encontrado")
            })
    @PostMapping("/subtrair")
    public Produto subtrairQtdProduot(@RequestBody Produto produto){
        return produtoService.subtrairQtdProduot(produto.getId(),produto.getQuantidade());
    }

    @Operation(summary = "Aumentar produto estoque",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Quantindo maior que no estoque"),
                    @ApiResponse(responseCode = "500", description = "Produto não encontrado")
            })
    @PostMapping("/somar")
    public Produto aumentarQtdProduot(@RequestBody Produto produto){
        return produtoService.aumentarQtdProduot(produto.getId(),produto.getQuantidade());
    }
}
