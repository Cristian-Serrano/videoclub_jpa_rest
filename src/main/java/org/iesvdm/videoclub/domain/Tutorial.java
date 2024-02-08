package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "tutorials",
        schema = "videoclub_jpa",
        indexes = {@Index(name = "index_titulo", columnList = "titulo", unique = false)}
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "titulo", length = 50)
    private String titulo;

    @Column(name = "descrip", length = 150)
    private String descripcion;

    @Column(name = "publi")
    private Boolean publicado;

    @Column(nullable = true)
    private Date fechaPublicacion;

    @OneToMany( mappedBy = "tutorial", cascade = CascadeType.ALL)// por defecto fetch es lazy
    private Set<Comentario> comentarios;

    /**
     * helper
     * @param comentario
     * @return
     */
    public Tutorial addComentario(Comentario comentario){
        this.comentarios.add(comentario);
        comentario.setTutorial(this);
        return this;
    }

    public Tutorial removeComentario(Comentario comentario){
        this.comentarios.remove(comentario);
        comentario.setTutorial(null);
        return this;
    }
}
