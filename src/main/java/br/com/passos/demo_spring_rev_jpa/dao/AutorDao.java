package br.com.passos.demo_spring_rev_jpa.dao;

import br.com.passos.demo_spring_rev_jpa.entity.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(Autor autor) {
        this.entityManager.persist(autor);
    }
}
