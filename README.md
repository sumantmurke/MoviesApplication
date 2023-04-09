# MoviesApplication
This repository is a basic web service that uses following technologies 
  1. Spring framework
  2. Kotlin
  3. Mysql for databses 

In this simple web services we do CRUD operations on Movies and Actors associated to it.

This web service covers below functionalities 
  1. It ensure that actors and movies are individually unique.
  2. An individual actor will not appear more than once in a movie.
  3. Movie title and release date are required and the combination of these two fields must be unique.
  4. Actor names must be unique.
  5. Each movie must have at least one actor.
  6. The list of actors must not be duplicated for the same movie.
  7. An Actor cannot be deleted if they are a part of any movie(s).

In order to set up the database please find a sql dump (Bill.sql)
