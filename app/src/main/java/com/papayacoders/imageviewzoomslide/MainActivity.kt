package com.papayacoders.imageviewzoomslide

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    val list = arrayListOf(
        "https://images.pexels.com/photos/12488389/pexels-photo-12488389.jpeg",
        "https://media.licdn.com/dms/image/C560BAQGa3UT1nTpSfA/company-logo_200_200/0/1613214749171?e=2147483647&v=beta&t=ap0k8VTLBs0qsAhV6hJ5tCNdX6Clqa4M_WNavxixEiM",
        "https://papaya-coders.b-cdn.net/wp-content/uploads/2023/02/@papayacoders.png",
        "https://user-images.githubusercontent.com/46995327/119235768-bc402e80-bb51-11eb-8b77-1e2a979df5a4.mp4",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSF-GL4w7-ux833MwzwscsPN-L-11lpU1nFVlvZeim8NLdPG9LCgl6UyZzpVYaPyMbXiU&usqp=CAU"
    )
//    private lateinit var viewer: StfalconImageViewer<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)


        imageView.setOnClickListener {
            val dialogFragment = MainActivity2()
            dialogFragment.show(supportFragmentManager, "My  Fragment")
        }

//        val builder = StfalconImageViewer.Builder(
//            this,
//            list
//        ) { imageView, image ->
//            Glide.with(this)
//                .load(image)
//                .into(imageView)
//        }
//
//        //some other codes
//
//        //some other codes
//        builder
//            .withStartPosition(0)
//            .withHiddenStatusBar(false)
//            .allowZooming(true)
//            .allowSwipeToDismiss(true)
//            .withImageChangeListener { position: Int -> }
//            .show()

    }

}