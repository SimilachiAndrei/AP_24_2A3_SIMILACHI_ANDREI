package com.Server.Controller;

import com.Server.Model.Genre;
import com.Server.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("/genres")
    public Genre postGenre(@RequestBody Genre genre) {
        return genreService.insertGenre(genre);
    }

}
