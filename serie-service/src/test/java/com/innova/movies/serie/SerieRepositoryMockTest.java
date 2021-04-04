package com.innova.movies.serie;

import com.innova.movies.serie.entity.Serie;
import com.innova.movies.serie.repository.SerieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class SerieRepositoryMockTest {

    @Autowired
    private SerieRepository serieRepository;

    @Test
    public void whenFindByGenero_thenReturnListSerie(){
        Serie serie01 = Serie.builder()
                .nombre("Stranger Things")
                .genero("Ciencia ficcion")
                .imagen("https://images-na.ssl-images-amazon.com/images/I/71ErafeiG7L._AC_SY879_.jpg")
                .link("http://localhost:8080/Proyecto/cienciaficcion.html")
                .fecha(new Date())
                .build();
        serieRepository.save(serie01);

        List<Serie> founds=serieRepository.findByGenero(serie01.getGenero());

        Assertions.assertThat(founds.size()).isEqualTo(2);
    }

}
