package com.projetobackend.demo.controllers;

import com.projetobackend.demo.dto.ProdutoRecordDto;
import com.projetobackend.demo.models.ProdutoModel;
import com.projetobackend.demo.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoModel> addProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        // Adjust field names
        produtoModel.setNmProduto(produtoRecordDto.nmProduto());
        produtoModel.setVlProduto(produtoRecordDto.vlProduto());
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAllProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }

    @GetMapping("/{cdProduto}")
    public ResponseEntity<Object> getOneProduto(@PathVariable(value = "cdProduto") int cdProduto) {
        Optional<ProdutoModel> produto = produtoRepository.findByCdProduto(cdProduto);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

    @GetMapping("/destaque")
    public ResponseEntity<List<ProdutoModel>> getProdutosDestaque() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findByDestaqueTrue());
    }

    @GetMapping("/categoria/{dsCategoria}")
    public ResponseEntity<List<ProdutoModel>> getProdutosByCategoria(@PathVariable(value = "dsCategoria") String dsCategoria) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findByDsCategoria(dsCategoria));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ProdutoModel>> searchProdutos(@RequestParam(value = "nmProduto") String nmProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findByNmProdutoContainingIgnoreCase(nmProduto));
    }

    @PutMapping("/{cdProduto}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "cdProduto") int cdProduto,
                                                @RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
        Optional<ProdutoModel> produto = produtoRepository.findByCdProduto(cdProduto);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        var produtoModel = produto.get();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        // Adjust field names
        produtoModel.setNmProduto(produtoRecordDto.nmProduto());
        produtoModel.setVlProduto(produtoRecordDto.vlProduto());
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel));
    }

    @DeleteMapping("/{cdProduto}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "cdProduto") int cdProduto) {
        Optional<ProdutoModel> produto = produtoRepository.findByCdProduto(cdProduto);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        produtoRepository.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso");
    }
}