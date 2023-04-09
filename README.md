# Movie Application
This repository is a basic web service that uses following technologies 
  1. Spring framework
  2. Kotlin
  3. Mysql for databse
  4. maven
  5. Java 7 or later 

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

**Steps to run the project **
 1. Make sure you have all the technologies listed above. 
 2. clone the repo. 
 3. Please create the scheama for the mysql that is part of this repo (Bill.sql).
 4. run mvn compile (to compile and download all the dependecies).
 5. to run the web service please run "mvn spring-boot:run"

