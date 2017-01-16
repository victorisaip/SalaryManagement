package com.example.victo.salarymanagement.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    private static final String TAG ="Register" ;
    //Layout attributes
    TextView tvRegisterName, tvRegisterEmail, tvRegisterPassword, tvBusinessRole;
    EditText etRegisterName, etRegisterEmail, etRegisterPassword;
    CheckBox chbManager, chbEmployee;


    Button btnRegister;
    //Database Instance
    DatabaseManager myManager;

    //Authentication attributes
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        btnRegister = (Button) view.findViewById(R.id.btnRegisterUser);
        tvRegisterName = (TextView) view.findViewById(R.id.tvName);
        tvRegisterEmail = (TextView) view.findViewById(R.id.tvRegisterEmail);
        tvRegisterPassword = (TextView) view.findViewById(R.id.tvRegisterPassword);
        tvBusinessRole = (TextView) view.findViewById(R.id.tvRegisterBusinessRole);
        etRegisterName = (EditText) view.findViewById(R.id.eTName);
        etRegisterEmail = (EditText) view.findViewById(R.id.eTregisterEmail);
        etRegisterPassword = (EditText) view.findViewById(R.id.eTRegisterPassword);
        chbManager = (CheckBox) view.findViewById(R.id.chbManager);
        chbEmployee = (CheckBox) view.findViewById(R.id.chbEmployee);


        //Authentication
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    //Sign in is succeed
                    String email = user.getEmail();

                    //Toast.makeText(LogInFragment.this, "Succesfully signed in by: " + email, Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etRegisterEmail.getText().toString();
                String password = etRegisterPassword.getText().toString();
                String name = etRegisterName.getText().toString();
                if (validateFormRegister()) {
                    Log.d(TAG, "Form validated");
                    if (checkBusinessRole()) {
                        Log.d(TAG, "Business role selected");
                        if (chbEmployee.isChecked()) {
                            Log.d(TAG, "Employee");
                            createAccount(email, password);
                            myManager.getInstance().
                                    createUser(email, password, name, "employee");


                        } else {
                            Log.d(TAG, "Manager");
                            createAccount(email, password);
                            myManager.getInstance().
                                    createUser(email, password, name, "manager");

                        }
                    }
                }
            }
        });



        return view ;
    }


    private boolean checkBusinessRole() {
        boolean valid = true;
        if (!chbEmployee.isChecked() && !chbManager.isChecked()) {
            Toast.makeText(getContext(), "You have to select your business role", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            chbEmployee.setError(null);
            chbManager.setError(null);
        }

        if (chbManager.isChecked() && chbEmployee.isChecked()) {
            Toast.makeText(getContext(), "You can not have both business roles", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            chbEmployee.setError(null);
            chbManager.setError(null);
        }
        return valid;
    }




    private boolean validateFormRegister() {
        boolean valid = true;
        String email = etRegisterEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etRegisterEmail.setError("Required");
            valid = false;
        } else {
            etRegisterEmail.setError(null);
        }

        String password = etRegisterPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            etRegisterPassword.setError("Required");
            valid = false;
        } else {
            etRegisterPassword.setError(null);
        }

        String name = etRegisterName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etRegisterName.setError("Required");
            valid = false;
        } else {
            etRegisterName.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "Before mAuth ");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        } else {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        clearWindowRegister();
                    }
                });

    }

    private void clearWindowRegister() {
        etRegisterName.setText("");
        etRegisterPassword.setText("");
        etRegisterEmail.setText("");
        chbEmployee.setChecked(false);
        chbManager.setChecked(false);
    }


}
