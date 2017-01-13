package com.example.victo.salarymanagement.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victo.salarymanagement.Activities.MainMenuForEmployees;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {
    //TAGS
    private static final String TAG = "Authentication" ;
    private static final String KEY= "Credentials";

    //Layout variables
    EditText etUserName, etPassword;
    Button btnLogin;


    //Authentication attributes
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in,container,false);

        // Inflate the layout for this fragment
        etUserName = (EditText) view.findViewById(R.id.eTuserName);
        etPassword = (EditText) view.findViewById(R.id.eTpassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                Log.d(TAG, "Logging in with email: "+email);
                Log.d(TAG, "Logging in with password: "+password);
                signIn(email, password);
            }
        });
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
        return view;
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "Sign in");
        if (validateFormLogIn()) {
            Log.d(TAG, "Entered SignIn");
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "onComplete: "+task.toString());
                            if(task.isSuccessful()){
                                String loggedInUser = mAuth.getCurrentUser().getEmail();


                                Intent myIntent = new Intent(getContext(),MainMenuForEmployees.class);
                                myIntent.putExtra(KEY,"email");
                                startActivity(myIntent);
                            } else {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
    /*
    public int getBusinessRoleFromUser (String email){
        ArrayList<User> myUsers = new ArrayList<>();
        myUsers = DatabaseManager.getInstance().getUsers();
        for (int i = 0; i < myUsers.size(); i++) {

        }

    }*/
    private boolean validateFormLogIn() {
        boolean valid = true;
        String email = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etUserName.setError("Required");
            valid = false;
        } else {
            etUserName.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    private void clearWindowLogIn() {
        etUserName.setText("");
        etPassword.setText("");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "On Start");
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {

            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_logout:
                signOut();
                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_help:
                Toast.makeText(getContext(), "Help: work in progress", Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }*/

}
