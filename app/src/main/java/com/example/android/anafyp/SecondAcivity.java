package com.example.android.anafyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;

import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SecondAcivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private ListView TariffList;
    private dataAdapter adapter;
    private List<Product> mProuctList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acivity);

        TariffList=(ListView)findViewById(R.id.TariffSelect);
        mProuctList=new ArrayList<>();
        mProuctList.add(new Product (1,"Fariya NET","ISPNo.1"));
        mProuctList.add(new Product (2,"Qubee","ISP.No.2"));
        mProuctList.add(new Product (3,"Wi-Tribe","ISP.No.3"));
        mProuctList.add(new Product (4,"Jazz Cash","ISP.No.4"));
        mProuctList.add(new Product (5,"Leo NET","ISP.No.5"));
        mProuctList.add(new Product (6,"NTC NET","ISP.No.6"));
        mProuctList.add(new Product (7,"PTCL DSL","ISP.No.7"));
        mProuctList.add(new Product (8,"PTCL EVO","ISP.No.8"));
        mProuctList.add(new Product (9,"Fiberlink","ISP.No.9"));
        mProuctList.add(new Product (10,"NetSol","ISP.No.10"));
        mProuctList.add(new Product (11,"COMSAT","ISP.No.11"));
        mProuctList.add(new Product (12,"Conect Comm.","ISP.No.12"));
        mProuctList.add(new Product (13,"Cyber Internet","ISP.No.13"));

        adapter = new dataAdapter(getApplicationContext(),mProuctList);
        TariffList.setAdapter(adapter);

        TariffList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0) {
                    Toast.makeText(getApplicationContext(), "Clicked Tariff: " + view.getTag(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondAcivity.this, ChartActivity.class));
                }
                if(id==1){
                    Toast.makeText(getApplicationContext(), "Clicked Tariff: " + view.getTag(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondAcivity.this,ChartActivity2.class));
                }
                if(id==2){
                    Toast.makeText(getApplicationContext(), "Clicked Tariff: " + view.getTag(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondAcivity.this,ChartActivity3.class));
                }
                if(id==3){
                    Toast.makeText(getApplicationContext(), "Clicked Tariff: " + view.getTag(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SecondAcivity.this,ChartActivity3.class));
                }

            }
            });


        firebaseAuth = FirebaseAuth.getInstance();
        logout=(Button)findViewById(R.id.BtnSummary);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });


    }

    public void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondAcivity.this,MainActivity.class));

    }


 @Override
public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.logoutMenu:{
                    Logout();
                    break;
                }
                case R.id.ProfileMenu:{
                    startActivity(new Intent(SecondAcivity.this,ProfileActivity.class));
                    break;
                }
            }

            return super.onOptionsItemSelected(item);
        }

    @Override
    public void onBackPressed() {
     }
}
