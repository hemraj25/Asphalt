package com.hemraj.asphalt

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.hemraj.asphalt.data.Request
import com.hemraj.asphalt.network.BitmapHunter


class Asphalt() {

    private var target: ImageView? = null
    private var imageUrl: String? = null
    private var placeHolder: Drawable? = null

    constructor(builder: Builder) : this() {
        target = builder.getTarget()
        imageUrl = builder.getImageUrl()
        placeHolder = builder.getPlaceHolder()
        enequeAndSubmit()
    }

    private fun enequeAndSubmit() {
        target?.let { target->
            placeHolder?.let {
                target.setImageDrawable(it)
            }

            imageUrl?.let {
                BitmapHunter(Request(it, target), Util.getLruCache()).hunt()
            }?: also {
                Log.d("Asphalt"," ImageUrl is null")
            }
        }
    }

    class Builder {
        private lateinit var asphalt: Asphalt
        private var target: ImageView? = null
        private var imageUrl: String? = null

        private var placeHolder: Drawable? = null

        fun load(imageUrl: String): Builder {
            this.imageUrl = imageUrl
            return this
        }

        fun setPlaceHolder(placeHolder: Drawable?): Builder {
            this.placeHolder = placeHolder
            return this
        }

        fun getPlaceHolder(): Drawable? {
            return placeHolder
        }

        fun getImageUrl(): String? {
            return imageUrl
        }

        fun into(target: ImageView): Builder {
            this.target = target
            build()
            return this
        }

        fun getTarget(): ImageView? {
            return target
        }

        private fun build(): Asphalt {
            asphalt = Asphalt(this)
            return asphalt
        }

    }
}