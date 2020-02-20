/*
package com.kriss.roomcontrol;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

    public void submitList(ArrayList<Task> listOfTasks){
        this.listOfTasks = listOfTasks;
    }


public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView taskName;
    //RelativeLayout expandableLayout;
    ConstraintLayout expandableLayout;
    ListView subtaskListView;
    ListView listView;



    public ViewHolder(View itemView) {
        super(itemView);
        //taskName = itemView.findViewById(R.id.taskName);
        //expandableLayout = itemView.findViewById(R.id.expandableTaskListLayout);
        //subtaskListView = itemView.findViewById(R.id.expandableTaskList);
    }

        public void Bind(final Task task ){
            subtaskListView.measure(0,0);
            subtaskListView.getLayoutParams().height = task.getTasks().size() * subtaskListView.getMeasuredHeight() ;

        //TODO tutaj z bazy danych brać nazwy
            taskName.setText(task.getTaskName());
            ArrayAdapter arrayAdapter = new ArrayAdapter(itemView.getContext(), android.R.layout.simple_list_item_1, task.getTasks());
            // ArrayAdapter arrayAdapter = new ArrayAdapter(itemView.getContext(),R.layout.task_extendable_list_item, task.getTasks());

            taskName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.setExpanded(!task.getExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        boolean isExpanded = task.getExpanded();
        expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);



            subtaskListView.setAdapter(arrayAdapter);

        }
    }

}

*/
