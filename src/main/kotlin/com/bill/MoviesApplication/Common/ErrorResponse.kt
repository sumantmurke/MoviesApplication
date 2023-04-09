package com.bill.MoviesApplication.Common

import org.springframework.http.HttpStatusCode

data class ErrorResponse(val message: String, val statusCode : HttpStatusCode)
