package com.bill.MoviesApplication.Repository

import com.bill.MoviesApplication.Model.Actor
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

interface ActorRepository: JpaRepository<Actor, Int> {
}