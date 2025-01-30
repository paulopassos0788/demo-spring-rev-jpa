package br.com.passos.demo_spring_rev_jpa.controller;

import br.com.passos.demo_spring_rev_jpa.dao.AutorDao;
import br.com.passos.demo_spring_rev_jpa.entity.Autor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorDao autorDao;

    public AutorController(AutorDao autorDao) {
        this.autorDao = autorDao;
    }

    @PostMapping
    public ResponseEntity<Autor> salvar(@RequestBody Autor autor) {
       autorDao.salvar(autor);
       return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    @PutMapping
    public ResponseEntity<Autor> update(@RequestBody Autor autor) {
        autorDao.update(autor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(autor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorDao.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> findById(@PathVariable Long id) {
        Autor autor = autorDao.findById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(autor);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> findAll() {
        List<Autor> listaAutores = autorDao.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaAutores);
    }
}
