package org.iesvdm.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaCustomRepositoryJPQLImpl implements CategoriaCustomRepository{
    @Autowired
    private EntityManager em;

    @Override
    public List<Categoria> queryCustomCategoría(Optional<String> buscarOptional, Optional<String> ordenarOptional){
        String query = "SELECT * FROM categoria";
        if(buscarOptional.isPresent()){
            query += "WHERE nombre like :nombre";
        }
        if(ordenarOptional.isPresent() && buscarOptional.isPresent()){
            if ("asc".equalsIgnoreCase(buscarOptional.get())){
                query += "ORDER BY nombre ASC";
            } else if ("desc".equalsIgnoreCase(buscarOptional.get())) {
                query += "ORDER BY nombre DESC";
            }
        }

        Query queryQuery = em.createNativeQuery(query, Categoria.class);
        if (buscarOptional.isPresent()) {
            queryQuery.setParameter("nombre", "%"+buscarOptional.get()+"%");
        }
        return queryQuery.getResultList();
    }
}
