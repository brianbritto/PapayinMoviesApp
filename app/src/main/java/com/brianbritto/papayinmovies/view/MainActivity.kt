package com.brianbritto.papayinmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.brianbritto.papayinmovies.R
import com.brianbritto.papayinmovies.di.Injection
import com.brianbritto.papayinmovies.model.Movie
import com.brianbritto.papayinmovies.view.adapter.MovieAdapter
import com.brianbritto.papayinmovies.viewmodel.MovieViewModel
import com.brianbritto.papayinmovies.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.name
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI(){
        adapter= MovieAdapter(viewModel.movies.value?: emptyList())
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, MovieViewModelFactory(Injection.providerMovieRepository())).get(MovieViewModel::class.java)
        viewModel.movies.observe(this, renderVideos)

        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    private val renderVideos = Observer<List<Movie>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }


    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.loadMovies()
    }
}
