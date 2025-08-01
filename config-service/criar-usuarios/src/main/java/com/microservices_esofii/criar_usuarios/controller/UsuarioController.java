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
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO response = service.cadastrarUsuario(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = service.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
}
