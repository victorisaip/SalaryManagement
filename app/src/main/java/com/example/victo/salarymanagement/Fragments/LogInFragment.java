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
import android.widget.Toast;

import com.example.victo.salarymanagement.Activities.MainMenuForEmployees;
import com.example.victo.salarymanagement.Activities.MenuForManagers;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {
    //TAGS
    private static final String TAG = "Authentication";
    private static final String KEY = "Credentials";

    //Layout variables
    EditText etUserName, etPassword;
    Button btnLogin;
    //Authentication attributes
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user ;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public  DatabaseReference myRefusers;

    //Firebase Database


    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        // Inflate the layout for this fragment
        etUserName = (EditText) view.findViewById(R.id.eTuserName);
        etPassword = (EditText) view.findViewById(R.id.eTpassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogIn);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Salary_Management_DB");
        myRefusers = myRef.child("Users");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                signIn(email, password);

            }
        });
        //Authentication
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    String email = user.getEmail();
                    Log.d(TAG, "User email: " + email);

                    myRefusers.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.d(TAG, "Listening for registered users " + dataSnapshot.getChildrenCount());
                            Log.d(TAG, "===========================================");
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                User post = postSnapshot.getValue(User.class);

                                Log.d(TAG, "Email : " + post.getEmail()+", Name: "+post.getName());
                                if(user.getEmail().equals(post.getEmail())){
                                    Log.d(TAG, "Business Role: "+post.getBusinessRole());
                                    if(post.getBusinessRole().equals("manager")){
                                        myRefusers.removeEventListener(this);
                                        mAuth.removeAuthStateListener(mAuthListener);
                                        Intent i = new Intent(getActivity(), MenuForManagers.class);
                                        startActivity(i);
                                    } else {
                                        if(post.getBusinessRole().equals("employee")){

                                            mAuth.removeAuthStateListener(mAuthListener);

                                            Intent i = new Intent(getActivity(),MainMenuForEmployees.class);
                                            startActivity(i);
                                        }
                                    }
                                }
                            }
                            Log.d(TAG, "===========================================");
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, "onCancelled: "+ "operation with error!");
                            Log.d(TAG, "===========================================");
                        }
                    });

                } else {

                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        return view;
    }

    private void signIn(final String email, String password) {

        Log.d(TAG, "Executing sign in method");
        Log.d(TAG, "========================================");
        if (validateFormLogIn()) {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "onComplete: " + task.toString());

                            if(!task.isSuccessful()){
                                Log.w(TAG, "signInWithEmail");
                                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }
    }

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
        mAuth.signOut();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

}
