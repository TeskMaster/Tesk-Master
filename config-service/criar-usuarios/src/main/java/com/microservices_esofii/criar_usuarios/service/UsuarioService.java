package com.microservices_esofii.criar_usuarios.service;

import com.microservices_esofii.criar_usuarios.model.Usuario;
import com.microservices_esofii.criar_usuarios.dto.UsuarioResponseDTO;
import com.microservices_esofii.criar_usuarios.dto.UsuarioRequestDTO;
import com.microservices_esofii.criar_usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    /**
     * Registra um novo usuário com base no DTO de entrada e retorna um DTO de resposta.
     * Verifica se o email já está em uso antes de cadastrar.
     */
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        Optional<Usuario> existente = repository.findByEmail(dto.getEmail());

        if (existente.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado com este email.");
        }

        // Mapeamento manual do DTO para entidade
        Usuario novo = new Usuario();
        novo.setNome(dto.getNome());
        novo.setEmail(dto.getEmail());

        Usuario salvo = repository.save(novo);

        // Mapeamento manual da entidade para DTO de resposta
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(salvo.getId());
        response.setNome(salvo.getNome());
        response.setEmail(salvo.getEmail());
        response.setCriadoEm(salvo.getDataCriacao());

        return response;
    }

    /**
     * Retorna uma lista de todos os usuários cadastrados, mapeados para DTOs de resposta.
     */
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(usuario -> {
                    UsuarioResponseDTO dto = new UsuarioResponseDTO();
                    dto.setId(usuario.getId());
                    dto.setNome(usuario.getNome());
                    dto.setEmail(usuario.getEmail());
                    dto.setCriadoEm(usuario.getDataCriacao());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
