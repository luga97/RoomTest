package com.test.room;

import androidx.room.*;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Insert
    void insetAll(User... user);
}
