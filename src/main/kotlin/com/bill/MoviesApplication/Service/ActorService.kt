package com.bill.MoviesApplication.Service

import com.bill.MoviesApplication.Model.Actor
import com.bill.MoviesApplication.Model.Movie
import com.bill.MoviesApplication.Repository.ActorRepository
import com.bill.MoviesApplication.Repository.MovieRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ActorService(val repository: ActorRepository) {

    fun getAll(): List<Actor> = repository.findAll()

    fun getById(id: Int): Actor = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(actor : Actor): Actor {
        println("Adding  actor")
        try{
            repository.save(actor)
        }catch(e : java.sql.SQLIntegrityConstraintViolationException){
            throw ResponseStatusException(HttpStatus.CONFLICT)
        }
        return actor
    }

    fun remove(id: Int) {
        println("Removing id")
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}