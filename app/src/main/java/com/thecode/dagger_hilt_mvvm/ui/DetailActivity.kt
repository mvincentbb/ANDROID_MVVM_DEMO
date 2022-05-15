package com.thecode.dagger_hilt_mvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode.dagger_hilt_mvvm.R
import com.thecode.dagger_hilt_mvvm.model.Detail

import com.thecode.dagger_hilt_mvvm.model.Post
import com.thecode.dagger_hilt_mvvm.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailActivity : AppCompatActivity(),  {
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val posts

        subscribeObservers()
        viewModel.setStateEvent(DetailStateEvent.GetDetailEvents)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.setStateEvent(DetailStateEvent.GetDetailEvents)
        }

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Detail>> -> {
                    displayLoading(false)
                    populateRecyclerView(dataState.data)
                }
                is DataState.Loading -> {
                    displayLoading(true)
                }
                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }
            }
        })
    }


    private fun displayError(message: String?) {
        if (message.isNullOrEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun populateRecyclerView(posts: List<Post>) {
        if (posts.isNotEmpty()) adapter.setItems(ArrayList(posts))
    }

/*    private fun setupRecyclerView() {
        adapter = PostAdapter(this)
        blog_recyclerview.layoutManager = LinearLayoutManager(this)
        blog_recyclerview.adapter = adapter
    }

    override fun onClickedBlog(postId: CharSequence) {
        Toast.makeText(this, postId, Toast.LENGTH_SHORT).show()
    }*/

}