package com.example.mvparchitecture.login;

import com.example.mvparchitecture.User;

public interface LoginActivityContract {
    interface View {
        void onSuccess(User user);
        void onFailure(String msg);
    }

    interface Presenter {
        void doLogin(String email, String password, String name, String mobileNo);
    }

    interface Model {
        boolean validate();
    }
}
