package one.digitalinnovation.gof.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String cep;


}
