package com.kriss.roomcontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TaskAdapter extends CheckableChildRecyclerViewAdapter<TaskViewHolder, SubtaskViewHolder> {

    public TaskAdapter(List<? extends CheckedExpandableGroup> groups) {
        super(groups);
    }

    /*public TaskAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }*/


    @Override
    public SubtaskViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_extendable_list_item, parent, false);
        return new SubtaskViewHolder(view);
    }

    @Override
    public void onBindCheckChildViewHolder(SubtaskViewHolder holder, int flatPosition, CheckedExpandableGroup group, int childIndex) {
        final  Subtask subtask = (Subtask) group.getItems().get(childIndex);
        holder.bind(subtask);
    }

    @Override
    public TaskViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(TaskViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Task task = (Task) group;
        holder.bind(task);
    }

    /*@Override
    public void onBindChildViewHolder(SubtaskViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final  Subtask subtask = (Subtask) group.getItems().get(childIndex);
        holder.bind(subtask);
    }*/

      /* @Override
    public SubtaskViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_extendable_list_item, parent, false);
        return new SubtaskViewHolder(view);
    }*/



}
