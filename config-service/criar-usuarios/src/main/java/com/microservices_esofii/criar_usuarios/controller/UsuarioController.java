package com.microservices_esofii.criar_usuarios.controller;

import java.util.List;
import com.microservices_esofii.criar_usuarios.dto.UsuarioRequestDTO;
import com.microservices_esofii.criar_usuarios.dto.UsuarioResponseDTO;
import com.microservices_esofii.criar_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    /**
     * API para cadastrar um novo usuário no sistema.
     * Recebe os dados do usuário (nome e email) e cria um novo registro.
     * Valida se o email já não está em uso antes de cadastrar.
     * 
     * @param dto Dados do usuário a ser cadastrado (nome e email)
     * @return Dados do usuário cadastrado com ID e data de criação
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO response = service.cadastrarUsuario(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * API para listar todos os usuários cadastrados no sistema.
     * Retorna uma lista com ID, nome, email e data de criação de cada usuário.
     * 
     * @return Lista de todos os usuários cadastrados
     */
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = service.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
}
