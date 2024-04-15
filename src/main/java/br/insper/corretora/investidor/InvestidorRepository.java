package br.insper.corretora.investidor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestidorRepository extends JpaRepository<Investidor, Integer> {
}