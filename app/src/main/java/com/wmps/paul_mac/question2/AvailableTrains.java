package com.wmps.paul_mac.question2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;


public class AvailableTrains extends AppCompatActivity {

    int flag = 0;
    ArrayAdapter ad;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_main);
        final Bundle i = getIntent().getExtras();

        final String flightSc1[] = {"Flight A1", "Flight A2", "Flight A3", "Flight A4", "Flight A5", "Flight A6"};
        final String flightSc2[] = {"Flight B1", "Flight B2", "Flight B3", "Flight B4", "Flight B5", "Flight B6"};
        final String flightSc3[] = {"Flight C1", "Flight C2", "Flight C3", "Flight C4", "Flight C5", "Flight C6"};
        final String flightSc4[] = {"Flight T1", "Flight T2", "Flight T3", "Flight T4", "Flight T5", "Flight T6"};
        final String noFlights[] = {"No Tatkal Flights Available"};

        String allFlight[][] = {flightSc1,flightSc2,flightSc3,flightSc4};

        TextView source = (TextView) findViewById(R.id.sourcetv);
        TextView destination = (TextView) findViewById(R.id.desttv);
        TextView time = (TextView) findViewById(R.id.time);
        TextView date = (TextView) findViewById(R.id.date);
        ToggleButton gentat = (ToggleButton) findViewById(R.id.toggleButton);
        final ListView lv = (ListView) findViewById(R.id.flightList);


        source.setText("From: "+i.getString("source"));
        destination.setText("To: "+ i.getString("destination"));
        date.setText("Date: "+i.getString("day")+"/"+i.getString("month")+"/"+i.getString("year"));
        time.setText("Time: "+i.getString("hour")+":"+i.getString("minute")+"hrs");


        gentat.setTextOff("General");
        gentat.setText("General");
        gentat.setTextOn("Tatkal");

        if(Integer.parseInt(i.getString("hour"))>=0 && Integer.parseInt(i.getString("hour"))<8){
            ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc1);
            lv.setAdapter(ad);
        }

        else if(Integer.parseInt(i.getString("hour"))>=9 && Integer.parseInt(i.getString("hour"))<16){
            ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc2);
            lv.setAdapter(ad);
        }

        else if(Integer.parseInt(i.getString("hour"))>=17 && Integer.parseInt(i.getString("hour"))<25){
            ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc3);
            lv.setAdapter(ad);
        }

        gentat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(Integer.parseInt(i.getString("hour"))>=11){
                        ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc4);
                        lv.setAdapter(ad);
                    }
                    else{
                        ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,noFlights);
                        lv.setAdapter(ad);
                    }

                }
                else{
                    if(Integer.parseInt(i.getString("hour"))>=0 && Integer.parseInt(i.getString("hour"))<8){
                        ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc1);
                        lv.setAdapter(ad);
                    }

                    if(Integer.parseInt(i.getString("hour"))>=9 && Integer.parseInt(i.getString("hour"))<16){
                        ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc2);
                        lv.setAdapter(ad);
                    }

                    if(Integer.parseInt(i.getString("hour"))>=17 && Integer.parseInt(i.getString("hour"))<25){
                        ad = new ArrayAdapter(AvailableTrains.this, R.layout.list_item,flightSc3);
                        lv.setAdapter(ad);
                    }
                }
            }
        });


    }

}
