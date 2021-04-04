package com.innova.movies.serie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.movies.serie.entity.Serie;
import com.innova.movies.serie.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    /* SERVICIO PARA VER LAS SERIES EXISTENTES O CON PARAMETROS*/

    @GetMapping
    public ResponseEntity<List<Serie>> listSerie(@RequestParam(name = "genero", required = false) String genero, @RequestParam(name = "link", required = false) String link){
        List<Serie> series = new ArrayList<>();
        if (genero == null && link == null){
            genero = "Eliminado";
            series = serieService.findByGeneroNot(genero);
            if (series.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else if (genero != null) {
            series = serieService.findByGenero(genero);
            if (series.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }else if (link != null){
            series = serieService.findByLink(link);
            if (series.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(series);
    }

    /* SERVICIO PARA VER UNA SERIE ESPECIFICA POR EL ID*/

    @GetMapping(value = "/{id}")
    public ResponseEntity<Serie> getSerie(@PathVariable("id") Long id){
        Serie serie = serieService.getSerie(id);
        if (serie == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(serie);
    }


    /* SERVICIO PARA CREAR OTRA SERIES*/

    @PostMapping
    public ResponseEntity<Serie> createSerie(@Valid @RequestBody Serie serie, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Serie serieCreate = serieService.createSerie(serie);
        return ResponseEntity.status(HttpStatus.CREATED).body(serieCreate);
    }

    /* SERVICIO PARA ACTUALIZAR UNA SERIE*/

    @PutMapping(value = "/{id}")
    public  ResponseEntity<Serie> updateSerie(@PathVariable("id") Long id, @RequestBody Serie serie){
        serie.setId(id);
        Serie serieDB = serieService.updateSerie(serie);
        if (serieDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serieDB);
    }

    /* SERVICIO PARA BORRAR UNA SERIE EXISTENTES POR ID*/

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Serie> deleteSerie(@PathVariable("id") Long id){
        Serie serieDelete = serieService.deleteSerie(id);
        if (serieDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serieDelete);
    }

    /* SERVICIO PARA VER EL ERROR DE LAS VALIDACIONES*/

    private String formatMessage (BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }


}
