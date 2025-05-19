package com.example.fitness_life;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private Context context;
    private List<WorkoutPlan> workoutPlanList;
    private WorkoutAdapterListener listener;

    public WorkoutAdapter(Context context, List<WorkoutPlan> workoutPlanList, WorkoutAdapterListener listener) {
        this.context = context;
        this.workoutPlanList = workoutPlanList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_workout, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        WorkoutPlan workoutPlan = workoutPlanList.get(position);
        
        holder.textViewWorkoutName.setText(workoutPlan.getName());
        holder.textViewDescription.setText(workoutPlan.getDescription());
        holder.textViewDifficulty.setText("Difficulty: " + workoutPlan.getDifficulty());
        holder.textViewDuration.setText("Duration: " + workoutPlan.getDuration() + " minutes");
        holder.textViewWorkoutTime.setText("Time: " + workoutPlan.getWorkoutTime());
        
        // Set click listeners for buttons
        holder.btnEditWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEditClick(holder.getAdapterPosition());
                }
            }
        });
        
        holder.btnDeleteWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDeleteClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutPlanList.size();
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWorkoutName, textViewDescription, textViewDifficulty, textViewDuration, textViewWorkoutTime;
        Button btnEditWorkout, btnDeleteWorkout;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWorkoutName = itemView.findViewById(R.id.textViewWorkoutName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDifficulty = itemView.findViewById(R.id.textViewDifficulty);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            textViewWorkoutTime = itemView.findViewById(R.id.textViewWorkoutTime);
            btnEditWorkout = itemView.findViewById(R.id.btnEditWorkout);
            btnDeleteWorkout = itemView.findViewById(R.id.btnDeleteWorkout);
        }
    }

    public interface WorkoutAdapterListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
}
