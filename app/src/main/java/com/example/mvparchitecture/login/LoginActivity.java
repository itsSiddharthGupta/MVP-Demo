package com.example.mvparchitecture.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvparchitecture.database.AppDatabase;
import com.example.mvparchitecture.R;
import com.example.mvparchitecture.User;
import com.example.mvparchitecture.database.UserDao;
import com.example.mvparchitecture.databinding.ActivityMainBinding;
import com.example.mvparchitecture.profile.ProfileActivity;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserDao dao = AppDatabase.getInstance(this).getUserDao();
        LoginActivityContract.Presenter presenter = new LoginActivityPresenter(this, dao);
        binding.btnLogin.setOnClickListener(view -> {
            presenter.doLogin(binding.editTextEmail.getText().toString(),
                    binding.editTextPassword.getText().toString(),
                    binding.editTextName.getText().toString(),
                    binding.editTextMobile.getText().toString());
        });
    }

    @Override
    public void onSuccess(User user) {
        startActivity(new Intent(this, ProfileActivity.class).putExtra("email", user.getEmail()));
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}