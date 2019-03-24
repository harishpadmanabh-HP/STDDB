package com.hp.hp.stddb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class StaffSearch extends Fragment {

    EditText filter;
    Button name,place,blood;

    AsyncHttpClient client;
    JSONArray jarray;
    JSONObject jobject;
    RequestParams params;
    ListView lv;

    ArrayList<String> namelist;
    ArrayList<String>agelist;
    ArrayList<String>regnolist ;
    ArrayList<String>doblist;
    ArrayList<String>courselist;
    ArrayList<String>emaillist;
    ArrayList<String>placelist;
    ArrayList<String>bloodlist;

    public StaffSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_staff_search, container, false);

        filter=view.findViewById(R.id.searchname);
        name=view.findViewById(R.id.searchbuttonname);
        place=view.findViewById(R.id.searchbuttonplace);
        blood=view.findViewById(R.id.searchbuttonblood);


        client = new AsyncHttpClient();
        params = new RequestParams();
        //	submit=(Button)findViewById(R.id.submit);
        lv = (ListView)view. findViewById(R.id.listsearch);
        namelist = new ArrayList<String>();
        agelist = new ArrayList<String>();
        regnolist = new ArrayList<String>();
        emaillist = new ArrayList<String>();
        doblist = new ArrayList<String>();
        courselist = new ArrayList<String>();
        placelist = new ArrayList<String>();
        bloodlist = new ArrayList<String>();


        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filterdata=filter.getText().toString();

                params.put("plc",filterdata);

                client.get("http://sicsglobal.co.in/StudentManagementApp/API/ByplcStudnt.aspx?",params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);


                        try {

                            jobject=new JSONObject(content);

                            String s=jobject.getString("Status");

                            if(s.equalsIgnoreCase("success")){

                                jarray =jobject.getJSONArray("Details By Place");
                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject obj = jarray.getJSONObject(i);

                                    String nm = obj.getString("name");
                                    namelist.add("Name :" + nm);

                                    String ag = obj.getString("age");
                                    agelist.add("Age :" + ag);

                                    String reg = obj.getString("regno");
                                    regnolist.add("Reg No :" + reg);

                                    String db = obj.getString("dob");
                                    doblist.add("DOB : " + db);

                                    String cs = obj.getString("course");
                                    courselist.add("Course :" + cs);

                                    String em = obj.getString("email");
                                    emaillist.add("Email :" + em);

                                    String plc = obj.getString("place");
                                    placelist.add("Place: " + plc);

                                    String bld = obj.getString("bloodgrp");
                                    bloodlist.add("Blood Group:" + bld);




                                }

                            }else{
                                Toast.makeText(getContext(), "Search failed . Check your entry . No data found.", Toast.LENGTH_SHORT).show();

                            }
                            adapter adpt = new adapter();
                            lv.setAdapter(adpt);


                        }catch (Exception e){
                            Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                        }



                    }
                });

            }
        });

        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filterdata=filter.getText().toString();

                params.put("bgp",filterdata);

                client.get("http://sicsglobal.co.in/StudentManagementApp/API/BybldGrpstudent.aspx?",params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);


                        try {

                            jobject=new JSONObject(content);

                            String s=jobject.getString("Status");

                            if(s.equalsIgnoreCase("success")){

                                jarray =jobject.getJSONArray(" Details By BloodGroup");
                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject obj = jarray.getJSONObject(i);

                                    String nm = obj.getString("name");
                                    namelist.add("Name :" + nm);

                                    String ag = obj.getString("age");
                                    agelist.add("Age :" + ag);

                                    String reg = obj.getString("regno");
                                    regnolist.add("Reg No :" + reg);

                                    String db = obj.getString("dob");
                                    doblist.add("DOB : " + db);

                                    String cs = obj.getString("course");
                                    courselist.add("Course :" + cs);

                                    String em = obj.getString("email");
                                    emaillist.add("Email :" + em);

                                    String plc = obj.getString("place");
                                    placelist.add("Place: " + plc);

                                    String bld = obj.getString("bloodgrp");
                                    bloodlist.add("Blood Group:" + bld);




                                }

                            }else{
                                Toast.makeText(getContext(), "Search failed . Check your entry . No data found.", Toast.LENGTH_SHORT).show();

                            }
                            adapter adpt = new adapter();
                            lv.setAdapter(adpt);


                        }catch (Exception e){
                            Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                        }



                    }
                });


            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filterdata=filter.getText().toString();

                params.put("name",filterdata);

                client.get("http://sicsglobal.co.in/studentManagementApp/API/ViewSearchDetailsStud.aspx?",params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);


                        try {

                            jobject=new JSONObject(content);

                            String s=jobject.getString("Status");

                            if(s.equalsIgnoreCase("success")){

                                jarray =jobject.getJSONArray("student Details");
                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject obj = jarray.getJSONObject(i);

                                    String nm = obj.getString("name");
                                    namelist.add("Name :" + nm);

                                    String ag = obj.getString("age");
                                    agelist.add("Age :" + ag);

                                    String reg = obj.getString("regno");
                                    regnolist.add("Reg No :" + reg);

                                    String db = obj.getString("dob");
                                    doblist.add("DOB : " + db);

                                    String cs = obj.getString("course");
                                    courselist.add("Course :" + cs);

                                    String em = obj.getString("email");
                                    emaillist.add("Email :" + em);

                                    String plc = obj.getString("place");
                                    placelist.add("Place: " + plc);

                                    String bld = obj.getString("bloodgrp");
                                    bloodlist.add("Blood Group:" + bld);




                                }

                            }else{
                                Toast.makeText(getContext(), "Search failed . Check your entry . No data found.", Toast.LENGTH_SHORT).show();

                            }
                            adapter adpt = new adapter();
                            lv.setAdapter(adpt);


                        }catch (Exception e){
                            Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                        }



                    }
                });
            }
        });







    return  view;
    }


    class adapter extends BaseAdapter {
        LayoutInflater Inflater;
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return namelist.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=Inflater.inflate(R.layout.list_search,null);
            Viewholder holder=new Viewholder();

            holder.name=(TextView)convertView.findViewById(R.id.namel);
            holder.name.setText(namelist.get(position));

            holder.age=(TextView)convertView.findViewById(R.id.agel);
            holder.age.setText(agelist.get(position));

            holder.regno=(TextView)convertView.findViewById(R.id.regl);
            holder.regno.setText(regnolist.get(position));

            holder.dob=(TextView)convertView.findViewById(R.id.dobl);
            holder.dob.setText(doblist.get(position));

            holder.course=(TextView)convertView.findViewById(R.id.coursel);
            holder.course.setText(courselist.get(position));

            holder.email=(TextView)convertView.findViewById(R.id.email);
            holder.email.setText(emaillist.get(position));

            holder.plc=(TextView)convertView.findViewById(R.id.placel);
            holder.plc.setText(placelist.get(position));

            holder.bld=(TextView)convertView.findViewById(R.id.bloodl);
            holder.bld.setText(bloodlist.get(position));


            return convertView;
        }
        class Viewholder{
            TextView name;
            TextView age;
            TextView regno;
            TextView dob;
            TextView course;
            TextView email;
            TextView plc;
            TextView bld;



        }
    }

}