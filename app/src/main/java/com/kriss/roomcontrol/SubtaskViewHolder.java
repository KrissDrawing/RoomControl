package com.kriss.roomcontrol;

import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SubtaskViewHolder extends CheckableChildViewHolder {

    private CheckedTextView subtaskName;

    public SubtaskViewHolder(View itemView) {
        super(itemView);
        subtaskName = itemView.findViewById(R.id.list_subtask_name);
    }

    @Override
    public Checkable getCheckable() {
        return subtaskName;
    }

    public void bind(Subtask subtask){
        subtaskName.setText(subtask.name);
    }



}
