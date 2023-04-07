package com.bill.MoviesApplication.Repository

import com.bill.MoviesApplication.Model.Actor
import org.springframework.data.jpa.repository.JpaRepository

interface ActorRepository: JpaRepository<Actor, Int> {
}