package com.kriss.roomcontrol;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

    private TaskRecyclerAdapter taskRecyclerAdapter;
    private List<Task> listOfTasks= new ArrayList();

    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View taskLayout = inflater.inflate(R.layout.fragment_task, container, false);

        populateList();
        RecyclerView recyclerView = taskLayout.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(taskLayout.getContext()));
        taskRecyclerAdapter = new TaskRecyclerAdapter(listOfTasks);
        //taskRecyclerAdapter.setClickListener(this);
        recyclerView.setAdapter(taskRecyclerAdapter);




        return taskLayout;
    }

    public void populateList(){
        List<String> dupa1 = Arrays.asList("foo", "bar", "dupa");
        List<Boolean> dupa2 = Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);

        Task task1 = new Task("dupa",dupa1,dupa2);
        Task task2 = new Task("dupa1",dupa1,dupa2);
        Task task3 = new Task("dupa2",dupa1,dupa2);
        Task task4 = new Task("dupa3",dupa1,dupa2);

        listOfTasks.add(task1);
        listOfTasks.add(task2);
        listOfTasks.add(task3);
        listOfTasks.add(task4);
    }

}
