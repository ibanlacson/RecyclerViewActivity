package com.auf.cea.recyclerviewactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.auf.cea.recyclerviewactivity.databinding.ActivityDetailsScreenBinding
import com.auf.cea.recyclerviewactivity.dialogs.DetailsFragment
import com.auf.cea.recyclerviewactivity.models.BooksModel

class DetailsScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityDetailsScreenBinding
    private var shopLink:String  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            val bookObject = intent.extras!!.get(MODEL_DATA) as BooksModel
            Log.d(DetailsScreenActivity::class.java.simpleName, bookObject.toString())

            shopLink = bookObject.shopLink

            // Change ActionBar Title
            supportActionBar!!.title = bookObject.name

            with(binding){
                imgHolder.setImageResource(bookObject.imgLink)
                txtTitle.text = bookObject.name
                txtYear.text = String.format("(%s)", bookObject.datePublished)
                txtAuthor.text = String.format("by: %s", bookObject.author)
                txtBookShortDescription.text = bookObject.shortDescription
                txtBookDescription.text = bookObject.description
                btnShopNow.setOnClickListener(this@DetailsScreenActivity)
            }
        }

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_shop_now) -> {
                val intent =  Intent(Intent.ACTION_VIEW, Uri.parse(shopLink))
                startActivity(intent)
            }
        }
    }
}