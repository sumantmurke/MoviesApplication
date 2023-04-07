package com.bill.MoviesApplication.Model

import jakarta.persistence.*


@Entity
@Table(name = "movies")
data class Movie(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Int,
    val releaseDate: String,
    val title: String,

    @ManyToMany
    @JoinTable(
    name = "movies_actors",
    joinColumns = [JoinColumn(name = "movie_id")],
    inverseJoinColumns = [JoinColumn(name = "actor_id")]
    )
    val actors: List<Actor>
)
