package br.com.passos.demo_spring_rev_jpa.dao;

import br.com.passos.demo_spring_rev_jpa.entity.Autor;
import br.com.passos.demo_spring_rev_jpa.entity.InfoAutor;
import br.com.passos.demo_spring_rev_jpa.projection.AutorInfoProjection;
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

    @Transactional(readOnly = true)
    public Long getTotalElementos() {
        String query = "select count(1) from Autor a";
        Long total = this.entityManager
                .createQuery(query, Long.class)
                .getSingleResult();
        return total;
    }

    @Transactional(readOnly = false)
    public Autor salvaInfAutor(InfoAutor infoAutor, Long autorId) {
        Autor autor = findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo) {
        String query = """
                select a from Autor a
                where a.infoAutor.cargo like :cargo
                order by a.nome asc
                """;
        List<Autor> result = this.entityManager
                .createQuery(query, Autor.class)
                .setParameter("cargo", "%" + cargo + "%")
                .getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id) {
        String query = """
                select new AutorInfoProjection(a.nome, a.sobrenome, a.infoAutor.cargo, a.infoAutor.bio)
                from Autor a
                where a.id = :id
                """;
        return this.entityManager
                .createQuery(query, AutorInfoProjection.class)
                .setParameter("id", id)
                .getSingleResult();
    }



}
