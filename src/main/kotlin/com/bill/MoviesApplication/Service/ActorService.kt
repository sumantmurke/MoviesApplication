package com.bill.MoviesApplication.Service

import com.bill.MoviesApplication.Model.Actor
import com.bill.MoviesApplication.Repository.ActorRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ActorService(val repository: ActorRepository) {

    fun getAll(): List<Actor> = repository.findAll()

    fun getById(id: Int): Actor = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(actor : Actor): Actor {

        try{
            repository.save(actor)
        }catch(e : org.springframework.dao.DataIntegrityViolationException){
            throw ResponseStatusException(HttpStatus.CONFLICT, "The actor you are trying to add is already present")
        }
        return actor
    }

    fun remove(id: Int) {

        if (repository.existsById(id)) {
            try{
                repository.deleteById(id)
            }catch (e : org.springframework.dao.DataIntegrityViolationException){
                throw ResponseStatusException(HttpStatus.CONFLICT, "The actor you are trying to delete is already part of another movie")
            }

        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Int, actor: Actor): Actor {
        return if (repository.existsById(id)) {
            actor.id = id
            repository.save(actor)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}