package br.com.passos.demo_spring_rev_jpa.dao;

import br.com.passos.demo_spring_rev_jpa.entity.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public void salvar(Autor autor) {
        this.entityManager.persist(autor);
    }

    @Transactional(readOnly = false)
    public void update(Autor autor) {
        this.entityManager.merge(autor);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.entityManager.remove(this.entityManager.getReference(Autor.class, id));
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        return this.entityManager.find(Autor.class, id);
    }
}
