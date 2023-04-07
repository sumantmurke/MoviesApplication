package com.bill.MoviesApplication.Controller

import com.bill.MoviesApplication.Model.Movie
import com.bill.MoviesApplication.Service.MovieService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1/movies")
@RestController
class MovieController(val service: MovieService) {

    @GetMapping("/hello")
    fun getHello() = "Hello shreya"

    @GetMapping
    fun getAllPlayers() = service.getAll()

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlayer(@RequestBody movie: Movie): Movie = service.create(movie)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlayer(@PathVariable id: Int) = service.remove(id)

    /*
    @PutMapping("/{id}")
    fun updatePlayer(
        @PathVariable id: Long, @RequestBody movie: Movie
    ) = service.update(id, movie) */
}