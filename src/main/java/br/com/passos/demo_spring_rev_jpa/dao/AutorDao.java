package br.com.passos.demo_spring_rev_jpa.dao;

import br.com.passos.demo_spring_rev_jpa.entity.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        String query = "select a from Autor a";
        List<Autor> listaAutores = this.entityManager.createQuery(query, Autor.class).getResultList();
        return listaAutores;
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllNomeOrSobrenome(String termo) {
        String query = "select a from Autor a where a.nome like :termo OR a.sobrenome like :termo";
        List<Autor> listaAutores = this.entityManager
                .createQuery(query, Autor.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
        return listaAutores;
    }
}
