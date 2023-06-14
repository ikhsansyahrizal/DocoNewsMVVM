package com.docotel.ikhsansyahrizal.second.ui.main.features.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.docotel.ikhsansyahrizal.doconewss.helper.EndlessOnScrollListener
import com.docotel.ikhsansyahrizal.second.R
import com.docotel.ikhsansyahrizal.second.helper.Constant
import com.docotel.ikhsansyahrizal.second.helper.StateApi
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.databinding.ActivityMainBinding
import com.docotel.ikhsansyahrizal.second.ui.main.adapter.NewsAdapter
import com.docotel.ikhsansyahrizal.second.ui.main.features.detail.DetailActivity
import com.docotel.ikhsansyahrizal.second.ui.main.features.saved.SavedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel : MainViewModel by viewModels()

    private var queryData = ""

    private val articleItemsArrayList = ArrayList<ArticlesItem?>()

    private var page = 0
    private var loadMore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter(this)
        binding.rvNews.adapter = newsAdapter



        val layoutmanager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutmanager

        setSupportActionBar(binding.myToolbar)

        observeStatus()

        binding.rvNews.stopScroll()
        page = 1
        loadMore = false
        viewModel.fetchNews("us", "", page, 5, Constant.API_KEY)

        scrollData().let { newItemDetected ->
            binding.rvNews.addOnScrollListener(newItemDetected)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            articleItemsArrayList.clear()
            binding.rvNews.stopScroll()
            page = 1
            loadMore = false
            viewModel.fetchNews("us", "", page, 5, Constant.API_KEY)
            scrollData().let { newItemDetected ->
                binding.rvNews.addOnScrollListener(newItemDetected)
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun observeStatus() {
        viewModel.stateApi.observe(this) {
            when(it) {
                is StateApi.Error -> {
                    binding.tvError.text = it.message
                    binding.tvError.visibility = View.VISIBLE
                }
                StateApi.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE

                }
                StateApi.NotLoading -> {
                    binding.progressbar.visibility = View.GONE
                }
                is StateApi.Success -> {
                    articleItemsArrayList.addAll(it.data)
                    newsAdapter.setList(articleItemsArrayList.toMutableList())
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }

        }
    }

    override fun onItemClick(article: ArticlesItem, position: Int) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable("ARTICLE_KEY", article)
            putExtras(bundle)
            putExtra("index", position)
        }
        startActivity(intent)
    }

    private fun scrollData(): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                page += 1
                loadMore = true
                viewModel.fetchNews("us", "", page, 5, Constant.API_KEY)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                articleItemsArrayList.clear()
                viewModel.fetchNews("us", query, page, 5, Constant.API_KEY)
                queryData = query
                scrollData().let { newItemDetected ->
                    binding.rvNews.addOnScrollListener(newItemDetected)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmarks -> {
                val intent = Intent(this, SavedActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}