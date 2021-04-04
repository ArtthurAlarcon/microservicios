package com.innova.movies.serie.service;

import com.innova.movies.serie.entity.Serie;
import com.innova.movies.serie.repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieServiceImplement implements SerieService{

    //@Autowired
    private final SerieRepository serieRepository;

    @Override
    public List<Serie> listAllSeries() {
        return serieRepository.findAll();

    }

    @Override
    public Serie getSerie(Long id) {
        return serieRepository.findById(id).orElse(null);
    }

    @Override
    public Serie createSerie(Serie serie) {
        // serie.setFecha(new Date());
        return serieRepository.save(serie);
    }

    @Override
    public Serie updateSerie(Serie serie) {
        Serie serieDB = getSerie(serie.getId());
        if (serieDB == null){
            return null;
        }
        serieDB.setNombre(serie.getNombre());
        serieDB.setGenero(serie.getGenero());
        serieDB.setImagen(serie.getImagen());
        serieDB.setLink(serie.getLink());
        serieDB.setFecha(serie.getFecha());

        return serieRepository.save(serieDB);
    }

    @Override
    public Serie deleteSerie(Long id) {
        Serie serieDB = getSerie(id);
        if (serieDB == null){
            return null;
        }
        serieDB.setNombre("Eliminado - " +serieDB.getNombre());
        serieDB.setGenero("Eliminado");
        serieDB.setLink("Eliminado");
        serieDB.setImagen("https://www.nicepng.com/png/detail/963-9636532_you-died-bmo-adventure-time-beemo.png");
        serieDB.setFecha(new Date());
        return serieRepository.save(serieDB);
    }

    @Override
    public List<Serie> findByGenero(String genero) {
        return serieRepository.findByGenero(genero);
    }

    @Override
    public List<Serie> findByLink(String link) {
        return serieRepository.findByLink(link);
    }

    @Override
    public List<Serie> findByGeneroNot(String genero) {
        genero = "Eliminado";
        return serieRepository.findByGeneroNot(genero);
    }

}
