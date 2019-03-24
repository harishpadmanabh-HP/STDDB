package com.hp.hp.stddb;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffAddAttendanceFrag extends Fragment {


    CheckBox roll1, roll2, roll3, roll4, roll5, roll6, roll7, roll8, roll9, roll10;
    AsyncHttpClient client;
    RequestParams params;
    String url = "http://sicsglobal.co.in/studentManagementApp/API/Attendance_Add.aspx?";
    TextView date, viewdate;


    public StaffAddAttendanceFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_staff_add_attendance, container, false);

        roll1 = v.findViewById(R.id.checkbox1);
        roll2 = v.findViewById(R.id.checkbox2);
        roll3 = v.findViewById(R.id.checkbox3);
        roll4 = v.findViewById(R.id.checkbox4);
        roll5 = v.findViewById(R.id.checkbox5);
        roll6 = v.findViewById(R.id.checkbox6);
        roll7 = v.findViewById(R.id.checkbox7);
        roll8 = v.findViewById(R.id.checkbox8);
        roll9 = v.findViewById(R.id.checkbox9);
        roll10 = v.findViewById(R.id.checkbox10);

        viewdate = v.findViewById(R.id.datedisp);
        date = v.findViewById(R.id.datetxt);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
// Create the DatePickerDialog instance

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
//set date to editext
                                viewdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        client = new AsyncHttpClient();
        params = new RequestParams();


//roll1
        roll1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    String rl1 = roll1.getText().toString();
                    String dat = viewdate.getText().toString();

                    params.put("rollno", "1");
                    params.put("dt", "24-3-2018");


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success1", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


//roll2

        roll2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl2 = roll2.getText().toString();
                    String dat2 = date.getText().toString();

                    params.put("rollno", rl2);
                    params.put("dt", dat2);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


        //roll3

        roll3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl3 = roll3.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl3);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


//roll4


        roll4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl4 = roll4.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl4);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


//roll5

        roll5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl5 = roll5.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl5);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


//roll6

        roll6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl6 = roll6.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl6);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });

//roll7

        roll7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl7 = roll1.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl7);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });

//roll8

        roll8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl8 = roll8.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl8);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


//roll9

        roll9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl9 = roll9.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl9);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });

//roll10
        roll1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String rl10 = roll10.getText().toString();
                    String dat = date.getText().toString();

                    params.put("rollno", rl10);
                    params.put("dt", dat);


                    client.get(url, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject mainobj = new JSONObject(content);
                                String att = mainobj.getString("Status");

                                if (att.equals("Success")) {

                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    String userid = mainobj.getString("AttendanceId");
                                    SharedPreferences sharedreg = getContext().getSharedPreferences("spf", MODE_PRIVATE);
                                    SharedPreferences.Editor edt = sharedreg.edit();
                                    edt.putString("attid", userid);
                                    edt.apply();

                                } else {
                                    Toast.makeText(getContext(), "not success", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });


        return v;
    }
}