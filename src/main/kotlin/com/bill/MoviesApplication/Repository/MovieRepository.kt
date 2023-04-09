package com.bill.MoviesApplication.Repository

import com.bill.MoviesApplication.Model.Movie
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

interface MovieRepository : JpaRepository<Movie, Int>