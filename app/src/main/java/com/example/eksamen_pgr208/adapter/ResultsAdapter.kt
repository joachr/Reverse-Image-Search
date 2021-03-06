package com.example.eksamen_pgr208.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.eksamen_pgr208.R
import com.example.eksamen_pgr208.activities.ResultActivity
import com.example.eksamen_pgr208.adapter.model.ImageResultItemModel
import kotlinx.android.synthetic.main.image_rv_layout.view.*

class ResultsAdapter(val context: Context?,
                     private val imageModels: ArrayList<ImageResultItemModel>,
                     private val listener: ResultActivity
                    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.image_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        context?.let {
            Glide.with(it.applicationContext)
                .load(imageModels[position].image_link)
                .fitCenter()
                .transform(RoundedCorners(15))
                .into(holder.itemView.image_result)
        } ?: Log.e("ResultsAdapter", "Could not load imageModels into recycler view for some reason")

    }

    override fun getItemCount(): Int {
        return imageModels.size
    }

    inner class ViewHolder(v: View?): RecyclerView.ViewHolder(v!!), View.OnClickListener{
        val image : ImageView = this.itemView.image_result

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onImageClick(position)
            }
        }

        init {
            image.setOnClickListener(this)
        }
    }

    interface RecyclerClick{
        fun onImageClick(position: Int)
    }

}