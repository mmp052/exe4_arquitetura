package br.insper.corretora.titulo;

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
public class TituloController {
    @Autowired
    private TituloService tituloService;

    @PostMapping("/titulo")
    public Titulo cadastrarTitulo(@RequestBody Titulo titulo){
        return tituloService.cadastrarTitulo(titulo);
    }

    @GetMapping("/titulo")
    public List<Titulo> listarTitulos(@RequestParam String tipo){
        return tituloService.listarTitulos(tipo);
    }

    @DeleteMapping("/titulo/{id}")
    public void deleteTitulo(@PathVariable Integer id){
        tituloService.deletarTitulo(id);
    }
}
