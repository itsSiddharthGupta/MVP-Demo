package com.example.mvparchitecture.profile;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvparchitecture.database.AppDatabase;
import com.example.mvparchitecture.R;
import com.example.mvparchitecture.User;
import com.example.mvparchitecture.database.UserDao;
import com.example.mvparchitecture.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity implements ProfileActivityContract.View {
    private ActivityProfileBinding binding;
    private ProfileActivityPresenter presenter;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        String email = getIntent().getExtras().getString("email");
        UserDao dao = AppDatabase.getInstance(this).getUserDao();
        presenter = new ProfileActivityPresenter(this, dao);
        presenter.fetchUserDetails(email);
    }

    @Override
    public void onFetchSuccess(User user) {
        this.user = user;
        binding.textEmail.setText(user.getEmail());
        binding.textMobile.setText(user.getMobileNo());
        binding.textName.setText(user.getName());
        binding.textPassword.setText(user.getPassword());
    }

    @Override
    public void onFetchFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (user != null)
            presenter.deleteUser();
    }
}
