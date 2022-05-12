package com.thecode.dagger_hilt_mvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thecode.dagger_hilt_mvvm.R
import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.model.Post
import kotlinx.android.synthetic.main.item_blog.view.*

class PostAdapter(private val listener: PostItemListener) : RecyclerView.Adapter<PostViewHolder>() {

    interface PostItemListener {
//        fun onClickedBlog(postTitle: CharSequence)
        fun onClickedBlog(postId: CharSequence)
    }

    private val items = ArrayList<Post>()
    private lateinit var post: Post
     val postId = 0

    fun setItems(items: ArrayList<Post>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return PostViewHolder(view, listener)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        post = items[position]
        val blog = items[position]
        holder.textTitle.text = blog.title
        holder.textDescription.text = blog.body
        holder.postId = blog.id



//        Glide.with(holder.itemLayout).load(blog.image)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.placeholder)
//            .apply(RequestOptions().centerCrop())
//            .into(holder.image)
    }
}

class PostViewHolder(itemView: View, private val listener: PostAdapter.PostItemListener) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    val itemLayout: ConstraintLayout = itemView.blog_layout
    val textTitle: TextView = itemView.text_title
//    val textId: TextView = itemView.
    val textDescription: TextView = itemView.text_description
    var postId: Int = 0
//    val image: AppCompatImageView = itemView.image

    init {
        itemLayout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener.onClickedBlog(postId.toString())
    }
}

