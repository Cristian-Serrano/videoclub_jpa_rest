package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.Comentario;
import org.iesvdm.videoclub.domain.Tutorial;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoclubApplicationTests {

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
                .build();

        Comentario comentario1 = Comentario.builder()
                .texto("Comentario1")
                .build();

        Comentario comentario2 = Comentario.builder()
                .texto("Comentario2")
                .build();

        tutorial.addComentario(comentario1).addComentario(comentario2);

        tutorialRepository.save(tutorial);
    }

}
