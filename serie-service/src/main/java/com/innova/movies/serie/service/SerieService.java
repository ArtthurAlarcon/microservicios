package com.innova.movies.serie.service;

import com.innova.movies.serie.entity.Serie;

import java.util.List;

public interface SerieService {
    public List<Serie> listAllSeries();
    public Serie getSerie(Long id);
    public Serie createSerie(Serie serie);
    public Serie updateSerie(Serie serie);
    public Serie deleteSerie(Long id);
    public List<Serie> findByGenero(String genero);
    public List<Serie> findByLink(String link);
    public List<Serie> findByGeneroNot(String genero);
}
