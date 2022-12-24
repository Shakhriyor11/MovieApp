package com.my_portfolio.movieapp.fragments.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.my_portfolio.movieapp.MainPageActivity;
import com.my_portfolio.movieapp.R;
import com.my_portfolio.movieapp.data.user_data.UserDao;
import com.my_portfolio.movieapp.data.user_data.UserDatabase;
import com.my_portfolio.movieapp.data.user_data.UserEntity;
import com.my_portfolio.movieapp.fragments.main_page.MainPageFragment;

public class LoginFragment extends Fragment {

    private EditText etLogInMail;
    private EditText etPassword;
    private TextView signUp;
    private TextView forgotPassword;
    private AppCompatButton logInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etLogInMail = view.findViewById(R.id.logInMail);
        etPassword = view.findViewById(R.id.logInPassword);
        forgotPassword = view.findViewById(R.id.forgotPassword);
        signUp = view.findViewById(R.id.signUp);
        logInButton = view.findViewById(R.id.logInButton);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ForgotPasswordFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.commit();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SignUpFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.commit();
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMail = etLogInMail.getText().toString();
                String password = etPassword.getText().toString();
                if (userMail.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                } else {
                    UserDatabase userDataBase = UserDatabase.getUserDatabase(getContext());
                    final UserDao userDao = userDataBase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.getUser(userMail,password);
                            if (userEntity == null) {
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "Неверное пароль или логин", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(getContext(), MainPageActivity.class);
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
            }
        });

        return view;
    }
}