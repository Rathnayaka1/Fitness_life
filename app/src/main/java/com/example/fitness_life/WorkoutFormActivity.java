package com.example.fitness_life;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WorkoutFormActivity extends AppCompatActivity {

    private TextView titleTextView, textViewSelectedTime;
    private EditText editTextWorkoutName, editTextDescription, editTextDuration;
    private Spinner spinnerDifficulty;
    private Button btnSaveWorkout, btnCancel, btnSelectTime;
    private FirebaseHelper firebaseHelper;
    
    private Calendar selectedTime = Calendar.getInstance();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private boolean isTimeSelected = false;
    
    private String workoutId = null;
    private WorkoutPlan workoutPlan;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_form);

        // Initialize Firebase helper
        firebaseHelper = new FirebaseHelper();

        // Initialize views
        titleTextView = findViewById(R.id.titlepage_workout_form);
        editTextWorkoutName = findViewById(R.id.editTextWorkoutName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDuration = findViewById(R.id.editTextDuration);
        textViewSelectedTime = findViewById(R.id.textViewSelectedTime);
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty);
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout);
        btnCancel = findViewById(R.id.btnCancel);
        btnSelectTime = findViewById(R.id.btnSelectTime);

        // Set up difficulty spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapter);

        // Check if we're in edit mode
        if (getIntent().hasExtra("workout_id")) {
            workoutId = getIntent().getStringExtra("workout_id");
            isEditMode = true;
            titleTextView.setText("Edit Workout Plan");
            loadWorkoutData();
        } else {
            titleTextView.setText("Add Workout Plan");
        }

        // Set click listeners
        btnSaveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkoutPlan();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    private void loadWorkoutData() {
        if (workoutId != null) {
            // Show loading indicator or disable UI
            btnSaveWorkout.setEnabled(false);
            
            firebaseHelper.getWorkoutPlan(workoutId, new FirebaseHelper.OnWorkoutPlanListener() {
                @Override
                public void onSuccess(WorkoutPlan plan) {
                    workoutPlan = plan;
                    
                    if (workoutPlan != null) {
                        editTextWorkoutName.setText(workoutPlan.getName());
                        editTextDescription.setText(workoutPlan.getDescription());
                        editTextDuration.setText(String.valueOf(workoutPlan.getDuration()));
                        
                        // Set the workout time if it exists
                        if (workoutPlan.getWorkoutTime() != null && !workoutPlan.getWorkoutTime().isEmpty()) {
                            textViewSelectedTime.setText(workoutPlan.getWorkoutTime());
                            isTimeSelected = true;
                        }
                        
                        // Set spinner selection based on difficulty
                        ArrayAdapter adapter = (ArrayAdapter) spinnerDifficulty.getAdapter();
                        int position = adapter.getPosition(workoutPlan.getDifficulty());
                        spinnerDifficulty.setSelection(position >= 0 ? position : 0);
                    }
                    
                    // Enable UI
                    btnSaveWorkout.setEnabled(true);
                }
                
                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(WorkoutFormActivity.this, "Error loading workout plan: " + errorMessage, Toast.LENGTH_SHORT).show();
                    // Enable UI
                    btnSaveWorkout.setEnabled(true);
                }
            });
        }
    }

    private void saveWorkoutPlan() {
        String name = editTextWorkoutName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String difficulty = spinnerDifficulty.getSelectedItem().toString();
        String durationStr = editTextDuration.getText().toString().trim();
        String workoutTime = textViewSelectedTime.getText().toString().trim();

        // Validate input
        if (name.isEmpty()) {
            editTextWorkoutName.setError("Please enter workout name");
            return;
        }

        if (description.isEmpty()) {
            editTextDescription.setError("Please enter description");
            return;
        }

        if (durationStr.isEmpty()) {
            editTextDuration.setError("Please enter duration");
            return;
        }
        
        if (!isTimeSelected) {
            Toast.makeText(this, "Please select a workout time", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = Integer.parseInt(durationStr);
        
        // Disable save button to prevent multiple submissions
        btnSaveWorkout.setEnabled(false);

        if (isEditMode && workoutPlan != null) {
            // Update existing workout plan
            workoutPlan.setName(name);
            workoutPlan.setDescription(description);
            workoutPlan.setDifficulty(difficulty);
            workoutPlan.setDuration(duration);
            workoutPlan.setWorkoutTime(workoutTime);
            
            firebaseHelper.updateWorkoutPlan(workoutPlan, new FirebaseHelper.OnWorkoutPlanListener() {
                @Override
                public void onSuccess(WorkoutPlan workoutPlan) {
                    Toast.makeText(WorkoutFormActivity.this, "Workout plan updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
                
                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(WorkoutFormActivity.this, "Error updating workout plan: " + errorMessage, Toast.LENGTH_SHORT).show();
                    btnSaveWorkout.setEnabled(true);
                }
            });
        } else {
            // Create new workout plan
            WorkoutPlan newWorkoutPlan = new WorkoutPlan(name, description, difficulty, duration, workoutTime);
            
            firebaseHelper.addWorkoutPlan(newWorkoutPlan, new FirebaseHelper.OnWorkoutPlanListener() {
                @Override
                public void onSuccess(WorkoutPlan workoutPlan) {
                    Toast.makeText(WorkoutFormActivity.this, "Workout plan added", Toast.LENGTH_SHORT).show();
                    finish();
                }
                
                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(WorkoutFormActivity.this, "Error adding workout plan: " + errorMessage, Toast.LENGTH_SHORT).show();
                    btnSaveWorkout.setEnabled(true);
                }
            });
        }
    }
    
    /**
     * Show time picker dialog to select workout time
     */
    private void showTimePickerDialog() {
        int hour = selectedTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedTime.get(Calendar.MINUTE);
        
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedTime.set(Calendar.MINUTE, minute);
                        
                        // Format the time and display it
                        String formattedTime = timeFormat.format(selectedTime.getTime());
                        textViewSelectedTime.setText(formattedTime);
                        isTimeSelected = true;
                    }
                }, hour, minute, false);
        
        timePickerDialog.setTitle("Select Workout Time");
        timePickerDialog.show();
    }
}
