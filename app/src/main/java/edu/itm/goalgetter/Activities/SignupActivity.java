package edu.itm.goalgetter.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.itm.goalgetter.FirebaseExtra.Api;
import edu.itm.goalgetter.FirebaseExtra.FirebaseInit;
import edu.itm.goalgetter.Models.UserModel;
import edu.itm.goalgetter.R;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,password,name,ssc,hsc;
    private String TAG = "SignupActivity";
    AlertDialog alertDialog;
    FirebaseUser user;




    List<String> DegreeList = new ArrayList<String>();
    List<String> BrachesList = new ArrayList<String>();
    List<String> YearList = new ArrayList<String>();

    RequestBody requestBody;

    private Spinner spinner1,spinner2,spinner3;
    ArrayAdapter<String> dataAdapter,dataAdapter2,dataAdapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        DegreeList.add("B.Tech");
        DegreeList.add("M.Tech");
        DegreeList.add("MBA");
        DegreeList.add("BSC");
        DegreeList.add("MSC");


        BrachesList.add("Computer Science & Engineering");
        BrachesList.add("Electrical Engineering");
        BrachesList.add("Civil Engineering");
        BrachesList.add("Mech. Engineering");

        YearList.add("1st year");
        YearList.add("2nd year");
        YearList.add("3rd year");
        YearList.add("4th year");




        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name_et);
        email = (EditText) findViewById(R.id.email_et);
        password = (EditText) findViewById(R.id.password_et);
        ssc = (EditText) findViewById(R.id.ssc_et);
        hsc = (EditText) findViewById(R.id.hsc_et);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);



        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, DegreeList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, BrachesList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, YearList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter2);
        spinner3.setAdapter(dataAdapter3);

        dialog();



    }



    public void restApiCall(){

        //retrofit fetching data from json
        Retrofit ret = new Retrofit.Builder()

                .baseUrl(Api.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = ret.create(Api.class);

        try{

            requestBody = RequestBody.create(MediaType.parse("application/json"),buidJsonObject().toString());

        }catch (Exception e){

        }


        api.postUserDataandGetAnalysis(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("RetrofitExample", response.body().string());

                    try {

                        JSONObject obj = new JSONObject(response.body().string());

                        Log.d("My App", obj.toString());

                    } catch (Throwable t) {
                        Log.e("My App", "Could not parse malformed JSON: \"" + response.body().string() + "\"");
                    }


                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void login(View view){
        alertDialog.show();

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            user= mAuth.getCurrentUser();
                            userDetailUpdate();
                            restApiCall();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });


    }

    public void userDetailUpdate(){

        UserModel user1 = new UserModel();
        user1.setEmailId(email.getText().toString());
        user1.setName(name.getText().toString());
        user1.setTimeStamp(System.currentTimeMillis());
        user1.setSsc(Integer.parseInt(ssc.getText().toString()));
        user1.setHsc(Integer.parseInt(hsc.getText().toString()));
        user1.setDegree(String.valueOf(spinner1.getSelectedItem()));
        user1.setBranch(String.valueOf(spinner2.getSelectedItem()));
        if(String.valueOf(spinner3.getSelectedItem()).equals("1st year")){
            user1.setYear(1);
        }else if(String.valueOf(spinner3.getSelectedItem()).equals("2nd year")){
            user1.setYear(2);
        }else if(String.valueOf(spinner3.getSelectedItem()).equals("3rd year")){
            user1.setYear(3);
        }else{
            user1.setYear(4);
        }

        FirebaseInit.getDatabase().getReference().child("users").child(user.getUid()).setValue(user1);

    }

    public void dialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Signing you up...");



        alertDialog = alertDialogBuilder.create();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    public void updateUI(FirebaseUser user){

        if(user!=null){
            Intent intent = new Intent(getApplicationContext(),IndustrySelection.class);
            startActivity(intent);
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            finish();
        }
    }

    public void onClickLogin(View view){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }


    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected = false;
        if (networkInfo != null && (isConnected = networkInfo.isConnected())) {
            // show "Connected" & type of network "WIFI or MOBILE"


        } else {
            // show "Not Connected"

        }

        return isConnected;
    }




    private JSONObject buidJsonObject() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("ssc", Integer.parseInt(ssc.getText().toString()));
        jsonObject.accumulate("hsc", Integer.parseInt(ssc.getText().toString()));

//        jsonObject.accumulate("ssc", 89);
//        jsonObject.accumulate("hsc", 78);

        return jsonObject;
    }


    public class Result{
        public String job_set;

        public String getJob_set() {
            return job_set;
        }

        public void setJob_set(String job_set) {
            this.job_set = job_set;
        }
    }







}
