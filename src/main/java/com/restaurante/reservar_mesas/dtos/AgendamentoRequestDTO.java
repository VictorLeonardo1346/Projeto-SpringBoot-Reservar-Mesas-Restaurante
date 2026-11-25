
package com.restaurante.reservar_mesas.dtos;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoRequestDTO {

    @NotBlank(message = "A mesa é obrigatória")
    private String mesa;

    @Positive(message = "A quantidade de lugares deve ser maior que zero")
    private int lugares;

    @NotNull(message = "A data e horário são obrigatórios")
    private LocalDateTime dataHoraAgendamento;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String cliente;

    @NotBlank(message = "O telefone do cliente é obrigatório")
    private String telefoneCliente;
}
