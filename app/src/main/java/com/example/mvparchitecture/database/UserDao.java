package com.example.mvparchitecture.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mvparchitecture.User;

@Dao
public interface UserDao {
    @Insert
    void saveUser(User user);

    @Query("SELECT * FROM users where users.email = :email limit 1")
    User getUser(String email);

    @Query("DELETE FROM users")
    void clearData();
}
