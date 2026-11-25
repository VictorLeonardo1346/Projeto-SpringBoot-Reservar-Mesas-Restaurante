package com.restaurante.reservar_mesas.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class AgendamentoEntity {

    @Setter
    @Getter
    @NoArgsConstructor
    @Entity
    @Table(name = "agendamento")
    public class Agendamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String mesa;

        private int lugares;

        private LocalDateTime dataHoraAgendamento;

        private String cliente;

        private String TelefoneCliente;

        private LocalDateTime dataInsercao = LocalDateTime.now();


    }

}
