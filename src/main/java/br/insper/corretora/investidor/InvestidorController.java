package br.insper.corretora.investidor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestidorController {

    @Autowired
    private InvestidorService investidorService;

    @PostMapping("/investidor")
    public Investidor cadastrarInvestidor(@RequestBody Investidor investidor){
        return investidorService.cadastrarInvestidor(investidor);
    }

    @GetMapping("/investidor")
    public List<Investidor> listarInvestidores(@RequestParam String perfil){
        return investidorService.listarInvestidores(perfil);
    }

    @DeleteMapping("/investidor/{id}")
    public void deleteInvestidor(@PathVariable Integer id){
        investidorService.deletarInvestidor(id);
    }
}