package com.era.sliderdemo

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.papayacoders.imageviewzoomslide.ClickListener
import com.papayacoders.imageviewzoomslide.R

class viewPagerAdapter(
    private val context: Context,
    private val list: List<MyStuff>,
    val listener: ClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract class MyViewHolderView(view: View) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            IMAGE_VIEW_TYPE -> {
                val view = inflater.inflate(R.layout.img_vp, parent, false)
                ImageViewHolder(view)
            }

            VIDEO_VIEW_TYPE -> {
                val view = inflater.inflate(R.layout.video_vp, parent, false)
                VideoViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (holder) {
            is ImageViewHolder -> {
                val dataItem = list[position] as MyStuff.Image
                Glide.with(context)
                    .load(dataItem.imageUrl)
                    .into(holder.imageView)


                holder.imageView.dismissListener(listener)
            }

            is VideoViewHolder -> {
                val dataItem = list[position] as MyStuff.Video
                val player = SimpleExoPlayer.Builder(context).build()
                holder.playerView.player = player
                val mediaItem = MediaItem.fromUri(dataItem.videoUrl)
                player.setMediaItem(mediaItem)
                player.prepare()
                val gestureDetector =
                    GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                        override fun onScroll(
                            e1: MotionEvent,
                            e2: MotionEvent,
                            distanceX: Float,
                            distanceY: Float
                        ): Boolean {
                            if (distanceY < -50) {
                                listener.clickListener()
//                                Toast.makeText(context, "Scrolled up", Toast.LENGTH_SHORT).show()
                            }
                            Log.e("@@", "Inside")
                            return super.onScroll(e1, e2, distanceX, distanceY)

                        }
                    })

                holder.playerView.setOnTouchListener { _, event ->
                    gestureDetector.onTouchEvent(event)
                    true
                }

            }
        }


    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is MyStuff.Image -> IMAGE_VIEW_TYPE
            is MyStuff.Video -> VIDEO_VIEW_TYPE
            else -> {
                Log.e("@@", "Error")
            }
        }
    }


    class ImageViewHolder(itemView: View) : MyViewHolderView(itemView) {
        val imageView: ZoomClass = itemView.findViewById(R.id.img_view)
    }

    class VideoViewHolder(itemView: View) : MyViewHolderView(itemView) {
        val playerView: PlayerView = itemView.findViewById(R.id.video_view)
    }

    companion object {
        private const val IMAGE_VIEW_TYPE = 0
        private const val VIDEO_VIEW_TYPE = 1
    }

}