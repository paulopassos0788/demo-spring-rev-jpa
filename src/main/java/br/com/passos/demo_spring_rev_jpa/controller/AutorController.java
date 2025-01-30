package br.com.passos.demo_spring_rev_jpa.controller;

import br.com.passos.demo_spring_rev_jpa.dao.AutorDao;
import br.com.passos.demo_spring_rev_jpa.entity.Autor;
import br.com.passos.demo_spring_rev_jpa.entity.InfoAutor;
import br.com.passos.demo_spring_rev_jpa.projection.AutorInfoProjection;
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

    @GetMapping("nomeOrSobrenome")
    public ResponseEntity<List<Autor>> findAllNomeOrSobrenome(@RequestParam String termo) {
        List<Autor> listaAutores = autorDao.findAllNomeOrSobrenome(termo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaAutores);
    }

    @GetMapping("total")
    public ResponseEntity<Long> getTotalElementos() {
        Long total = autorDao.getTotalElementos();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(total);
    }

    @PutMapping("{id}/info")
    public ResponseEntity<Autor> salvaInfAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        Autor autor = autorDao.salvaInfAutor(infoAutor, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(autor);
    }

    @GetMapping("info")
    public ResponseEntity<List<Autor>> findByCargo(@RequestParam String cargo) {
        List<Autor> listaAutores = autorDao.findByCargo(cargo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaAutores);
    }

    @GetMapping("autor-info")
    public ResponseEntity<AutorInfoProjection> findAutorInfoById(@RequestParam Long id){
        AutorInfoProjection autorInfoProjection = autorDao.findAutorInfoById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(autorInfoProjection);
    }
}
