package com.example.paindiary;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public String uid;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "domain")
    public String domain;

}
