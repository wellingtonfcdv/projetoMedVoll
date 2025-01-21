package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
        //System.out.println("Dados recebidos: " + dados);
    }

    //@PageableDefaul: Define a forma que será exibida a consulta
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort={"nome"}) Pageable paginacao){
        // findAllByAtivoTrue: para retornar somente os médicos que estão ativos
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){

        //carregar os dados do banco de dados
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        //repository.deleteById(id);

        //Recupera os dados pelo id
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
