package com.kriss.roomcontrol;

import java.util.List;

public class Task {

    private  String taskName;
    private  List<String> tasks;
    private  List<Boolean> taskChecks;

    public String getTaskName() {
        return taskName;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public List<Boolean> getTaskChecks() {
        return taskChecks;
    }

    private Boolean expanded;


    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Task(String taskName, List<String> tasks, List<Boolean> taskChecks){
        this.taskName = taskName;
        this.tasks = tasks;
        this.taskChecks = taskChecks;
        expanded = false;
    }
}
