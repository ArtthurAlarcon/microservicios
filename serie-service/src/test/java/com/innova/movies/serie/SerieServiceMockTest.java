package com.innova.movies.serie;

import com.innova.movies.serie.entity.Serie;
import com.innova.movies.serie.repository.SerieRepository;
import com.innova.movies.serie.service.SerieService;
import com.innova.movies.serie.service.SerieServiceImplement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class SerieServiceMockTest {

    @Mock
    private SerieRepository serieRepository;

    private SerieService serieService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        serieService = new SerieServiceImplement(serieRepository);
        Serie serie01 = Serie.builder()
                .id(1L)
                .nombre("Stranger Things")
                .genero("Ciencia ficcion")
                .imagen("https://images-na.ssl-images-amazon.com/images/I/71ErafeiG7L._AC_SY879_.jpg")
                .link("http://localhost:8080/Proyecto/cienciaficcion.html")
                .fecha(new Date())
                .build();

        Mockito.when(serieRepository.findById(1L))
                .thenReturn(Optional.of(serie01));
    }

    @Test
    public void whenValidGetId_ThenReturnSerie(){
        Serie found = serieService.getSerie(1L);
        Assertions.assertThat(found.getNombre()).isEqualTo("Stranger Things");
    }
}
