package com.innova.movies.serie.repository;

import com.innova.movies.serie.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    public List<Serie> findByGenero(String genero);
    public List<Serie> findByLink(String link);
    public List<Serie> findByGeneroNot(String genero);
}
