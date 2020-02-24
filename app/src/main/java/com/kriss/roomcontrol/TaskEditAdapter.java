package com.kriss.roomcontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskEditAdapter extends RecyclerView.Adapter<TaskEditAdapter.TaskEditViewHolder> {

    private Task selectedTask;

    public TaskEditAdapter(Task selectedTask){
        this.selectedTask = selectedTask;
    }

    public TaskEditAdapter(){
        selectedTask = new Task("zmienic to", new ArrayList<Subtask>());
    }

    @NonNull
    @Override
    public TaskEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_edit_item, parent, false);
        return new TaskEditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskEditViewHolder holder, int position) {
        holder.bind(selectedTask.getSubtaskList().get(position));
    }

    @Override
    public int getItemCount() {
        return selectedTask.getSubtaskList().size();
    }

    public class TaskEditViewHolder extends RecyclerView.ViewHolder {

        private TextView subtaskName;
        private TextView subtaskIndex;
        private ImageView subtaskDelete;



        public TaskEditViewHolder(@NonNull View itemView) {
            super(itemView);
            subtaskName = itemView.findViewById(R.id.subtask_edit_name);
            subtaskIndex = itemView.findViewById(R.id.subtask_edit_index);
            subtaskDelete = itemView.findViewById(R.id.subtask_edit_delete);

        }

        public void bind(final Subtask subtask){
            if(subtask != null){
                subtaskName.setText(subtask.name);
                subtaskIndex.setText(Integer.toString(getAdapterPosition() + 1));

                subtaskDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO check for deleting ?
                        selectedTask.getSubtaskList().remove(subtask);
                        notifyDataSetChanged();
                    }
                });
            }

        }
    }
}
