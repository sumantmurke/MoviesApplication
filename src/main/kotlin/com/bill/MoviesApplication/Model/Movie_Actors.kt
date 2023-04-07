package com.bill.MoviesApplication.Model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "movie_actors")
data class Movie_Actors(
    @Id
    @GeneratedValue
    val id: Int,

    val movie_id: Int,
    val actor_id: Int,
)
