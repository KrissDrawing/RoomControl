package com.kriss.roomcontrol;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

    // private TaskRecyclerAdapter taskRecyclerAdapter;
    private List<Task> listOfTasks= new ArrayList();
   // private Bundle savedInstanceState;
    private TaskAdapter adapter;
    Serializable state;

    public TaskFragment() {
        // Required empty public constructor
    }

   /* public TaskFragment(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        // Required empty public constructor
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            listOfTasks = savedInstanceState.getParcelableArrayList("ITEMS");
            adapter = new TaskAdapter(listOfTasks);
            adapter.onRestoreInstanceState(savedInstanceState);
        }else{
            populateList();
            adapter = new TaskAdapter(listOfTasks);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View taskLayout = inflater.inflate(R.layout.task_fragment, container, false);



        RecyclerView recyclerView = taskLayout.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(taskLayout.getContext()));

        //adapter.onRestoreInstanceState(savedInstanceState);
        TopSpacingItemDecoration topSpacingItemDecoration = new TopSpacingItemDecoration(30);
        recyclerView.addItemDecoration(topSpacingItemDecoration);
        //recyclerView.setHasFixedSize(false);
        //taskRecyclerAdapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return taskLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);

        //outState.putSerializable("ITEMS", state);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        adapter.onRestoreInstanceState(savedInstanceState);
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_task,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){
            Intent intent = new Intent(getContext(), TaskEditActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(),"dodaj aktywnosc", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


       /* @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.onSaveInstanceState(savedInstanceState);

    }
*/


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
