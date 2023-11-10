package com.example.project8

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var omdbApiClient: OMDbApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<Button>(R.id.search_button)
        val searchBar = findViewById<EditText>(R.id.search_bar)

        val movieImageView = findViewById<ImageView>(R.id.poster_image_view)
        val movieTitleView = findViewById<TextView>(R.id.title_text_view)
        val movieYearView =  findViewById<TextView>(R.id.year_text_view)
        val movieRatingView =  findViewById<TextView>(R.id.rating_text_view)
        val movieRunTimeView = findViewById<TextView>(R.id.running_time_text_view)
        val movieGenreView =  findViewById<TextView>(R.id.genre_text_view)
        val imdbRatingView =  findViewById<TextView>(R.id.imdb_rating_text_view)
        val imdbLinkView =  findViewById<TextView>(R.id.imdb_link_text_view)

        val shareButton = findViewById<Button>(R.id.share_button)

        val omdbApiClient = OMDbApiClient.getOMDbApi()!!

        searchButton.setOnClickListener {
            //search for the movie and fill in text views
            val movieToSearch = searchBar.text.toString()

            omdbApiClient.getMovie(movieToSearch).enqueue(object : Callback<Movie>{
                override fun onResponse(call: Call<Movie>?, response: Response<Movie>) {
                    Glide.with(this@MainActivity).load(response.body()?.Poster).into(movieImageView)
                    movieTitleView.text = response.body()?.Title.toString()
                    movieYearView.text = response.body()?.Year.toString()
                    movieRatingView.text = response.body()?.Rating.toString()
                    movieRunTimeView.text = response.body()?.Runtime.toString()
                    movieGenreView.text = response.body()?.Genre.toString()
                    imdbRatingView.text = response.body()?.ImdbRating.toString()
                    imdbLinkView.text = response.body()?.ImdbLink.toString()

                    movieImageView.visibility = VISIBLE
                    movieTitleView.visibility = VISIBLE
                    movieYearView.visibility = VISIBLE
                    movieRatingView.visibility = VISIBLE
                    movieRunTimeView.visibility = VISIBLE
                    movieGenreView.visibility = VISIBLE
                    imdbRatingView.visibility = VISIBLE
                    imdbLinkView.visibility = VISIBLE
                }
                override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                    movieRatingView.visibility = VISIBLE
                    movieRatingView.text = "There was no result for a movie titled \"$movieToSearch\""
                }
            })
        }

        imdbLinkView.setOnClickListener {
            //implicit intent to open link in browser
            val imdbLink = imdbLinkView.text.toString()
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$imdbLink"))
            startActivity(browserIntent)
        }

        shareButton.setOnClickListener {
            //implicit intent to share title and imdb link
            val title = movieTitleView.text.toString()
            val imdbLink = imdbLinkView.text.toString()
            val sendIntent: Intent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Movie Title: $title \nIMDB Link: $imdbLink")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }
}