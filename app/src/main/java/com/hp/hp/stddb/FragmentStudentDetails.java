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

import org.json.JSONArray;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class FragmentStudentDetails extends Fragment {
TextView name,email,dob,age,regno,course;
    AsyncHttpClient client;
    RequestParams params;
    public FragmentStudentDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_fragment_student_details, container, false);

        email=view.findViewById(R.id.svdemail);
        name=view.findViewById(R.id.svdname);
        age=view.findViewById(R.id.svdage);
        regno=view.findViewById(R.id.svdregno);
        course=view.findViewById(R.id.svdcourseperiod);
        dob=view.findViewById(R.id.svddob);

        client=new AsyncHttpClient();
        params=new RequestParams();

        SharedPreferences sharedregemail=getContext().getSharedPreferences("spfemail",MODE_PRIVATE);
final String emailsh=sharedregemail.getString("email",null);


params.put("email",emailsh);

client.get("http://sicsglobal.co.in/studentManagementApp/API/ViewStdntDetails.aspx?",params,new AsyncHttpResponseHandler(){
    @Override
    public void onSuccess(String content) {
        super.onSuccess(content);


        if(emailsh.equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(), "Email not valid", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try{

                JSONObject jsonObject=new JSONObject(content);

                JSONArray jarray =jsonObject.getJSONArray("Details");
                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject obj = jarray.getJSONObject(i);

                    String names= obj.getString("name");
                    name.setText("NAME : "+names);

                    String emails= obj.getString("email");
                    email.setText("Email : "+emails);

                    String ages= obj.getString("age");
                    age.setText("AGE : "+ages);

                    String dobs= obj.getString("dob");
                    dob.setText("DOB : "+dobs);


                    String regs= obj.getString("regno");
                    regno.setText("REG NO  : "+regs);

                    String courses= obj.getString("periodofcrs");
                    course.setText("COURSE PERIOD : "+courses);



                }


                }catch (Exception e)
            {
                Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();

            }
        }


    }
});



        return view;
    }

}
