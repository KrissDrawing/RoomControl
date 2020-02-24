package com.kriss.roomcontrol;

import android.os.Parcelable;

import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class Task extends MultiCheckExpandableGroup implements Parcelable {

    private int iconResId;
    private List<Subtask> subtaskList = new ArrayList();

    public Task(String title, List<Subtask> items) {
        super(title, items);
        this.subtaskList = items;
    }

    public List<Subtask> getSubtaskList() {
        return subtaskList;
    }

    public int getIconResId(){
        return iconResId;
    }
}
