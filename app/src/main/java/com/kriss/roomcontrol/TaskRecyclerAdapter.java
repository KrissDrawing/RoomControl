package com.kriss.roomcontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder> {

    private List<Task> listOfTasks;

    public TaskRecyclerAdapter(List<Task> listOfTasks){
        this.listOfTasks = listOfTasks;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.task_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(listOfTasks.get(position));
    }


    @Override
    public int getItemCount() {
        return listOfTasks.size();
    }



public class ViewHolder extends RecyclerView.ViewHolder {
    public CheckBox taskName;
    ConstraintLayout expandableLayout;
    ListView listView;



    public ViewHolder(View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.taskName);
        expandableLayout = itemView.findViewById(R.id.expandableTaskListLayout);
        listView = itemView.findViewById(R.id.expandableTaskList);
    }

        public void Bind(final Task task ){


        //TODO tutaj z bazy danych brać nazwy
            taskName.setText(task.getTaskName());

        boolean isExpanded = task.getExpanded();
        expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

            ArrayAdapter arrayAdapter = new ArrayAdapter(itemView.getContext(), android.R.layout.simple_list_item_1, task.getTasks());
           // ArrayAdapter arrayAdapter = new ArrayAdapter(itemView.getContext(),R.layout.task_extendable_list_item, task.getTasks());
            listView.setAdapter(arrayAdapter);

        taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setExpanded(!task.getExpanded());
                notifyItemChanged(getAdapterPosition());
            }
        });
        }
    }
}

