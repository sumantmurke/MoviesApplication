package com.bill.MoviesApplication.Repository

import com.bill.MoviesApplication.Model.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Int>