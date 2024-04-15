package br.insper.corretora.investimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestimentoController {
    @Autowired
    private InvestimentoService investimentoService;

    @PostMapping("/investimento")
    public Investimento cadastrarInvestimento(@RequestBody Investimento investimento){
        return investimentoService.cadastrarInvestimento(investimento);
    }

    @GetMapping("/investimento")
    public List<Investimento> listarInvestimentos(@RequestParam String cpf){
        return investimentoService.listarInvestimentos(cpf);
    }
}
