package edu.itm.goalgetter.Activities;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import edu.itm.goalgetter.FirebaseExtra.FirebaseInit;
import edu.itm.goalgetter.Models.UserModel;
import edu.itm.goalgetter.R;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,password,name;
    private String TAG = "SignupActivity";
    AlertDialog alertDialog;
    FirebaseUser user;


    List<String> DegreeList = new ArrayList<String>();
    List<String> BrachesList = new ArrayList<String>();

    private Spinner spinner1,spinner2;
    ArrayAdapter<String> dataAdapter,dataAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name_et);
        email = (EditText) findViewById(R.id.email_et);
        password = (EditText) findViewById(R.id.password_et);
        dialog();

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
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }

    public void userDetailUpdate(){

        UserModel user1 = new UserModel();
        user1.setEmailId(email.getText().toString());
        user1.setName(name.getText().toString());
        FirebaseInit.getDatabase().getReference().child("users").child(user.getUid()).setValue(user1);


    }

    public void dialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Signing you up...");
//                alertDialogBuilder.setPositiveButton("yes",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface arg0, int arg1) {
//                                Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
//                            }
//                        });


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
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
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

}
