package com.bill.MoviesApplication.Controller

import com.bill.MoviesApplication.Model.Actor
import com.bill.MoviesApplication.Service.ActorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

@RequestMapping("api/v1/actors")
@RestController
class ActorController(val service: ActorService) {

    @GetMapping
    fun getAllActors() = service.getAll()

    @GetMapping("/{id}")
    fun getActor(@PathVariable id: Int) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveActor(@RequestBody actor: Actor): Actor = service.create(actor)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteActor(@PathVariable id: Int) = service.remove(id)


    @PutMapping("/{id}")
    fun updateActor(
        @PathVariable id: Int, @RequestBody actor: Actor
    ) = service.update(id, actor)
}


