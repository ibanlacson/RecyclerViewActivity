package com.auf.cea.recyclerviewactivity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.auf.cea.recyclerviewactivity.*
import com.auf.cea.recyclerviewactivity.databinding.ContentRecyclerViewBinding
import com.auf.cea.recyclerviewactivity.dialogs.DetailsFragment
import com.auf.cea.recyclerviewactivity.models.BooksModel

class RecyclerViewAdapter(private var bookList: ArrayList<BooksModel>, private var context:Context): RecyclerView.Adapter<RecyclerViewAdapter.RVViewHolder>() {

    var activity = context as AppCompatActivity

    inner class RVViewHolder(val binding: ContentRecyclerViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(binding: ContentRecyclerViewBinding){
            binding.llCardView.setOnClickListener{
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val binding = ContentRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        with(holder){
            holder.bind(holder.binding)
            with(bookList[position]){

                binding.txtTitle.text = this.name
                binding.txtAuthorAndYear.text = String.format("by: %s (%s)", this.author, this.datePublished)
                binding.txtBookDescription.text = this.shortDescription
                binding.imgHolder.setImageResource(this.imgLink)

                binding.btnFullDescription.setOnClickListener{
                    val intent = Intent(context,DetailsScreenActivity::class.java)
                    intent.putExtra(MODEL_DATA,bookList[position])
                    context.startActivity(intent)
                }

                binding.btnViewMore.setOnClickListener{
                    val fragmentManager = DetailsFragment.newInstance(bookList[position])
                    fragmentManager.show(activity.supportFragmentManager, null)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}

