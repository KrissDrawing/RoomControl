package com.kriss.roomcontrol;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Tasks extends ExpandableGroup<Task> {


    public Tasks(String title, List<Task> items) {
        super(title, items);
    }
}
