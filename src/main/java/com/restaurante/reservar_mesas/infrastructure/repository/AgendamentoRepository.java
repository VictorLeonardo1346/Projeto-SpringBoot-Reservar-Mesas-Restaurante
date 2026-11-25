
package com.restaurante.reservar_mesas.infrastructure.repository;

import com.restaurante.reservar_mesas.infrastructure.entity.AgendamentoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity.Agendamento, Long> {

    AgendamentoEntity.Agendamento findByMesaAndDataHoraAgendamentoBetween(
            String mesa,
            LocalDateTime dataInicio,
            LocalDateTime dataFinal
    );

    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(
            LocalDateTime dataHoraAgendamento,
            String cliente
    );

    List<AgendamentoEntity.Agendamento> findByDataHoraAgendamentoBetween(
            LocalDateTime dataHoraInicial,
            LocalDateTime dataHoraFinal
    );

    AgendamentoEntity.Agendamento findByDataHoraAgendamentoAndCliente(
            LocalDateTime dataHoraAgendamento,
            String cliente
    );
}
