package com.bill.MoviesApplication.Service

import com.bill.MoviesApplication.Model.Actor
import com.bill.MoviesApplication.Repository.ActorRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

class ActorServiceTest {

    private val repository = mock(ActorRepository::class.java)
    private val service = ActorService(repository)

    @Test
    fun getAll() {
        val actors = listOf(Actor(1, "Tom Hanks"), Actor(2, "Meryl Streep"))
        `when`(repository.findAll()).thenReturn(actors)

        val result = service.getAll()

        assertEquals(actors, result)
        verify(repository, times(1)).findAll()
    }

    @Test
    fun getById() {
        val actor = Actor(1, "Tom")
        given(repository.findByIdOrNull(actor.id)).willReturn(actor)

        // when
        val result = service.getById(actor.id)

        // then
        assertEquals(actor, result)
    }

    @Test
    fun create() {
        val actor = Actor(1, "Tom")
        given(repository.save(actor)).willReturn(actor)

        val result = service.create(actor)
        assertEquals(actor, result)

        // when you try to add same actor again
        given(repository.save(actor)).willThrow(DataIntegrityViolationException::class.java)
        assertThrows(ResponseStatusException::class.java) {
            service.create(actor)
        }
    }

    @Test
    fun remove() {
        val id = 1
        given(repository.existsById(id)).willReturn(true)
        service.remove(id)
        verify(repository).deleteById(id)

        // trying to remove actor that doesnt exist
        given(repository.existsById(id)).willReturn(false)
        assertThrows(ResponseStatusException::class.java) {
            service.remove(id)
        }

        // removing actor that is part of another movie
        given(repository.existsById(id)).willReturn(true)
        given(repository.deleteById(id)).willThrow(DataIntegrityViolationException::class.java)

        // when and then
        assertThrows(ResponseStatusException::class.java) {
            service.remove(id)
        }
    }

    @Test
    fun update() {
        val actor = Actor(1, "Tom")

        `when`(repository.existsById(actor.id)).thenReturn(true)
        `when`(repository.save(actor)).thenReturn(actor)

        val updatedActor = service.update(actor.id, actor)

        assertEquals(actor, updatedActor)

        // update should throw ResponseStatusException with NOT_FOUND when actor does not exist
        `when`(repository.existsById(actor.id)).thenReturn(false)

        assertThrows(ResponseStatusException::class.java) { service.update(actor.id, actor) }
            .also { assertEquals(HttpStatus.NOT_FOUND, it.statusCode) }
    }
}