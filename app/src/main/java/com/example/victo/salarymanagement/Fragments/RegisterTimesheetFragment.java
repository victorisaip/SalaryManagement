

package com.example.victo.salarymanagement.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.Email;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTimesheetFragment extends Fragment {
    Email myemail ;
    private static final String TAG = "RegisterTM";
    //Layout variables
    Button btnRegisterTimesheet;
    EditText startDate, etEndDate, etStatus, etMonday, etTuesday, etWednesday, etThursday, etFriday,etTotalHours,etActualDate;
    DatePickerFragment dialog;
    static Spinner spApprover;
    String mstartDate, mEndDate, mApprover, mMonday, mTuesday, mWednesday, mThursday, mFriday,mTotalHours;
    double hoursMonday, hoursTuesday, hoursWednesday, hoursThursday, hoursFriday;
    double finalSum = 0;
    private static final String DIALOG_DATE = "DialogDate";
    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    private static ArrayList<User> myManagers;
    private static Context context;
    Button sendEmailBtn;

    public RegisterTimesheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_timesheet, container, false);
        RegisterTimesheetFragment.context = getContext();
        etEndDate = (EditText) view.findViewById(R.id.etEndDate);
        sendEmailBtn = (Button) view.findViewById(R.id.btnSendEmail);

        startDate = (EditText) view.findViewById(R.id.etStartDate);
        etStatus = (EditText) view.findViewById(R.id.etStatus);
        etTotalHours = (EditText) view.findViewById(R.id.etNumberOfHours);
        btnRegisterTimesheet = (Button) view.findViewById(R.id.btnRegisterTimeSheet);
        etMonday = (EditText) view.findViewById(R.id.etMonday);
        etTuesday = (EditText) view.findViewById(R.id.etTuesday);
        etWednesday = (EditText) view.findViewById(R.id.etWednesday);
        etThursday = (EditText) view.findViewById(R.id.etThursday);
        etFriday = (EditText) view.findViewById(R.id.etFriday);
        etActualDate = (EditText) view.findViewById(R.id.etActualDate);
        spApprover = (Spinner)view.findViewById(R.id.SpinSelectApprover);

        mstartDate = startDate.getText().toString();
        mEndDate = etEndDate.getText().toString();
       // mApprover = etApprover.getText().toString();
        etMonday.setText("0.0");
        etTuesday.setText("0.0");
        etWednesday.setText("0.0");
        etThursday.setText("0.0");
        etFriday.setText("0.0");
        etTotalHours.setText("0.0");
        etStatus.setText("created");
        mMonday = etMonday.getText().toString();
        mTuesday = etTuesday.getText().toString();
        mWednesday = etWednesday.getText().toString();
        mThursday = etThursday.getText().toString();
        mFriday = etFriday.getText().toString();


        Date actualDate = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("MM-dd-yyyy");
        final String receivedResult = dt1.format(actualDate);

        etActualDate.setText(receivedResult);
        btnRegisterTimesheet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("Ramesh","OnClick Listners");
                Toast.makeText(getContext(),
                        "OnClickListener : " +

                                "\nYour Approver "+ String.valueOf(spApprover.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();

                String mangerFromSpinner = String.valueOf(spApprover.getSelectedItem());

                mstartDate = startDate.getText().toString();
                mEndDate = etEndDate.getText().toString();
               /// mApprover = etApprover.getText().toString();
                mMonday = etMonday.getText().toString();
                mTuesday = etTuesday.getText().toString();
                mWednesday = etWednesday.getText().toString();
                mThursday = etThursday.getText().toString();
                mFriday = etFriday.getText().toString();
                mTotalHours = etTotalHours.getText().toString();

                DatabaseManager.getInstance().createTimeSheet(mstartDate, mEndDate,
                        mangerFromSpinner, receivedResult, mMonday, mTuesday, mWednesday, mThursday, mFriday,mTotalHours,email);

                String myemailBody = "Hi "+spApprover.getSelectedItem().toString().split("@")[0]+",\n"+"Please find the Time sheet details for the week"+
                        " as below : \n\n ___________________________\n"+"Mon : " + mMonday+ "hrs\n"
                        +"Tue : " + mTuesday+ "hrs\n"+"Wed : " + mWednesday+ "hrs\n"+"Thu : " + mThursday+ "hrs\n"
                        +"Fri : " + mFriday+ "hrs\n"+"___________________________"+"\n" +
                        "\t Total Hours for Week is : " + mTotalHours+
                        "" +
                        "\n\nThanks\n"+email;

            myemail  = new Email(spApprover.getSelectedItem().toString(),"Time Sheet for the current week"+mstartDate ,myemailBody);

            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        etMonday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    if(etMonday.getText().toString().equals("")){
                        etMonday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }

            }
        });

        etTuesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etTuesday.getText().toString().equals("")){
                        etTuesday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }

            }
        });

        etWednesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etWednesday.getText().toString().equals("")){
                        etWednesday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }
            }
        });

        etThursday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etThursday.getText().toString().equals("")){
                        etThursday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }
            }
        });

        etFriday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etFriday.getText().toString().equals("")){
                        etFriday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }
            }
        });
        setListenerUsers();



  sendEmailBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          sendEmail();
      }
  });

        return view;
    }
    /*
    * Adding approvers to the list in Register Time sheet fragment*/
    public static void addApprovers() {
        ArrayList<String> list = new ArrayList<>();

        for (User manger:myManagers) {
            String name = manger.getEmail();
            list.add(name);
            Log.d("\nRamesh",name);
        }
        Log.d("Ramesh", "Approvers List will be Here");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterTimesheetFragment.context,android.R.layout.simple_list_item_1, list);
       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spApprover.setAdapter(dataAdapter);
        Log.d("Ramesh",""+spApprover.getTextAlignment());


    }

    public void setEtEndDate(String text) {
        etEndDate.setText(text);
    }

    public void showDatePicker() {
        FragmentManager manager = getFragmentManager();
        Date date = new Date();
        dialog = DatePickerFragment.newInstance(date);
        dialog.show(manager, DIALOG_DATE);
    }

    public void setStartDate(String text) {
        startDate.setText(text);
    }

    public void sumHoursWeek() {
        mMonday = etMonday.getText().toString();
        mTuesday = etTuesday.getText().toString();
        mWednesday = etWednesday.getText().toString();
        mThursday = etThursday.getText().toString();
        mFriday = etFriday.getText().toString();

        finalSum = 0;
        hoursMonday = Double.parseDouble(mMonday);
        hoursTuesday = Double.parseDouble(mTuesday);
        hoursWednesday = Double.parseDouble(mWednesday);
        hoursThursday = Double.parseDouble(mThursday);
        hoursFriday = Double.parseDouble(mFriday);
        finalSum = finalSum + hoursMonday+hoursTuesday+hoursWednesday+hoursThursday+hoursFriday;
    }

    public void resetScreen(){
        etMonday.setText("");
        etTuesday.setText("");
        etWednesday.setText("");
        etThursday.setText("");
        etFriday.setText("");
        etTotalHours.setText("");
        etStatus.setText("created");
    }

    public static void setListenerUsers(){



        //Listening for any change in the users
        DatabaseManager.getInstance().myRefusers.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                DatabaseManager.getInstance().users = new ArrayList<>();
//                DatabaseManager.getInstance().employees = new ArrayList<>();

                myManagers = new ArrayList<User>();


                Log.d(TAG, "Listening for registered users " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User post = postSnapshot.getValue(User.class);
                    if(post.getBusinessRole().equals("manager")){
                        myManagers.add(post);
                    }
                }

                Log.d("Ramesh",myManagers.toString());
                addApprovers();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+ "operation with error!");
                Log.d(TAG, "===========================================");
            }
        });
    }
public void sendEmail()
{

    if (myemail != null)
    {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{myemail.getToEmail()});
        i.putExtra(Intent.EXTRA_SUBJECT, myemail.getSubject());
        i.putExtra(Intent.EXTRA_TEXT   , myemail.getBody());
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        myemail = null;
    }
    else
    {

        Toast.makeText(getActivity(), "Please Regisgter the Time or You have sent email Just now.", Toast.LENGTH_SHORT).show();
    }


}
}
