package br.insper.corretora.investimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.insper.corretora.investidor.Investidor;
import br.insper.corretora.investidor.InvestidorService;
import br.insper.corretora.titulo.Titulo;
import br.insper.corretora.titulo.TituloService;

@Service
public class InvestimentoService {
    @Autowired
    private InvestimentoRepository InvestimentoRepository;

    @Autowired
    private InvestidorService investidorService;

    @Autowired
    private TituloService tituloService;

    public Investimento cadastrarInvestimento(Investimento investimento){
        Investidor investidor = investidorService.getInvestidor(investimento.getInvestidor().getId());
        Titulo titulo = tituloService.getTitulo(investimento.getTitulo().getId());

        if (investidor == null ||  titulo == null) {
            throw new RuntimeException("investidor ou titulo não encotrado");
        }

        if (investidor.getPerfil().equals("CONSERVADOR") & titulo.getTipo().equals("ação")){
            throw new RuntimeException("voce não pode investir em ação");
        }

        if (investidor.getPerfil().equals("MODERADO") & titulo.getTipo().equals("ação")){
            List<Investimento> investimentos = this.listarInvestimentos(investidor.getCpf());
            double total = 0;
            double totalAcoes = 0;
            for(Investimento investimento2 : investimentos){
                total = total + investimento2.getValor();
                if (investimento2.getTitulo().getTipo().equals("ação")){
                    totalAcoes = totalAcoes + investimento2.getValor();
                }
            }

            if (totalAcoes / total > 0.5){
                throw new RuntimeException("voce não pode investir em ação");
            }
        }

        investimento.setInvestidor(investidor);
        investimento.setTitulo(titulo);

        return InvestimentoRepository.save(investimento);
    }

    public List<Investimento> listarInvestimentos(String cpf){

        Investidor investidor = investidorService.getInvestidor(cpf);

        if(investidor == null){
            throw new RuntimeException("investidor não encontrado");
        }

        List<Investimento> investimentos = InvestimentoRepository.findAll();
        ArrayList<Investimento> lista = new ArrayList<>();

        for(Investimento investimento : investimentos){
            if(investimento.getInvestidor().getCpf().equals(cpf)){
                lista.add(investimento);
            }
        }

        return lista;
    }

    public boolean tituloExiste(Titulo titulo){

        List<Investimento> investimentos = InvestimentoRepository.findAll();

        for(Investimento investimento : investimentos){
            if(investimento.getTitulo().getIdentificador().equals(titulo.getIdentificador())){
                return true;
            }
        }

        return false;
    }

    public boolean investidorExiste(Investidor investidor){

        List<Investimento> investimentos = InvestimentoRepository.findAll();

        for(Investimento investimento : investimentos){
            if(investimento.getInvestidor().getCpf().equals(investidor.getCpf())){
                return true;
            }
        }

        return false;
    }
}
