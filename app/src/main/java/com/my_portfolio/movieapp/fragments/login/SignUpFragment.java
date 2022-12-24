package com.my_portfolio.movieapp.fragments.login;

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

import com.my_portfolio.movieapp.R;
import com.my_portfolio.movieapp.data.user_data.UserDao;
import com.my_portfolio.movieapp.data.user_data.UserDatabase;
import com.my_portfolio.movieapp.data.user_data.UserEntity;

public class SignUpFragment extends Fragment {

    private EditText etEmail;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhoneNumber;
    private EditText etPassword;
    private AppCompatButton signUpButton;
    private TextView goToLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        etEmail = view.findViewById(R.id.etEmail);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        etPassword = view.findViewById(R.id.etPassword);
        signUpButton = view.findViewById(R.id.signUpButton);
        goToLogin = view.findViewById(R.id.have_account_Login);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new LoginFragment());
                fragmentTransaction.commit();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserEmail(etEmail.getText().toString());
                userEntity.setFirstName(etFirstName.getText().toString());
                userEntity.setLastName(etLastName.getText().toString());
                userEntity.setPhoneNumber(etPhoneNumber.getText().toString());
                userEntity.setUserPassword(etPassword.getText().toString());
                if (validateInput(userEntity)){
                    UserDatabase userDataBase = UserDatabase.getUserDatabase(getContext());
                    final UserDao userDao = userDataBase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.userRegister(userEntity);
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "User Registred!", Toast.LENGTH_SHORT).show();
                                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                                    ft.replace(R.id.container,new LoginFragment());
                                    ft.commit();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(getContext(), "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getUserEmail().isEmpty() || userEntity.getFirstName().isEmpty()
                || userEntity.getLastName().isEmpty() || userEntity.getPhoneNumber().isEmpty()
                || userEntity.getUserPassword().isEmpty()) {
            return false;
        }
        return true;
    }

}