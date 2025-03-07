package br.unitins.tp1.repository;

import br.unitins.tp1.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public Estado findBySigla(String sigla) {
        return find("sigla", sigla).firstResult();
    }

    public List<Estado> findByNome(String nome) {
        return list("nome", nome);
    }
}