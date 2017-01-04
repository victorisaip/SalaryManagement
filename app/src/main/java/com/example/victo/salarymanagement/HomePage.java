package com.example.victo.salarymanagement;
/*
Project name:  Salary Management
Description: Apps to manage the employment salary according to the total hours worked.
Developers: Victor , Saul , Ramesh */
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {
    //Constants
    private static final String TAG = "Authentication" ;

    //Authentication attributes
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    //Database attributes
    DatabaseManager myManager;

    //Layout attributes
    TextView tvUserName,tvPassword,tvRegisterName,tvRegisterEmail,tvRegisterPassword,tvBusinessRole;
    EditText etUserName, etPassword,etRegisterName,etRegisterEmail,etRegisterPassword;
    CheckBox chbManager,chbEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Layout Elements
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvRegisterName = (TextView) findViewById(R.id.tvName);
        tvRegisterEmail = (TextView) findViewById(R.id.tvRegisterEmail);
        tvRegisterPassword = (TextView) findViewById(R.id.tvRegisterPassword);
        tvBusinessRole = (TextView) findViewById(R.id.tvRegisterBusinessRole);
        etUserName = (EditText) findViewById(R.id.eTuserName);
        etPassword = (EditText) findViewById(R.id.eTpassword);
        etRegisterName = (EditText) findViewById(R.id.eTName);
        etRegisterEmail = (EditText) findViewById(R.id.eTregisterEmail);
        etRegisterPassword = (EditText) findViewById(R.id.eTRegisterPassword);
        chbManager = (CheckBox) findViewById(R.id.chbManager);
        chbEmployee = (CheckBox) findViewById(R.id.chbEmployee);


        //Realtime Database
        myManager = DatabaseManager.getInstance();
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

                    Toast.makeText(HomePage.this, "Succesfully signed in by: "+email, Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            signOut();
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void logIn(View view) {
        String email = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        signIn(email,password);

        myManager.readUser(email);
    }

    public void registerUser(View view) {
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String name = etRegisterName.getText().toString();
        if(validateFormRegister()){
            if(checkBusinessRole()){

                if(chbEmployee.isChecked()){
                     myManager.createUser(email,password,name,"employee");
                    createAccount(name,email,password,"employee");


                } else {
                    myManager.createUser(email,password,name,"manager");
                    createAccount(name,email,password,"manager");
                }
            }
        }
    }

    private void signIn(String email,String password){

        if(validateFormLogIn()){
            mAuth.signInWithEmailAndPassword(etUserName.getText().toString(),etPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            clearWindowLogIn();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Toast.makeText(HomePage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    private void signOut(){
        mAuth.signOut();
    }

    private boolean checkBusinessRole(){
        boolean valid = true;
        if(!chbEmployee.isChecked() && !chbManager.isChecked()){
            Toast.makeText(this, "You have to select your business role", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            chbEmployee.setError(null);
            chbManager.setError(null);
        }

        if(chbManager.isChecked() && chbEmployee.isChecked()){
            Toast.makeText(this, "You can not have both business roles", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            chbEmployee.setError(null);
            chbManager.setError(null);
        }
        return valid;
    }

    private boolean validateFormLogIn(){
        boolean valid = true;
        String email = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            etUserName.setError("Required");
            valid = false;
        } else {
            etUserName.setError(null);
        }

        if(TextUtils.isEmpty(password)){
            etPassword.setError("Required");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

    private boolean validateFormRegister(){
        boolean valid = true;
        String email = etRegisterEmail.getText().toString();
        if(TextUtils.isEmpty(email)){
            etRegisterEmail.setError("Required");
            valid = false;
        } else {
            etRegisterEmail.setError(null);
        }

        String password = etRegisterPassword.getText().toString();
        if(TextUtils.isEmpty(password)){
            etRegisterPassword.setError("Required");
            valid = false;
        } else {
            etRegisterPassword.setError(null);
        }

        String name = etRegisterName.getText().toString();
        if(TextUtils.isEmpty(name)){
            etRegisterName.setError("Required");
            valid = false;
        } else {
            etRegisterName.setError(null);
        }
        return valid;
    }

    private void createAccount(String name, String email, String password, String businessRole){
        if(validateFormRegister()){
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            clearWindowRegister();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(HomePage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
    public void logOut(View view) {
        signOut();
    }

    private void clearWindowLogIn(){
        etUserName.setText("");
        etPassword.setText("");
    }

    private void clearWindowRegister(){
        etRegisterName.setText("");
        etRegisterPassword.setText("");
        etRegisterEmail.setText("");
        chbEmployee.setChecked(false);
        chbManager.setChecked(false);
    }
}
