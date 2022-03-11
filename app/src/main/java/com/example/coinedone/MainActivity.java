package com.example.coinedone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView_classTime,textView_studyTime,textView_freeTime,textView_freeTime_usage,textView_freeTime_max_usage;
    TextView textView_phone_totaltime,textView_laptop_totaltime,textView_total_time;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui_init();
        /* this fuction use retrofit library to fetch screen time data */
        fetchData();
    }


    public void ui_init()
    {
        textView_classTime=findViewById(R.id.text_classTime);
        textView_studyTime=findViewById(R.id.text_studyTime);
        textView_freeTime=findViewById(R.id.text_freeTime);
        textView_freeTime_usage=findViewById(R.id.text_freeTime_usage);
        textView_freeTime_max_usage=findViewById(R.id.text_freeTime_max);
        progressbar=findViewById(R.id.progressBar);
        textView_phone_totaltime=findViewById(R.id.text_phone_time);
        textView_laptop_totaltime=findViewById(R.id.text_laptop_time);
        textView_total_time=findViewById(R.id.total_time_text);

    }

    public void fetchData()
    {
        Call<List<ScreenTimePojo>> call = RetrofitClient.getInstance().getMyApi().gettime_data();
        call.enqueue(new Callback<List<ScreenTimePojo>>() {
            @Override
            public void onResponse(Call<List<ScreenTimePojo>> call, Response<List<ScreenTimePojo>> response) {
                List<ScreenTimePojo> screenTime_data_body = response.body();

                Log.d("jishnu","success");

                /* In the on response call functions to set datas in the user interface */

                ScreenTimePojo screenTime_data=(ScreenTimePojo)screenTime_data_body.get(0);
                set_data_to_Ui(screenTime_data);
                set_data_to_horizontalbar(screenTime_data);
                setProgressBar(screenTime_data);
                setDeviceUsage(screenTime_data);
                Log.d("jishnu",screenTime_data.chartdata.freetime.getChartData_freeTime());
            }

            @Override
            public void onFailure(Call<List<ScreenTimePojo>> call, Throwable t) {
                Log.d("jishnu","failed");
            }

        });


    }

    public void set_data_to_Ui(ScreenTimePojo screenTime_data)
    {

        int studytime=Integer.parseInt(screenTime_data.chartdata.studytime.getChartData_studyTime());
        int classtime=Integer.parseInt(screenTime_data.chartdata.classtime.getChartData_classTime());
        int freetime=Integer.parseInt(screenTime_data.chartdata.freetime.getChartData_freeTime());
          CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgress);
        circularProgressBar.setProgressValue(studytime,classtime,freetime);

        textView_total_time.setText(stringToFormatTimeString(screenTime_data.chartdata.totaltime.getChartData_total()));



    }

    public void set_data_to_horizontalbar(ScreenTimePojo screenTime_data)
    {
        String class_time_in_HM;
        String study_time_in_HM;
        String free_time_in_HM;
        String free_time_Max_in_HM;

        class_time_in_HM=stringToFormatTimeString(screenTime_data.chartdata.classtime.getChartData_classTime());


        study_time_in_HM=stringToFormatTimeString(screenTime_data.chartdata.studytime.getChartData_studyTime());



        free_time_in_HM=stringToFormatTimeString(screenTime_data.chartdata.freetime.getChartData_freeTime());


        free_time_Max_in_HM=stringToFormatTimeString(screenTime_data.getFreeTimeMaxUsage());


        textView_classTime.setText(class_time_in_HM);
        textView_studyTime.setText(study_time_in_HM);
        textView_freeTime.setText(free_time_in_HM);
        textView_freeTime_usage.setText(free_time_in_HM);
        textView_freeTime_max_usage.setText(free_time_Max_in_HM);


    }

    public void setProgressBar(ScreenTimePojo screenTime_data)
    {
        int maximumvalue=Integer.parseInt(screenTime_data.getFreeTimeMaxUsage());
        int currentvalue=Integer.parseInt(screenTime_data.chartdata.freetime.getChartData_freeTime());
        progressbar.setMax(maximumvalue);
        ProgressBarAnimation anim = new ProgressBarAnimation(progressbar, 0, currentvalue);
        anim.setDuration(1000);
       progressbar.startAnimation(anim);


    }

    public void setDeviceUsage(ScreenTimePojo screenTime_data)
    {


        textView_phone_totaltime.setText(stringToFormatTimeString(screenTime_data.deviceusage.totaltime.getTotal_mobile()));
        textView_laptop_totaltime.setText(stringToFormatTimeString(screenTime_data.deviceusage.totaltime.getTotal_laptop()));
    }

    /* This function will convert minutes time to hour minutes format */

    public String stringToFormatTimeString(String time)
    {
        int timeHour=Integer.parseInt(time)/60;
        int timeMinute=Integer.parseInt(time)%60;
        String formstedTime;
        if(timeHour==0)
        {
            formstedTime=timeMinute+"m";
        }
        else if(timeMinute==0){
           formstedTime=timeHour+"h";
        }
        else {
            formstedTime=timeHour+"h "+timeMinute+"m";
        }


        return formstedTime;
    }
}