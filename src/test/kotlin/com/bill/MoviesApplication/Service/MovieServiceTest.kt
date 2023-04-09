package com.bill.MoviesApplication.Service

import com.bill.MoviesApplication.Model.Actor
import com.bill.MoviesApplication.Model.Movie
import com.bill.MoviesApplication.Repository.ActorRepository
import com.bill.MoviesApplication.Repository.MovieRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

class MovieServiceTest {

    private val repository = Mockito.mock(MovieRepository::class.java)
    private val service = MovieService(repository)


    private lateinit var movieList: List<Movie>

    @BeforeEach
    fun setUp() {
        movieList = listOf(
            Movie(1, "2010","Inception", actors = listOf(Actor(id = 1, name = "Tom"))),
            Movie(2, "2008", "The Dark Knight" , actors = listOf(Actor(id = 2, name = "Eva")))
        )
    }
    @Test
    fun getAll() {
        `when`(repository.findAll()).thenReturn(movieList)
        val result = service.getAll()

        assertEquals(movieList, result)
    }

    @Test
    fun create() {
        var newMovie = Movie(1, "2010","Inception", actors = listOf(Actor(id = 1, name = "Tom")))

        // Successfully creating a movie
        `when`(repository.save(newMovie)).thenReturn(newMovie)
        val result1 = service.create(newMovie)

        // cannot create same movie twice
        `when`(repository.save(movieList[0])).thenThrow(org.springframework.dao.DataIntegrityViolationException::class.java)
        val result2 = assertThrows(ResponseStatusException::class.java) { service.create(movieList[0]) }
        assertEquals(newMovie, result1)
       assertEquals(HttpStatus.CONFLICT, result2.statusCode)
    }

    @Test
    fun remove() {

        `when`(repository.existsById(1)).thenReturn(true)
        `when`(repository.existsById(5)).thenReturn(false)

        // Successfully removing a movie
        service.remove(1)

        // Exception when removing if the id is not present
        assertThrows(ResponseStatusException::class.java) { service.remove(5) }

    }

    @Test
    fun update() {
        val updatedMovie = Movie(2, "2008", "The Dark Knight" , actors = listOf(Actor(id = 2, name = "Eva")))
        `when`(repository.existsById(2)).thenReturn(true)
        `when`(repository.existsById(5)).thenReturn(false)
        `when`(repository.save(updatedMovie)).thenReturn(updatedMovie)

        val result1 = service.update(2, updatedMovie)
        val result2 = assertThrows(ResponseStatusException::class.java) { service.update(5, updatedMovie) }

        assertEquals(updatedMovie, result1)
        assertEquals(HttpStatus.NOT_FOUND, result2.statusCode)
    }
}