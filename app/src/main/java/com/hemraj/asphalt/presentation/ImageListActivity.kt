package com.hemraj.asphalt.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hemraj.asphalt.R
import kotlinx.android.synthetic.main.activity_main.*

class ImageListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        imageLoadRv.layoutManager = LinearLayoutManager(this)
        imageLoadRv.adapter = ImageLoadAdaptor(getImageUrls())
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            title = "Image List"
        }
    }

    private fun getImageUrls(): List<String> {
        return mutableListOf<String>().apply {
            addAll(getImageList())
            addAll(getImageList())
        }
    }

    private fun getImageList(): List<String> {
        return mutableListOf<String>().apply {
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-1_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-2_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-3_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-4_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-5_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-6_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-7_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-8_1.jpg")
            add("https://gamedata.britishcouncil.org/sites/default/files/attachment/number-9_1.jpg")
        }
    }

}
