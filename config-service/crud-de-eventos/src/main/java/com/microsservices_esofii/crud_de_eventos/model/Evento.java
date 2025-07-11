package com.microsservices_esofii.crud_de_eventos.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "evento")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
   private String titulo;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String descricao;


    @Column(name = "data_inicio",nullable = false)
    private LocalDate dataInicio;

    @Column(name = "horario_inicio",nullable = false)
    private LocalTime horarioInicio;

    @Column(name = "local_evento",nullable = false)
    private String localEvento;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate data) {
        this.dataInicio = data;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String local) {
        this.localEvento = local;
    }
}
