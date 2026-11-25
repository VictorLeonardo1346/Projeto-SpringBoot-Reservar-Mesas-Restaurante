package com.restaurante.reservar_mesas.controller;

import com.restaurante.reservar_mesas.dtos.AgendamentoRequestDTO;
import com.restaurante.reservar_mesas.infrastructure.entity.AgendamentoEntity;
import com.restaurante.reservar_mesas.services.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;


    @PostMapping
    public ResponseEntity<AgendamentoEntity.Agendamento> salvarAgendamento(
            @Valid @RequestBody AgendamentoRequestDTO dto) {

        AgendamentoEntity.Agendamento agendamento =
                new AgendamentoEntity().new Agendamento();

        agendamento.setMesa(dto.getMesa());
        agendamento.setLugares(dto.getLugares());
        agendamento.setDataHoraAgendamento(dto.getDataHoraAgendamento());
        agendamento.setCliente(dto.getCliente());
        agendamento.setTelefoneCliente(dto.getTelefoneCliente());

        return ResponseEntity.accepted()
                .body(agendamentoService.salvarAgendamento(agendamento));
    }


    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(
            @RequestParam String cliente,
            @RequestParam LocalDateTime dataHoraAgendamento) {

        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoEntity.Agendamento>> buscarAgendamentosDia(
            @RequestParam LocalDate data) {

        return ResponseEntity.ok()
                .body(agendamentoService.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<AgendamentoEntity.Agendamento> alterarAgendamentos(
            @RequestBody AgendamentoEntity.Agendamento agendamento,
            @RequestParam String cliente,
            @RequestParam LocalDateTime dataHoraAgendamento) {

        return ResponseEntity.accepted()
                .body(agendamentoService.alterarAgendamento(
                        agendamento, cliente, dataHoraAgendamento));
    }
}
