package com.example.fitness_life;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for Firebase Firestore operations
 */
public class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";
    private static final String COLLECTION_WORKOUT_PLANS = "workout_plans";

    private FirebaseFirestore db;

    public FirebaseHelper() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Add a new workout plan to Firestore
     * @param workoutPlan The workout plan to add
     * @param listener Callback for success/failure
     */
    public void addWorkoutPlan(WorkoutPlan workoutPlan, final OnWorkoutPlanListener listener) {
        // Convert workout plan to Map
        Map<String, Object> workoutData = new HashMap<>();
        workoutData.put("name", workoutPlan.getName());
        workoutData.put("description", workoutPlan.getDescription());
        workoutData.put("difficulty", workoutPlan.getDifficulty());
        workoutData.put("duration", workoutPlan.getDuration());
        workoutData.put("workoutTime", workoutPlan.getWorkoutTime());

        // Add to Firestore
        db.collection(COLLECTION_WORKOUT_PLANS)
                .add(workoutData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        workoutPlan.setFirebaseId(documentReference.getId());
                        if (listener != null) {
                            listener.onSuccess(workoutPlan);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        if (listener != null) {
                            listener.onFailure(e.getMessage());
                        }
                    }
                });
    }

    /**
     * Get all workout plans from Firestore
     * @param listener Callback with the list of workout plans
     */
    public void getAllWorkoutPlans(final OnWorkoutPlansListener listener) {
        db.collection(COLLECTION_WORKOUT_PLANS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<WorkoutPlan> workoutPlans = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WorkoutPlan workoutPlan = new WorkoutPlan();
                                workoutPlan.setFirebaseId(document.getId());
                                workoutPlan.setName(document.getString("name"));
                                workoutPlan.setDescription(document.getString("description"));
                                workoutPlan.setDifficulty(document.getString("difficulty"));
                                
                                // Handle Long to int conversion for duration
                                if (document.contains("duration")) {
                                    Object durationObj = document.get("duration");
                                    if (durationObj instanceof Long) {
                                        workoutPlan.setDuration(((Long) durationObj).intValue());
                                    } else if (durationObj instanceof Integer) {
                                        workoutPlan.setDuration((Integer) durationObj);
                                    }
                                }
                                
                                workoutPlan.setWorkoutTime(document.getString("workoutTime"));
                                workoutPlans.add(workoutPlan);
                            }
                            if (listener != null) {
                                listener.onSuccess(workoutPlans);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            if (listener != null) {
                                listener.onFailure(task.getException().getMessage());
                            }
                        }
                    }
                });
    }

    /**
     * Get a single workout plan by ID
     * @param firebaseId The Firestore ID of the workout plan
     * @param listener Callback with the workout plan
     */
    public void getWorkoutPlan(String firebaseId, final OnWorkoutPlanListener listener) {
        db.collection(COLLECTION_WORKOUT_PLANS)
                .document(firebaseId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                WorkoutPlan workoutPlan = new WorkoutPlan();
                                workoutPlan.setFirebaseId(document.getId());
                                workoutPlan.setName(document.getString("name"));
                                workoutPlan.setDescription(document.getString("description"));
                                workoutPlan.setDifficulty(document.getString("difficulty"));
                                
                                // Handle Long to int conversion for duration
                                if (document.contains("duration")) {
                                    Object durationObj = document.get("duration");
                                    if (durationObj instanceof Long) {
                                        workoutPlan.setDuration(((Long) durationObj).intValue());
                                    } else if (durationObj instanceof Integer) {
                                        workoutPlan.setDuration((Integer) durationObj);
                                    }
                                }
                                
                                workoutPlan.setWorkoutTime(document.getString("workoutTime"));
                                
                                if (listener != null) {
                                    listener.onSuccess(workoutPlan);
                                }
                            } else {
                                Log.d(TAG, "No such document");
                                if (listener != null) {
                                    listener.onFailure("Workout plan not found");
                                }
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                            if (listener != null) {
                                listener.onFailure(task.getException().getMessage());
                            }
                        }
                    }
                });
    }

    /**
     * Update a workout plan
     * @param workoutPlan The workout plan to update
     * @param listener Callback for success/failure
     */
    public void updateWorkoutPlan(WorkoutPlan workoutPlan, final OnWorkoutPlanListener listener) {
        // Convert workout plan to Map
        Map<String, Object> workoutData = new HashMap<>();
        workoutData.put("name", workoutPlan.getName());
        workoutData.put("description", workoutPlan.getDescription());
        workoutData.put("difficulty", workoutPlan.getDifficulty());
        workoutData.put("duration", workoutPlan.getDuration());
        workoutData.put("workoutTime", workoutPlan.getWorkoutTime());

        // Update in Firestore
        db.collection(COLLECTION_WORKOUT_PLANS)
                .document(workoutPlan.getFirebaseId())
                .update(workoutData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        if (listener != null) {
                            listener.onSuccess(workoutPlan);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        if (listener != null) {
                            listener.onFailure(e.getMessage());
                        }
                    }
                });
    }

    /**
     * Delete a workout plan
     * @param workoutPlan The workout plan to delete
     * @param listener Callback for success/failure
     */
    public void deleteWorkoutPlan(WorkoutPlan workoutPlan, final OnWorkoutPlanListener listener) {
        db.collection(COLLECTION_WORKOUT_PLANS)
                .document(workoutPlan.getFirebaseId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        if (listener != null) {
                            listener.onSuccess(workoutPlan);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                        if (listener != null) {
                            listener.onFailure(e.getMessage());
                        }
                    }
                });
    }

    /**
     * Interface for workout plan operations callbacks
     */
    public interface OnWorkoutPlanListener {
        void onSuccess(WorkoutPlan workoutPlan);
        void onFailure(String errorMessage);
    }

    /**
     * Interface for getting all workout plans callback
     */
    public interface OnWorkoutPlansListener {
        void onSuccess(List<WorkoutPlan> workoutPlans);
        void onFailure(String errorMessage);
    }
}
