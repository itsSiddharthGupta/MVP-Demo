package com.example.mvparchitecture.login;

import com.example.mvparchitecture.User;
import com.example.mvparchitecture.database.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {
    private LoginActivityContract.Model user;
    private final LoginActivityContract.View view;
    private final ExecutorService executors;
    private final UserDao dao;

    public LoginActivityPresenter(LoginActivityContract.View view, UserDao dao) {
        this.view = view;
        executors = Executors.newSingleThreadExecutor();
        this.dao = dao;
    }

    @Override
    public void doLogin(String email, String password, String name, String mobileNo) {
        user = new User(email, password, name, mobileNo);
        if (user.validate()) {
            executors.execute(() -> {
                dao.saveUser((User) user);
                view.onSuccess((User) user);
            });
        } else {
            view.onFailure("Wrong email or password!");
        }
    }
}
