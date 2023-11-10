package com.example.project8

class Movie {
    val Poster: String
    val Title: String
    val Year: String
    val Rating: String
    val Runtime: String
    val Genre: String
    val ImdbRating: String
    val ImdbLink: String

    constructor(Poster: String, Title: String, Year: String, Rating: String, Runtime: String, Genre: String, ImdbRating: String, ImdbID: String){
        this.Poster = Poster
        this.Title = Title
        this.Year = Year
        this.Rating = Rating
        this.Runtime = Runtime
        this.Genre = Genre
        this.ImdbRating = ImdbRating
        this.ImdbLink = "https://www.imdb.com/title/$ImdbID/"
    }
}