
package com.restaurante.reservar_mesas.services;

import com.restaurante.reservar_mesas.infrastructure.entity.AgendamentoEntity;
import com.restaurante.reservar_mesas.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoEntity.Agendamento salvarAgendamento(
            AgendamentoEntity.Agendamento agendamento) {

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = horaAgendamento.plusMinutes(1);

        AgendamentoEntity.Agendamento agendados =
                agendamentoRepository.findByMesaAndDataHoraAgendamentoBetween(
                        agendamento.getMesa(),
                        horaAgendamento,
                        horaFim
                );

        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("Reserva já está preenchida");
        }

        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(
            LocalDateTime dataHoraAgendamento, String cliente) {

        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(
                dataHoraAgendamento, cliente);
    }

    public List<AgendamentoEntity.Agendamento> buscarAgendamentosDia(LocalDate data) {

        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(
                primeiraHoraDia, horaFinalDia);
    }

    public AgendamentoEntity.Agendamento alterarAgendamento(
            AgendamentoEntity.Agendamento agendamento,
            String cliente,
            LocalDateTime dataHoraAgendamento) {

        AgendamentoEntity.Agendamento agenda =
                agendamentoRepository.findByDataHoraAgendamentoAndCliente(
                        dataHoraAgendamento, cliente);

        if (Objects.isNull(agenda)) {
            throw new RuntimeException("Reserva não está preenchida");
        }

        agendamento.setId(agenda.getId());

        return agendamentoRepository.save(agendamento);
    }
}
