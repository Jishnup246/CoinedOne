package com.example.coinedone;
import com.google.gson.annotations.SerializedName;
public class ScreenTimePojo {

    @SerializedName("chartData")
    public chartData chartdata;
    @SerializedName("deviceUsage")
    public deviceUsage deviceusage;

    private String freeTimeMaxUsage;

    public String getFreeTimeMaxUsage() {
        return freeTimeMaxUsage;
    }

    public class chartData{

        @SerializedName("totalTime")
        public totalTime totaltime;

        @SerializedName("studyTime")
        public studyTime studytime;

        @SerializedName("classTime")
        public classTime classtime;

        @SerializedName("freeTime")
        public freeTime freetime;



        public class totalTime
        {
            @SerializedName("total")
            private String chartData_total;

            public String getChartData_total() {
                return chartData_total;
            }
        }
        public class studyTime
        {
            @SerializedName("total")
            public String chartData_studyTime;

            public String getChartData_studyTime() {
                return chartData_studyTime;
            }
        }

        public class classTime
        {
            @SerializedName("total")
            private String chartData_classTime;

            public String getChartData_classTime() {
                return chartData_classTime;
            }
        }

        public class freeTime
        {
            @SerializedName("total")
            private String chartData_freeTime;

            public String getChartData_freeTime() {
                return chartData_freeTime;
            }
        }


    }



    public class deviceUsage
    {
        @SerializedName("totalTime")
        public totalTime totaltime;
        @SerializedName("studyTime")
        public studyTime studytime;
        @SerializedName("classTime")
        public classTime classtime;
        @SerializedName("freeTime")
        public freeTime freetime;

        public class totalTime
        {
            @SerializedName("mobile")
            private String total_mobile;
            @SerializedName("laptop")
            private String total_laptop;

            public String getTotal_mobile() {
                return total_mobile;
            }

            public String getTotal_laptop() {
                return total_laptop;
            }
        }

        public class studyTime
        {
            @SerializedName("mobile")
            private String studytime_mobile;
            @SerializedName("laptop")
            private String studytime_laptop;

            public String getStudytime_mobile() {
                return studytime_mobile;
            }

            public String getStudytime_laptop() {
                return studytime_laptop;
            }
        }

        public class classTime
        {
            @SerializedName("mobile")
            private String classtime_mobile;
            @SerializedName("laptop")
            private String classtime_laptop;

            public String getClasstime_mobile() {
                return classtime_mobile;
            }

            public String getClasstime_laptop() {
                return classtime_laptop;
            }
        }

        public class freeTime
        {
            @SerializedName("mobile")
            private String freetime_mobile;
            @SerializedName("laptop")
            private String freetime_laptop;

            public String getFreetime_mobile() {
                return freetime_mobile;
            }

            public String getFreetime_laptop() {
                return freetime_laptop;
            }
        }




    }



}
