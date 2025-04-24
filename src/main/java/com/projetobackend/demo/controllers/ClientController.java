package com.projetobackend.demo.controllers;

import com.projetobackend.demo.dto.ClientRecordDto;
import com.projetobackend.demo.dto.SenhaRecordDto;
import com.projetobackend.demo.models.ClientModel;
import com.projetobackend.demo.repository.ClientRepository;
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
@RequestMapping("/cliente")

public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<ClientModel> addClient(@RequestBody @Valid ClientRecordDto clientRecordDto) {
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        // Fix for dtNasc to dtNascimento
        clientModel.setDtNasc(clientRecordDto.dtNasc());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    @GetMapping("/{cdCliente}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "cdCliente") int cdCliente) {
        Optional<ClientModel> client0 = clientRepository.findByCdCliente(cdCliente);
        if (client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    @PutMapping("/{cdCliente}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "cdCliente") int cdCliente,
                                               @RequestBody @Valid ClientRecordDto clientRecordDto) {
        Optional<ClientModel> client0 = clientRepository.findByCdCliente(cdCliente);
        if (client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrado");
        }
        var clientModel = client0.get();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        // Fix for dtNasc to dtNascimento
        clientModel.setDtNasc(clientRecordDto.dtNasc());
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }

    @DeleteMapping("/{cdCliente}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "cdCliente") int cdCliente) {
        Optional<ClientModel> cliente0 = clientRepository.findByCdCliente(cdCliente);
        if (cliente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrado");
        }
        clientRepository.delete(cliente0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso");

    }

    @PostMapping("/{cdCliente}/alterar-senha")
    public ResponseEntity<Object> alterarSenha(@PathVariable(value = "cdCliente") int cdCliente,
                                               @RequestBody @Valid SenhaRecordDto senhaRecordDto) {
        Optional<ClientModel> client0 = clientRepository.findByCdCliente(cdCliente);
        if (client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrado");
        }

        var clientModel = client0.get();

        if (!clientModel.getDsSenha().equals(senhaRecordDto.senhaAtual())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha atual incorreta");
        }

        clientModel.setDsSenha(senhaRecordDto.novaSenha());
        clientRepository.save(clientModel);

        return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso");
    }
}