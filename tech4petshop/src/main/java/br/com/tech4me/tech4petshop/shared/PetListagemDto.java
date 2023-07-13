package br.com.tech4me.tech4petshop.shared;

import java.util.List;

import br.com.tech4me.tech4petshop.model.Vacinado;

public record PetListagemDto(String id,
@NotEmpty(message = "Digite o nome do seu pet.")
String nome,
@NotEmpty(message = "Qual é a raça do seu pet?")
String raca,
@Positive(message = "Digite o ano de nascimento do seu pet.")
int anoNascimento,
@NotNull(message = "Seu pet é vacinado? TRUE ou FALSE.")
Vacinado vacinado,
@NotBlank(message = "Qual o procedimento do seu pet?")
List<String> procedimentos) {
    
}
