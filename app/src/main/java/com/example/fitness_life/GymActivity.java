package com.example.fitness_life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GymActivity extends AppCompatActivity implements WorkoutAdapter.WorkoutAdapterListener {

    private RecyclerView recyclerView;
    private TextView textViewEmptyState;
    private WorkoutAdapter workoutAdapter;
    private Button btnAddWorkout, btnBack;
    private ProgressBar progressBar;
    private FirebaseHelper firebaseHelper;
    private List<WorkoutPlan> workoutPlanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        // Initialize Firebase helper
        firebaseHelper = new FirebaseHelper();

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewWorkouts);
        textViewEmptyState = findViewById(R.id.textViewEmptyState);
        btnAddWorkout = findViewById(R.id.btnAddWorkout);
        btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progressBar);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set click listeners
        btnAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to workout form activity for adding a new workout
                Intent intent = new Intent(GymActivity.this, WorkoutFormActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to main activity
                finish();
            }
        });

        // Load workout plans
        loadWorkoutPlans();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload workout plans when returning to this activity
        loadWorkoutPlans();
    }

    private void loadWorkoutPlans() {
        // Show loading indicator
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        textViewEmptyState.setVisibility(View.GONE);
        
        // Get all workout plans from Firestore
        firebaseHelper.getAllWorkoutPlans(new FirebaseHelper.OnWorkoutPlansListener() {
            @Override
            public void onSuccess(List<WorkoutPlan> workoutPlans) {
                // Hide loading indicator
                progressBar.setVisibility(View.GONE);
                
                workoutPlanList = workoutPlans;
                
                // Set up adapter
                workoutAdapter = new WorkoutAdapter(GymActivity.this, workoutPlanList, GymActivity.this);
                recyclerView.setAdapter(workoutAdapter);
                
                // Update UI based on whether we have workout plans
                if (workoutPlanList.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    textViewEmptyState.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    textViewEmptyState.setVisibility(View.GONE);
                }
            }
            
            @Override
            public void onFailure(String errorMessage) {
                // Hide loading indicator
                progressBar.setVisibility(View.GONE);
                textViewEmptyState.setText("Error loading workout plans: " + errorMessage);
                textViewEmptyState.setVisibility(View.VISIBLE);
                Toast.makeText(GymActivity.this, "Error loading workout plans: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditClick(int position) {
        // Navigate to workout form activity for editing
        WorkoutPlan workoutPlan = workoutPlanList.get(position);
        Intent intent = new Intent(GymActivity.this, WorkoutFormActivity.class);
        intent.putExtra("workout_id", workoutPlan.getFirebaseId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(int position) {
        // Delete workout plan
        final WorkoutPlan workoutPlan = workoutPlanList.get(position);
        final int deletedPosition = position;
        
        // Show loading indicator or disable UI
        progressBar.setVisibility(View.VISIBLE);
        
        firebaseHelper.deleteWorkoutPlan(workoutPlan, new FirebaseHelper.OnWorkoutPlanListener() {
            @Override
            public void onSuccess(WorkoutPlan deletedWorkoutPlan) {
                // Hide loading indicator
                progressBar.setVisibility(View.GONE);
                
                // Refresh the list
                workoutPlanList.remove(deletedPosition);
                workoutAdapter.notifyItemRemoved(deletedPosition);
                
                // Update UI if list is now empty
                if (workoutPlanList.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    textViewEmptyState.setVisibility(View.VISIBLE);
                }
                
                Toast.makeText(GymActivity.this, "Workout plan deleted", Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onFailure(String errorMessage) {
                // Hide loading indicator
                progressBar.setVisibility(View.GONE);
                Toast.makeText(GymActivity.this, "Error deleting workout plan: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
