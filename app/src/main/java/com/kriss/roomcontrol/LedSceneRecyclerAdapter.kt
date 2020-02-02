package com.kriss.roomcontrol

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.led_scene_list_item.view.*

class LedSceneRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    private var items : List<LedScene> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LedSceneViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.led_scene_list_item, parent, false)
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

    fun submitList(ledSceneList: List<LedScene>){
        items = ledSceneList
    }


class LedSceneViewHolder constructor(
    itemView: View
): RecyclerView.ViewHolder(itemView){
    val ledSceneImage = itemView.led_scene_image
    val ledSceneName = itemView.led_scene_name
    val ledSceneDescription = itemView.led_scene_description

    fun bind(ledScene: LedScene){
        ledSceneName.setText(ledScene.name)
        ledSceneDescription.setText(ledScene.description)


        //https://www.youtube.com/watch?v=Jo6Mtq7zkkg with displaing images
    }
}

}