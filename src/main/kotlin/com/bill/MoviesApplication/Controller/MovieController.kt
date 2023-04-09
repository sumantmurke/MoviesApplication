package com.bill.MoviesApplication.Controller

import com.bill.MoviesApplication.Model.Movie
import com.bill.MoviesApplication.Service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

@RequestMapping("api/v1/movies")
@RestController
class MovieController(val service: MovieService) {

    @GetMapping
    fun getAllMovies() = service.getAll()

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMovie(@RequestBody movie: Movie): Movie {

        if (movie.title.isNullOrEmpty() || movie.releaseDate.isNullOrEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Title and release data are required fields")
        }

        if(movie.actors.isEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie should have at least one actor")
        }

        return service.create(movie)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMovie(@PathVariable id: Int) = service.remove(id)


    @PutMapping("/{id}")
    fun updateMovie(
        @PathVariable id: Int, @RequestBody movie: Movie
    ) = service.update(id, movie)
}
