package br.insper.corretora.investimento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<Investimento, Integer> {
}
