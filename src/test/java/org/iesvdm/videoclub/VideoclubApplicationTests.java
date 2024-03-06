package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Comentario;
import org.iesvdm.videoclub.domain.Tutorial;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.iesvdm.videoclub.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    TutorialRepository tutorialRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    void pruebaOneToManyTutorial(){
        var tutorialList = tutorialRepository.findAll();

        tutorialList.forEach(tutorial -> System.out.println(tutorial));
    }

    @Test
    void pruebaGrabarOneToMany(){
        Tutorial tutorial = Tutorial.builder()
                .titulo("titulo del tutorial")
                .publicado(true)
                .descripcion("descripcion del tutorial")
                .fechaPublicacion(new Date())
                .comentarios(new HashSet<>())
                .build();

        Comentario comentario1 = Comentario.builder()
                .texto("Comentario1")
                .build();

        Comentario comentario2 = Comentario.builder()
                .texto("Comentario2")
                .build();

        tutorial.addComentario(comentario1);

        tutorialRepository.save(tutorial);

        tutorial.addComentario(comentario2);

        tutorialRepository.save(tutorial);
    }

    @Test
    @Transactional
    public void pruebaEliminarComentario(){
        Optional<Tutorial> optionalTutorial = this.tutorialRepository.findById(1L);

        optionalTutorial.ifPresent(tutorial -> {
            tutorial
                    .getComentarios()
                    .forEach(System.out::println);

            var optionalComentario = tutorial.getComentarios().stream().findFirst();

            tutorial.removeComentario(optionalComentario.get());

            this.tutorialRepository.save(tutorial);
        });

        //this.tutorialRepository.delete(optionalTutorial.get());

        tutorialRepository.flush();
    }

    @Test
    public void crearCategoria(){
        Categoria categoria1 = new Categoria(1,"categoria5",new HashSet<>(),new Date(),0);
        categoriaService.save(categoria1);

        Categoria categoria2 = new Categoria(2,"categoria6",new HashSet<>(),new Date(),0);
        categoriaService.save(categoria2);
    }
}
