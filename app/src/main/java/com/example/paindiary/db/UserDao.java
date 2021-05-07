package com.example.paindiary.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email =:emails")
    List<User> loadAllByIds(String emails);

    @Query("SELECT date FROM user WHERE email =:emails ORDER BY date DESC LIMIT 1")
    String getDate(String emails);

    @Query("delete from user where email = :emails")
    void deleteUser(String emails);

    @Insert
    void insert(User user);

    @Update
    public void updateUsers(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    public void nukeTable();
}