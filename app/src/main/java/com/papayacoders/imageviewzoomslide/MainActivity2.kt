package com.papayacoders.imageviewzoomslide

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ScaleGestureDetector
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.era.sliderdemo.MyStuff
import com.era.sliderdemo.viewPagerAdapter


class MainActivity2 : DialogFragment(R.layout.activity_main2), ClickListener {


    val list = listOf(
        MyStuff.Image( "https://images.pexels.com/photos/12488389/pexels-photo-12488389.jpeg"),
           MyStuff.Image("https://media.licdn.com/dms/image/C560BAQGa3UT1nTpSfA/company-logo_200_200/0/1613214749171?e=2147483647&v=beta&t=ap0k8VTLBs0qsAhV6hJ5tCNdX6Clqa4M_WNavxixEiM"),
           MyStuff.Image("https://papaya-coders.b-cdn.net/wp-content/uploads/2023/02/@papayacoders.png"),
           MyStuff.Video("https://user-images.githubusercontent.com/46995327/119235768-bc402e80-bb51-11eb-8b77-1e2a979df5a4.mp4"),
           MyStuff.Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSF-GL4w7-ux833MwzwscsPN-L-11lpU1nFVlvZeim8NLdPG9LCgl6UyZzpVYaPyMbXiU&usqp=CAU")
    )
    private  lateinit var sliderViewPager : ViewPager2


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState).apply {
            val window = this.window;
            window?.setLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            window?.setGravity(Gravity.CENTER)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliderViewPager = view.findViewById<ViewPager2>(R.id.sliderViewPager)
        val sliderAdapter = viewPagerAdapter(requireContext(), list, this)
        sliderViewPager.adapter = sliderAdapter


        sliderViewPager.requestDisallowInterceptTouchEvent(true)

    }

    override fun clickListener() {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
        this.dismiss()
//        super.clickListener()
    }

    override fun isScroll(scroll : Boolean) {
        Log.d("SHUBH", "isScroll: $scroll")
        sliderViewPager.isUserInputEnabled = scroll
    }


}