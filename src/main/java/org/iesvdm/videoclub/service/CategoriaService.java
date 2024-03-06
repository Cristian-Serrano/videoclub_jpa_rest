package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaCustomRepository;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaCustomRepository categoriaCustomRepository;

    public Categoria one(Long id){
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public List<Categoria> all(Optional<String> buscar, Optional<String> ordenar){
        return this.categoriaCustomRepository.queryCustomCategoria(buscar, ordenar);
    }

    public List<Categoria> all(){
        return this.categoriaRepository.findAll();
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Categoria save(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    public int numPeliculasPorCategoria(long idCategoria){
        int conteo = this.one(idCategoria).getPeliculas().size();
        this.one(idCategoria).setConteo(conteo);
        return conteo;
    }
}
