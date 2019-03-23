package com.hp.hp.stddb;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStudentViewAttendance extends Fragment {
    AsyncHttpClient client;
    RequestParams params;

    TextView attendper,dat;

    String url="http://sicsglobal.co.in/studentManagementApp/API/OneMonthPercAttendance.aspx?";


    public FragmentStudentViewAttendance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_student_view_attendance, container, false);



        attendper=v.findViewById(R.id.attendence);
        dat=v.findViewById(R.id.date);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        dat.setText(formattedDate);

        client=new AsyncHttpClient();
        params=new RequestParams();

        SharedPreferences sharedlogin = getActivity().getSharedPreferences("sharedlogin", MODE_PRIVATE);
String uid=sharedlogin.getString("uid",null);

        params.put("roll",uid);

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try {
                    JSONObject mainobj=new JSONObject(content);
                    if (mainobj.getString("Status").equals("Success")){

                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                        String att=mainobj.getString("Attendance Percentage");
                        attendper.setText(att);

                    }


                }catch (Exception e){
                    Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();

                }
            }
        });

        return v;
    }

}