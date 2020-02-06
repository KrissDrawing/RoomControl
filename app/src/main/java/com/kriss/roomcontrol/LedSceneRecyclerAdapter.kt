package com.kriss.roomcontrol

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.led_scene_list_item.view.*

class LedSceneRecyclerAdapter(var items : MutableList<LedScene>, var mOnLedSceneListener: OnLedSceneListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    //private var items : List<LedScene> = ArrayList()
    //private lateinit var mOnLedSceneListener : OnLedSceneListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LedSceneViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.led_scene_list_item, parent, false),
            mOnLedSceneListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LedSceneViewHolder ->{
                holder.bind(items.get(position))
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(ledSceneList: MutableList<LedScene>){
        items = ledSceneList
    }


class LedSceneViewHolder constructor(
    itemView: View, onLedSceneListener: OnLedSceneListener
): RecyclerView.ViewHolder(itemView), View.OnClickListener{
    val ledSceneImage = itemView.led_scene_image
    val ledSceneName = itemView.led_scene_name
    val ledSceneSettingsButton = itemView.led_scene_image_settings
    val ledSceneDescription = itemView.led_scene_description
    var onLedSceneListener : OnLedSceneListener = onLedSceneListener


    fun bind(ledScene: LedScene){
        ledSceneName.setText(ledScene.name)
        ledSceneDescription.setText(ledScene.description)
        itemView.setOnClickListener(this)

        ledSceneSettingsButton.setOnClickListener{
            val intent: Intent? = Intent(itemView.context, MainActivity::class.java)
            itemView.context.startActivity(intent)
        }


        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(ledScene.image)
            .into(ledSceneImage)


        }


    override fun onClick(v: View?) {
        onLedSceneListener.OnLedSceneClick(adapterPosition)
    }

    //https://www.youtube.com/watch?v=Jo6Mtq7zkkg with displaing images
    }
    interface OnLedSceneListener{
        fun OnLedSceneClick(position: Int)
    }

}


