package com.example.mvparchitecture.profile;

import com.example.mvparchitecture.User;
import com.example.mvparchitecture.database.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfileActivityPresenter implements ProfileActivityContract.Presenter {
    private final ProfileActivityContract.View view;
    private final ExecutorService executors;
    private final UserDao dao;

    public ProfileActivityPresenter(ProfileActivityContract.View view, UserDao dao) {
        this.view = view;
        this.executors = Executors.newSingleThreadExecutor();
        this.dao = dao;
    }

    @Override
    public void fetchUserDetails(String email) {
        executors.execute(() -> {
            view.onFetchSuccess(dao.getUser(email));
        });
    }

    @Override
    public void deleteUser() {
        executors.execute(() -> {
            dao.clearData();
        });
    }
}
