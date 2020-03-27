package com.hemraj.asphalt.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hemraj.asphalt.Asphalt
import com.hemraj.asphalt.AsphaltApplication
import com.hemraj.asphalt.R

class ImageLoadAdaptor(
    private val imageUrlList: List<String>
) : RecyclerView.Adapter<ImageLoadAdaptor.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_image_load_rv, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindTo(imageUrlList[position])
        with(holder.view) {
            tag = imageUrlList[position]
        }
    }

    class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageView)
        fun bindTo(url: String) {
            Asphalt.Builder()
                .load(url)
                .setPlaceHolder(ContextCompat.getDrawable(AsphaltApplication.INSTANCE, R.drawable.loading))
                .into(imageView)
        }
    }
}
