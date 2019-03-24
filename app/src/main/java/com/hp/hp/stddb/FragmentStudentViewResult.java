package com.hp.hp.stddb;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStudentViewResult extends Fragment {

    AsyncHttpClient client;
    RequestParams params;

    TextView name,rollno,sem,batch,date,maths,physics,ics,ds,daa,rdbms,co;

    String url="http://sicsglobal.co.in/studentManagementApp/API/ViewResult.aspx?";

    public FragmentStudentViewResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fragment_student_view_result, container, false);

        name=v.findViewById(R.id.stdname);
        rollno=v.findViewById(R.id.stdroll);
        sem=v.findViewById(R.id.stdsem);
        batch=v.findViewById(R.id.stdbatch);
        date=v.findViewById(R.id.stddate);
        maths=v.findViewById(R.id.stdmaths);
        physics=v.findViewById(R.id.stdphy);
        ics=v.findViewById(R.id.stdics);
        ds=v.findViewById(R.id.stdds);
        daa=v.findViewById(R.id.stddaa);
        rdbms=v.findViewById(R.id.stdrdbms);
        co=v.findViewById(R.id.stdco);



        client=new AsyncHttpClient();
        params=new RequestParams();

        SharedPreferences sharedlogin = getActivity().getSharedPreferences("sharedlogin", MODE_PRIVATE);
        final String emailsh=sharedlogin.getString("emailkey",null);

        String e=sharedlogin.getString("uid",null);
        params.put("sem","s1");
        params.put("batch","bca");
        params.put("rollno",e);


        Log.e("inn","out");

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                try{
                    JSONObject mainobj=new JSONObject(content);
                    if (mainobj.getString("Status").equals("Success")){
                        JSONArray jsonArray=mainobj.getJSONArray("Details");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jobj=jsonArray.getJSONObject(i);

                            String nm=jobj.getString("stname");
                            name.setText(nm);

                            String rl=jobj.getString("strollno");
                            rollno.setText(rl);

                            String sm=jobj.getString("sem");
                            sem.setText(sm);

                            String btch=jobj.getString("batch");
                            batch.setText(btch);

                            String dt=jobj.getString("date");
                            date.setText(dt);

                            String mat=jobj.getString("maths");
                            maths.setText(mat);

                            String phy=jobj.getString("physics");
                            physics.setText(phy);

                            String ic=jobj.getString("Ics");
                            ics.setText(ic);

                            String dss=jobj.getString("Ds");
                            ds.setText(dss);

                            String da=jobj.getString("DAA");
                            daa.setText(da);

                            String rd=jobj.getString("RDBMS");
                            rdbms.setText(rd);

                            String c=jobj.getString("CO");
                            co.setText(c);

                        }

                    }


                }catch (Exception E){




                }
            }
        });



        return v;
    }

}