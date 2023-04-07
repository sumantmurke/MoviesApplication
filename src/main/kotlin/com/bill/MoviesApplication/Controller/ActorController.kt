package com.bill.MoviesApplication.Controller

import com.bill.MoviesApplication.Model.Actor
import com.bill.MoviesApplication.Service.ActorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1/actors")
@RestController
class ActorController(val service: ActorService) {

    @GetMapping("/helloactor")
    fun getHello() = "Hello actor"

    @GetMapping
    fun getAllPlayers() = service.getAll()

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlayer(@RequestBody actor: Actor): Actor = service.create(actor)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlayer(@PathVariable id: Int) = service.remove(id)

    /*
    @PutMapping("/{id}")
    fun updatePlayer(
        @PathVariable id: Long, @RequestBody movie: Movie
    ) = service.update(id, movie) */
}