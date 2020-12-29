package com.example.mvparchitecture.profile;

import com.example.mvparchitecture.User;

public interface ProfileActivityContract {
    interface View {
        void onFetchSuccess(User user);

        void onFetchFailure(String msg);
    }

    interface Presenter {
        void fetchUserDetails();
        void deleteUser(User user);
    }
}
