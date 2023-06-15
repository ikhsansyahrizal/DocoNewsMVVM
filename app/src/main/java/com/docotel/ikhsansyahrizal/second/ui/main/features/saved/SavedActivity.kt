package com.docotel.ikhsansyahrizal.second.ui.main.features.saved

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.docotel.ikhsansyahrizal.doconewss.viewmodel.SavedViewModel
import com.docotel.ikhsansyahrizal.second.R
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.databinding.ActivitySavedBinding
import com.docotel.ikhsansyahrizal.second.ui.main.adapter.NewsAdapter
import com.docotel.ikhsansyahrizal.second.ui.main.features.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySavedBinding
    private lateinit var bookmarkadapter: NewsAdapter
    private val viewModel: SavedViewModel by viewModels ()
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbarBookmark)
        toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
        toast.view?.findViewById<ImageView>(android.R.id.icon)?.apply {
            setImageResource(R.drawable.ic_delete_24)
            visibility = View.VISIBLE
        }

        binding.rvNewsBookmark.layoutManager = LinearLayoutManager(this)
        bookmarkadapter = NewsAdapter(this)
        binding.rvNewsBookmark.adapter = bookmarkadapter


        viewModel.bookmarkedArticlesObserve.observe(this) {
            val newdata = viewModel.getBookmarkedArticles()
            bookmarkadapter.setList(newdata.toMutableList())
        }


        binding.swipeRefreshLayout.setOnRefreshListener {

            val newdata = viewModel.getBookmarkedArticles()
            bookmarkadapter.setList(newdata.toMutableList())
            binding.swipeRefreshLayout.isRefreshing = false
        }


    }

    override fun onItemClick(article: ArticlesItem, position: Int) {
        val intent = Intent(this@SavedActivity, DetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable("ARTICLE_KEY", article)
            putExtras(bundle)
            putExtra("index", position)
        }
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bookmark, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clear -> {
                clearAllData()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearAllData() {
        viewModel.clearAllData()
        bookmarkadapter.setList(emptyList<ArticlesItem?>().toMutableList())
//        Toast.makeText(applicationContext, getString(R.string.all_saved_data_cleared), Toast.LENGTH_SHORT).show()
        toast.setText(R.string.all_saved_data_cleared)
        toast.show()
    }

    override fun onResume() {
        val newdata = viewModel.getBookmarkedArticles()
        bookmarkadapter.setList(newdata.toMutableList())
        super.onResume()
    }

}