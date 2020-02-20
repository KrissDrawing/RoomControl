package com.kriss.roomcontrol;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

   // private TaskRecyclerAdapter taskRecyclerAdapter;
    private List<Task> listOfTasks= new ArrayList();

    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View taskLayout = inflater.inflate(R.layout.fragment_task, container, false);


        RecyclerView recyclerView = taskLayout.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(taskLayout.getContext()));
        populateList();
        TaskAdapter adapter = new TaskAdapter(listOfTasks);

        TopSpacingItemDecoration topSpacingItemDecoration = new TopSpacingItemDecoration(30);
        recyclerView.addItemDecoration(topSpacingItemDecoration);
        //recyclerView.setHasFixedSize(false);
        //taskRecyclerAdapter.setClickListener(this);
        recyclerView.setAdapter(adapter);



        return taskLayout;
    }

    /*private void onDataBaseListener() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("LED_SCENE");

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getChildren().
                                mapNotNullTo(listItems) { it.getValue<LedScene>(LedScene::class.java) }
                        //initRecyclerView()
                        //addDataSet()
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                })

            override fun onDataChange(p0: DataSnapshot) {

            }
        })
    }*/

    public void populateList(){

        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Subtask> subtasks = new ArrayList<>();

        subtasks.add(new Subtask("dupa1"));
        subtasks.add(new Subtask("dupa2"));
        subtasks.add(new Subtask("dupa3"));
        subtasks.add(new Subtask("dupa4"));
        subtasks.add(new Subtask("dupa5"));

        Task task1 = new Task("dupy",subtasks);
        listOfTasks.add(task1);

        ArrayList<Subtask> subtasks1 = new ArrayList<>();

        subtasks1.add(new Subtask("siusiak1"));
        subtasks1.add(new Subtask("siusiak2"));
        subtasks1.add(new Subtask("siusiak3"));
        subtasks1.add(new Subtask("siusiak4"));
        subtasks1.add(new Subtask("siusiak5"));

        Task task2 = new Task("siusiaki",subtasks1);
        listOfTasks.add(task2);
    }

}
