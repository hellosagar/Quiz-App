package com.sagarkhurana.quizforfun.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAttempt(Attempt attempt);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user")
    List<User> observeAllUser();

    @Delete
    void deleteUser(User user);

    @Transaction
    @Query("SELECT DISTINCT *  FROM attempt WHERE email = :email")
    List<Attempt> getUserAndAttemptsWithSameEmail(String email);

    @Transaction
    @Query("SELECT SUM(earned) FROM attempt WHERE email = :email")
    int getOverAllPoints(String email);

}
