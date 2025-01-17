package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String texto;

    @ManyToOne
    @ToString.Exclude
            @JoinColumn(name="id_tutorial_fk", nullable = false,referencedColumnName = "id", foreignKey = @ForeignKey(name="FK_TUTO"))//por defecto se relaciona con el id de la otra tabla
    Tutorial tutorial;
}
