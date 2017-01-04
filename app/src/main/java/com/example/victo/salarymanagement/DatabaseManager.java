package com.example.victo.salarymanagement;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by victo on 1/2/2017.
 */

public class DatabaseManager {

    private static final String TAG ="Database" ;
    private static DatabaseManager dbManager;
    //Firebase attributes
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<User> users = new ArrayList<User>();
    private DatabaseManager(){
        Log.d(TAG, "Constructor Enters");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Salary_Management_DB");
        myRef.addValueEventListener(new ValueEventListener() {
            String[] arr = new String[6];

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user;
                String id;
                users = new ArrayList<User>();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    String json = child.getValue().toString();
                    Log.d(TAG,"Jason: "+json);
                    arr = parseUsersFromJson(json);
                    user = new User(arr[1],arr[2],arr[0],arr[3]);
                    Log.d(TAG,"User: "+user.getEmail());
                    id = child.getKey();

                    user.setId(id);
                    Log.d(TAG,"Key: "+user.getId());
                    users.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    protected static DatabaseManager getInstance(){
        if(dbManager==null){
            Log.d(TAG, "Get instance, Enters");
            dbManager = new DatabaseManager();
        }
        return dbManager;
    }

    public void createUser(String email, String password,String name,String businessRole){
        Log.d(TAG, "Create user: Enters");
        User myUser = new User(email,password,name,businessRole);
        myRef.push().setValue(myUser);
        users.add(myUser);
    }

    public void readUser(String email){
        int pos = getPos(email);
        String key = users.get(pos).getId();
        User searched = new User();
        searched = users.get(pos);
        Log.d(TAG,"Email: "+searched.getEmail());
    }

    public int getPos (String email){

        int i = 0;
        boolean flag = false;

        while(i<users.size()-1 && flag == false){
            if(users.get(i).getEmail().equals(email)){
                flag = true;
            } else {
                i = i + 1;
            }
        }
        return i;
    }

    public String[] parseUsersFromJson(String json){
        String parsedJson[] = new String[6];
        int pos = 0;
        char character;
        char character2;
        int j;

        for(int i = 0; i<json.length();i++){
            character = json.charAt(i);

            if(character == '='){
                j = i+1;
                boolean flag = false;
                character2 = json.charAt(j);
                while(j<json.length() && flag == false){
                    if(character2==',' || character2 =='}'){
                        flag = true;
                    } else {
                        j = j + 1;
                        character2 = json.charAt(j);
                    }
                }
                parsedJson[pos] = json.substring(i+1,j);
                pos = pos + 1;
            }
        }
        return parsedJson;
    }
}
