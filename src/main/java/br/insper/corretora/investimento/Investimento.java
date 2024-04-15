package br.insper.corretora.investimento;

import java.util.Date;

import br.insper.corretora.investidor.Investidor;
import br.insper.corretora.titulo.Titulo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_investidor")
    private Investidor investidor;
    @ManyToOne
    @JoinColumn(name = "id_titulo")
    private Titulo titulo;
    @Column(nullable = false)
    private double valor;
    private Date data = new Date();

    public Investimento(){

    }

    public Investimento(double valor){
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Investidor getInvestidor(){
        return this.investidor;
    }

    public void setInvestidor(Investidor investidor){
        this.investidor = investidor;
    }

    public Titulo getTitulo(){
        return this.titulo;
    }

    public void setTitulo(Titulo titulo){
        this.titulo = titulo;
    }

    public double getValor(){
        return this.valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public Date getData(){
        return this.data;
    }
}
