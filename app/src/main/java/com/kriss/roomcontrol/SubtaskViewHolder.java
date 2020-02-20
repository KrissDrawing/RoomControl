package com.kriss.roomcontrol;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SubtaskViewHolder extends ChildViewHolder {

    private TextView subtaskName;

    public SubtaskViewHolder(View itemView) {
        super(itemView);
        subtaskName = itemView.findViewById(R.id.list_subtask_name);
    }

    public void bind(Subtask subtask){
        subtaskName.setText(subtask.name);
    }



}
