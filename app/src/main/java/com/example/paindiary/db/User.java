package com.example.paindiary.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int mood;

    public int steps;

    public int today_steps;

    public String painLocation;

    public String painLevel;

    public String email;

    public String username;

    public String date;

    public float temp;

    public float humidity;

    public float pressure;
}
