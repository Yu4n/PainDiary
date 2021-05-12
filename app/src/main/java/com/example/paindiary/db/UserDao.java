package com.example.paindiary.db;

import android.database.Cursor;
import android.util.Pair;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    public class Count_Pain{
        public String painLocation;
        public int count;
    }
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email =:emails")
    List<User> loadAllByIds(String emails);

    @Query("SELECT painLocation, COUNT(*) as count FROM user WHERE email =:emails group by painLocation")
    List<Count_Pain> loadLocationsByIds(String emails);

    @Query("SELECT date FROM user WHERE email =:emails ORDER BY date DESC LIMIT 1")
    String getDate(String emails);

    @Query("SELECT * FROM user WHERE email =:emails ORDER BY date DESC LIMIT 1")
    User getSteps(String emails);

    @Query("delete from user where email = :emails")
    void deleteUser(String emails);

    @Insert
    void insert(User user);

    @Query("update User set painLocation = :painLocation, painLevel = :painLevel, mood = :mood, steps = :Steps, today_steps = :todaySteps where email =:emails and date = :date ")
    public void updateUsers(String emails, String painLocation, String painLevel, int mood, String date, int Steps, int todaySteps);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    public void nukeTable();
}