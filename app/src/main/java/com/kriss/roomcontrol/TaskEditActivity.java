package com.kriss.roomcontrol;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskEditActivity extends AppCompatActivity{

    private Button addSubtaskButton;
    private ArrayList<Subtask> subtasks = new ArrayList<>();
    private EditText taskName;
    private Task newTask = new Task("empty", subtasks);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);

//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setElevation(0); // or other...
        }

        addSubtaskButton = findViewById(R.id.edit_task_add_button);
        taskName = findViewById(R.id.edit_task_name);


        subtasks.add(new Subtask("dupa1"));
        subtasks.add(new Subtask("dupa2"));
        subtasks.add(new Subtask("dupa3"));
        subtasks.add(new Subtask("dupa4"));
        subtasks.add(new Subtask("dupa5"));
        //TODO get certain Task, or create new

        final TaskEditAdapter adapter = new TaskEditAdapter(newTask);

        RecyclerView recyclerView = findViewById(R.id.edit_task_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TopSpacingItemDecoration topSpacingItemDecoration = new TopSpacingItemDecoration(30);
        recyclerView.addItemDecoration(topSpacingItemDecoration);
        recyclerView.setAdapter(adapter);

        addSubtaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO dialog with typing and position
                int position = subtasks.size();
                addItemAtPosition(position);
                adapter.notifyItemInserted(position);
            }
        });
    }


    private void addItemAtPosition(int position) {
        //some sort of dialog
        subtasks.add(position,new Subtask("dupa4"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_task,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){

            Toast.makeText(this,"dodaj aktywnosc", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
