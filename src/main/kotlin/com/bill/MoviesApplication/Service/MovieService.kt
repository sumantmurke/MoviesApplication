package com.bill.MoviesApplication.Service

import com.bill.MoviesApplication.Model.Movie
import com.bill.MoviesApplication.Repository.MovieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MovieService(val repository: MovieRepository) {

    fun getAll(): List<Movie> = repository.findAll()

    fun getById(id: Int): Movie = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(movie: Movie): Movie = repository.save(movie)

    fun remove(id: Int) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}