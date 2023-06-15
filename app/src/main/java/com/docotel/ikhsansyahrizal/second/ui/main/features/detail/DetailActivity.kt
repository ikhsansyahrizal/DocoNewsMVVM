package com.docotel.ikhsansyahrizal.second.ui.main.features.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.docotel.ikhsansyahrizal.second.R
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel : DetailViewModel by viewModels()
    private var isBookmarked: Boolean = false

    private lateinit var articlesItem: ArticlesItem
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbarDetail)

        val article = intent.getParcelableExtra<ArticlesItem?>("ARTICLE_KEY")
        articlesItem = intent.getParcelableExtra("ARTICLE_KEY")!!
        pos = intent.getIntExtra("index", 0)

        binding.tvTitleDetail.text = article?.title
        binding.tvDescriptionDetail.text = article?.description
        binding.tvAuthorDetail.text = article?.author
        Picasso.get().load(article?.urlToImage).into(binding.imgDetail)


        val tvReadMore = binding.tvReadMoreDetail

        tvReadMore.isClickable = true
        tvReadMore.movementMethod = LinkMovementMethod.getInstance()

        val url = article?.url.toString()
        val linkText = getText(R.string.read_more)

        val link_span = SpannableString(linkText)
        link_span.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }, 0, linkText.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        tvReadMore.text = link_span

        binding.ibShare.setOnClickListener {
            shareLink(url)
        }

    }

    private fun shareLink(link: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, link)
        startActivity(Intent.createChooser(intent, "Share via"))
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        val bookmarkIcon = menu?.findItem(R.id.add_bookmark)
        if (isBookmarked) {
            bookmarkIcon?.setIcon(R.drawable.ic_bookmark_added_24)
        } else {
            bookmarkIcon?.setIcon(R.drawable.ic_bookmark_add_24)
        }
        updateBookmarkIcon(articlesItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        invalidateOptionsMenu()
        return when (item.itemId) {
            R.id.add_bookmark -> {
                toogledBookmark()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toogledBookmark() {
        if (isBookmarked) {
            viewModel.removeBookmark(articlesItem)
            isBookmarked = false
        } else {
            viewModel.addBookmark(articlesItem)
            isBookmarked = true
        }
        isBookmarked = !isBookmarked
    }

    private fun updateBookmarkIcon(articlesItem: ArticlesItem) {
        val list_article = viewModel.getBookmarkedArticles()
        isBookmarked = list_article.contains(articlesItem)
        invalidateOptionsMenu()
    }


}