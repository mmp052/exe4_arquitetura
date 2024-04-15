package br.insper.corretora.titulo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.insper.corretora.investimento.InvestimentoService;

@Service
public class TituloService {
    @Autowired
    private TituloRepository tituloRepository;

    @Autowired
    private InvestimentoService investimentoService;

    public Titulo cadastrarTitulo(Titulo titulo){
        return tituloRepository.save(titulo);
    }

    public List<Titulo> listarTitulos(String tipo){
        List<Titulo> titulos = tituloRepository.findAll();
        if(tipo != null){
            ArrayList<Titulo> lista = new ArrayList<>();

            for(Titulo titulo : titulos){
                if(titulo.getTipo().equals(tipo)){
                    lista.add(titulo);
                }
            }

            return lista;
        }

        return titulos;
    }

    public Titulo getTitulo(Integer id){
        return tituloRepository.getReferenceById(id);
    }

    public void deletarTitulo(Integer id){
        Titulo titulo = this.getTitulo(id);

        if (investimentoService.tituloExiste(titulo)){
            throw new RuntimeException("titulo não pode ser deletado");
        }

        if(titulo == null){
            throw new RuntimeException("titulo não encontrado");
        }

        tituloRepository.deleteById(id);
    }
}
