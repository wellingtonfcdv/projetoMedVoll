package med.vol.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.vol.api.endereco.DadosEndereco;

//DTO para atualização dos dados
public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {

}
