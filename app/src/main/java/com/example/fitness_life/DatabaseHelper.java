package com.example.fitness_life;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Database helper class for workout plan CRUD operations
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitness_life.db";
    private static final int DATABASE_VERSION = 2; // Increment version to trigger database upgrade

    // Table name
    private static final String TABLE_WORKOUT_PLANS = "workout_plans";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DIFFICULTY = "difficulty";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_WORKOUT_TIME = "workout_time";

    // Create table query
    private static final String CREATE_TABLE_WORKOUT_PLANS = "CREATE TABLE " + TABLE_WORKOUT_PLANS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_DIFFICULTY + " TEXT,"
            + COLUMN_DURATION + " INTEGER,"
            + COLUMN_WORKOUT_TIME + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_WORKOUT_PLANS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_PLANS);
        onCreate(db);
    }

    /**
     * Add a new workout plan to the database
     * @param workoutPlan The workout plan to add
     * @return The ID of the newly added workout plan
     */
    public long addWorkoutPlan(WorkoutPlan workoutPlan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, workoutPlan.getName());
        values.put(COLUMN_DESCRIPTION, workoutPlan.getDescription());
        values.put(COLUMN_DIFFICULTY, workoutPlan.getDifficulty());
        values.put(COLUMN_DURATION, workoutPlan.getDuration());
        values.put(COLUMN_WORKOUT_TIME, workoutPlan.getWorkoutTime());

        // Insert row
        long id = db.insert(TABLE_WORKOUT_PLANS, null, values);
        db.close();
        return id;
    }

    /**
     * Get a single workout plan by ID
     * @param id The ID of the workout plan to retrieve
     * @return The workout plan with the specified ID
     */
    public WorkoutPlan getWorkoutPlan(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_WORKOUT_PLANS,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_DIFFICULTY, COLUMN_DURATION, COLUMN_WORKOUT_TIME},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        WorkoutPlan workoutPlan = null;
        
        if (cursor != null && cursor.moveToFirst()) {
            workoutPlan = new WorkoutPlan(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULTY)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DURATION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WORKOUT_TIME)));
            cursor.close();
        }
        
        return workoutPlan;
    }

    /**
     * Get all workout plans
     * @return A list of all workout plans
     */
    public List<WorkoutPlan> getAllWorkoutPlans() {
        List<WorkoutPlan> workoutPlans = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_WORKOUT_PLANS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WorkoutPlan workoutPlan = new WorkoutPlan();
                workoutPlan.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                workoutPlan.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                workoutPlan.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                workoutPlan.setDifficulty(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIFFICULTY)));
                workoutPlan.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DURATION)));
                workoutPlan.setWorkoutTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WORKOUT_TIME)));

                workoutPlans.add(workoutPlan);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return workoutPlans;
    }

    /**
     * Update a workout plan
     * @param workoutPlan The workout plan to update
     * @return The number of rows affected
     */
    public int updateWorkoutPlan(WorkoutPlan workoutPlan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, workoutPlan.getName());
        values.put(COLUMN_DESCRIPTION, workoutPlan.getDescription());
        values.put(COLUMN_DIFFICULTY, workoutPlan.getDifficulty());
        values.put(COLUMN_DURATION, workoutPlan.getDuration());
        values.put(COLUMN_WORKOUT_TIME, workoutPlan.getWorkoutTime());

        // Update row
        return db.update(TABLE_WORKOUT_PLANS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(workoutPlan.getId())});
    }

    /**
     * Delete a workout plan
     * @param workoutPlan The workout plan to delete
     */
    public void deleteWorkoutPlan(WorkoutPlan workoutPlan) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKOUT_PLANS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(workoutPlan.getId())});
        db.close();
    }

    /**
     * Get the count of workout plans
     * @return The number of workout plans
     */
    public int getWorkoutPlansCount() {
        String countQuery = "SELECT * FROM " + TABLE_WORKOUT_PLANS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
