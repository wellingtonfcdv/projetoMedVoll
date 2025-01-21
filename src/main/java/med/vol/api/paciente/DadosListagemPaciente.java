package med.vol.api.paciente;

public record DadosListagemPaciente(Long id, Boolean ativo,String nome, String email, String cpf, String telefone) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getAtivo(),paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone());
    }
}
