package com.hp.hp.stddb;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class StaffAddMarkFrag extends Fragment {


    AsyncHttpClient client;
    RequestParams params;

    TextView date;
    Button submitbtn;

    EditText sem,batch,name,rollno,maths,physics,ics,ds,daa,rdbms,co;

    String url="http://sicsglobal.co.in/StudentManagementApp/API/Result_Add.aspx?";


    public StaffAddMarkFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_staff_add_mark, container, false);

        date=v.findViewById(R.id.date);
        submitbtn=v.findViewById(R.id.sub);

        sem=v.findViewById(R.id.semedt);
        batch=v.findViewById(R.id.batchedt);
        name=v.findViewById(R.id.nameedt);
        rollno=v.findViewById(R.id.rolledt);
        maths=v.findViewById(R.id.mathsedt);
        physics=v.findViewById(R.id.physicsedt);
        ics=v.findViewById(R.id.icsedt);
        ds=v.findViewById(R.id.dsedt);
        daa=v.findViewById(R.id.daaedt);
        rdbms=v.findViewById(R.id.rdbmsedt);
        co=v.findViewById(R.id.coedt);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);


        client=new AsyncHttpClient();
        params=new RequestParams();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sm=sem.getText().toString();
                String bat=batch.getText().toString();
                String nam=name.getText().toString();
                String rol=rollno.getText().toString();
                String math=maths.getText().toString();
                String phy=physics.getText().toString();
                String ic=ics.getText().toString();
                String d=ds.getText().toString();
                String da=daa.getText().toString();
                String rd=rdbms.getText().toString();
                String c=co.getText().toString();
                String dt=date.getText().toString();

                if (!sm.isEmpty()&&!bat.isEmpty()&&!nam.isEmpty()&&!rol.isEmpty()&&!math.isEmpty()&&!phy.isEmpty()&&!ic.isEmpty()&&!d.isEmpty()&&!da.isEmpty()&&!rd.isEmpty()&&!c.isEmpty()){

                    params.put("sem",sm);
                    params.put("batch",bat);
                    params.put("stname",nam);
                    params.put("strollno",rol);
                    params.put("maths",math);
                    params.put("physics",phy);
                    params.put("Ics",ic);
                    params.put("Ds",d);
                    params.put("DAA",da);
                    params.put("RDBMS",rd);
                    params.put("CO",c);
                    params.put("date",dt);

                    client.get(url,params,new AsyncHttpResponseHandler(){
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try{
                                Log.e("Inn","Out");
                                JSONObject mainobj=new JSONObject(content);
                                String s=mainobj.getString("Status");

                                if (s.equals("Success")){
                                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                                    String userid=mainobj.getString("Result Id");
                                    SharedPreferences sharedreg=getContext().getSharedPreferences("spf",MODE_PRIVATE);
                                    SharedPreferences.Editor edt=sharedreg.edit();
                                    edt.putString("resid",userid);
                                    edt.apply();

                                }

                                else{
                                    Toast.makeText(getContext(), "Not Success", Toast.LENGTH_SHORT).show();
                                }



                            }catch (Exception e){
                                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


                }else
                {
                    Toast.makeText(getContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });






        return v;
    }

}
