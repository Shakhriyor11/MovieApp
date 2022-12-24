package com.my_portfolio.movieapp.data.user_data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void userRegister(UserEntity userEntity);

    @Query("SELECT * FROM user WHERE userEmail=(:userEmail) and userPassword=(:userPassword)")
    UserEntity getUser(String userEmail,String userPassword);
}
