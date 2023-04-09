package com.bill.MoviesApplication.Model

import jakarta.persistence.*

/**
 * @author  Sumant Murke (sumantmurke)
 * @version 1.0
 */

@Entity
@Table(name = "actors")
data class Actor(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Int,

    @Column(unique = true)
    val name: String
)
