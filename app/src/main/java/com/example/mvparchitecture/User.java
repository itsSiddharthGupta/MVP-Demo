package com.example.mvparchitecture;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mvparchitecture.login.LoginActivityContract;

@Entity(tableName = "users")
public class User implements LoginActivityContract.Model {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "email")
    private final String email;
    @ColumnInfo(name = "password")
    private final String password;
    @ColumnInfo(name = "name")
    private final String name;
    @ColumnInfo(name = "mobile_no")
    private final String mobileNo;

    public User(String email, String password, String name, String mobileNo){
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobileNo = mobileNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean validate() {
        return !TextUtils.isEmpty(this.email)
                && Patterns.EMAIL_ADDRESS.matcher(this.email).matches()
                && !TextUtils.isEmpty(this.password)
                && this.password.length() >= 3
                && !TextUtils.isEmpty(this.name)
                && !TextUtils.isEmpty(this.mobileNo)
                && this.mobileNo.length()==10;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
