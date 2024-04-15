package br.insper.corretora.investidor;

import org.springframework.stereotype.Service;

import br.insper.corretora.investimento.InvestimentoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InvestidorService {
    @Autowired
    private InvestidorRepository investidorRepository;

    @Autowired
    private InvestimentoService investimentoService;

    public Investidor cadastrarInvestidor(Investidor investidor){
        return investidorRepository.save(investidor);
    }

    public List<Investidor> listarInvestidores(String perfil){
        List<Investidor> investidores = investidorRepository.findAll();
        if(perfil != null){
            ArrayList<Investidor> lista = new ArrayList<>();

            for(Investidor investidor : investidores){
                if(investidor.getPerfil().equals(perfil)){
                    lista.add(investidor);
                }
            }

            return lista;
        }

        return investidores;
    }

    public Investidor getInvestidor(Integer id){
        return investidorRepository.getReferenceById(id);
    }

    public Investidor getInvestidor(String cpf){
        List<Investidor> investidores = investidorRepository.findAll();
        
        for(Investidor investidor : investidores){
            if(investidor.getCpf().equals(cpf)){
                return investidor;
            }
        }

        return null;
    }

    public void deletarInvestidor(Integer id){
        Investidor investidor = this.getInvestidor(id);

        if (investimentoService.investidorExiste(investidor)){
            throw new RuntimeException("investidor não pode ser deletado");
        }

        if(investidor == null){
            throw new RuntimeException("investidor não encontrado");
        }

        investidorRepository.deleteById(id);
    }
}
