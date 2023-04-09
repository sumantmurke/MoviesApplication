
package com.bill.MoviesApplication.Service

import com.bill.MoviesApplication.Model.Movie
import com.bill.MoviesApplication.Repository.MovieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

@Service
class MovieService(val repository: MovieRepository) {

    fun getAll(): List<Movie> = repository.findAll()

    fun getById(id: Int): Movie = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(movie: Movie): Movie{
        var resp: Movie? = null

        try{
            resp = repository.save(movie)
        }catch (e : org.springframework.dao.DataIntegrityViolationException){
            println(e.message)
            println(e.cause)
            println(e.rootCause)
            throw ResponseStatusException(HttpStatus.CONFLICT, "The movie you are trying to add is already present")
        }

        return resp!!
    }

    fun remove(id: Int) {

        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Int, movie: Movie): Movie {
        return if (repository.existsById(id)) {
            movie.id = id
            repository.save(movie)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}