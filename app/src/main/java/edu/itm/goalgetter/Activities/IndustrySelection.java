package edu.itm.goalgetter.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import edu.itm.goalgetter.FirebaseExtra.FirebaseInit;
import edu.itm.goalgetter.R;

public class IndustrySelection extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private Button nextbutton;


    ImageView im1,im2,im3,im4,im5,im6,im7,im8,im9,im10,im11,im12;

    boolean t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;

    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;


    List<String> compainesList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_selection);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        t1=t2 =t3=t4=t5=t6=t7=t8=t9=t10=t11=t12= false;


        im1 = (ImageView) findViewById(R.id.tile1);
        im2 = (ImageView) findViewById(R.id.tile2);
        im3 = (ImageView) findViewById(R.id.tile3);
        im4 = (ImageView) findViewById(R.id.tile4);
        im5 = (ImageView) findViewById(R.id.tile5);
        im6 = (ImageView) findViewById(R.id.tile6);
        im7 = (ImageView) findViewById(R.id.tile7);
        im8 = (ImageView) findViewById(R.id.tile8);
        im9 = (ImageView) findViewById(R.id.tile9);
        im10 = (ImageView) findViewById(R.id.tile10);
        im11 = (ImageView) findViewById(R.id.tile11);
        im12 = (ImageView) findViewById(R.id.tile12);


        c1 = (CardView) findViewById(R.id.c1);
        c2 = (CardView) findViewById(R.id.c2);
        c3 = (CardView) findViewById(R.id.c3);
        c4 = (CardView) findViewById(R.id.c4);
        c5 = (CardView) findViewById(R.id.c5);
        c6 = (CardView) findViewById(R.id.c6);
        c7 = (CardView) findViewById(R.id.c7);
        c8 = (CardView) findViewById(R.id.c8);
        c9 = (CardView) findViewById(R.id.c9);
        c10 = (CardView) findViewById(R.id.c10);
        c11 = (CardView) findViewById(R.id.c11);
        c12 = (CardView) findViewById(R.id.c12);

        nextbutton = (Button) findViewById(R.id.nextButton);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseInit.getDatabase().getReference().child("users").child(user.getUid()).child("companiesList").setValue(compainesList);
                Toast.makeText(getApplicationContext(),"Companies Updated",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });



        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t1){
                    t1 =false;
                    c1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("Google");
                }else{
                    t1=true;
                    c1.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("Google");
                }
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t2){
                    t2 =false;
                    c2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("Facebook");
                }else{
                    t2=true;
                    c2.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("Facebook");
                }
            }
        });


        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t3){
                    t3 =false;
                    c3.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("amazon");
                }else{
                    t3=true;
                    c3.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("amazon");
                }
            }
        });

        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t4){
                    t4 =false;
                    c4.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("flipkart");
                }else{
                    t4=true;
                    c4.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("flipkart");
                }
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t5){
                    t5 =false;
                    c5.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("oracle");
                }else{
                    t5=true;
                    c5.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("oracle");
                }
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t6){
                    t6 =false;
                    c6.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("paypal");
                }else{
                    t6=true;
                    c6.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("paypal");
                }
            }
        });
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t7){
                    t7 =false;
                    c7.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("accenture");
                }else{
                    t7=true;
                    c7.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("accenture");
                }
            }
        });
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t8){
                    t8 =false;
                    c8.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("wipro");
                }else{
                    t8=true;
                    c8.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("wipro");
                }
            }
        });
        im9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t9){
                    t9 =false;
                    c9.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("cognizant");
                }else{
                    t9=true;
                    c9.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("cognizant");

                }
            }
        });
        im10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t10){
                    t10 =false;
                    c10.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("tcs");
                }else{
                    t10=true;
                    c10.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("tcs");

                }
            }
        });
        im11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t11){
                    t11 =false;
                    c11.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("infosys");
                }else{
                    t11=true;
                    c11.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("infosys");
                }
            }
        });
        im12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t12){
                    t12 =false;
                    c12.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    compainesList.remove("thoughworks");
                }else{
                    t12=true;
                    c12.setBackgroundColor(getResources().getColor(R.color.colorSelection));
                    compainesList.add("thoughworks");
                }
            }
        });







    }
}
