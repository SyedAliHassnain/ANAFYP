package com.example.android.anafyp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;
import java.util.Map;

import im.dacer.androidcharts.LineView;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.auth.FirebaseAuth;


public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        readTariffdata();


    }
    private List<TariffSample> TariffSamples= new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void readTariffdata(){
       InputStream is =  getResources().openRawResource(R.raw.speedtest);
        BufferedReader reader= new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))

        );

        String line="";

            try {
                reader.readLine();

                while (((line= reader.readLine())!= null)) {
                    Log.d("MyActivity","Line"+line);
                    String[] tokens = line.split(",");


                    TariffSample sample = new TariffSample();
                    sample.setDate(tokens[0]);
                    sample.setPing(Double.parseDouble(tokens[2]));
                    sample.setTime(tokens[1]);
                    sample.setUpload(Double.parseDouble(tokens[3]));
                    sample.setDown(Double.parseDouble(tokens[4]));
                    TariffSamples.add(sample);
//                Log.d("MyActivity","Tariff Data"+ sample);

                }
                setuplinechart();
            } catch (IOException e) {
                Log.wtf("MyActivity","Error reading data on line "+ line,e);
                e.printStackTrace();
            }

    }

    private ArrayList<String> strList=new ArrayList<String>();
    private ArrayList<ArrayList<Float>> dataLists=new ArrayList<ArrayList<Float>>();

    private void dataFormatting(){
        ArrayList<Float> upload=new ArrayList<>();
        ArrayList<Float> down=new ArrayList<>();
        ArrayList<Float> ping=new ArrayList<>();
    for (int i=TariffSamples.size()-20;i<TariffSamples.size();i++)
    {

        upload.add((float)TariffSamples.get(i).getUpload());
        down.add((float)TariffSamples.get(i).getDown());
        ping.add((float)TariffSamples.get(i).getPing());

        strList.add(TariffSamples.get(i).getTime());
    }
    dataLists.add(ping);
    dataLists.add(upload);
    dataLists.add(down);

        }

private void setuplinechart() {
    dataFormatting();
    LineView lineView = (LineView)findViewById(R.id.line_view);
    lineView.setDrawDotLine(true); //optional
    lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
    lineView.setBottomTextList(strList);
    lineView.setColorArray(new int[]{Color.BLACK,Color.GREEN,Color.GRAY});
//    lineView.setDataList(dataLists); //or lineView.setFloatDataList(floatDataLists)
    lineView.setFloatDataList(dataLists);
   }


}



