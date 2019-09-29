package com.example.android.anafyp;

class TariffSample {
private double Ping,Upload;
private String Date,Time;
private double Down;

    public double getDown() {
        return Down;
    }

    public void setDown(double down) {
        Down = down;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getPing() {
        return Ping;
    }

    public void setPing(double ping) {
        Ping = ping;
    }

    public double getUpload() {
        return Upload;
    }

    public void setUpload(double upload) {
        Upload = upload;
    }

    @Override
    public String toString() {
        return "TariffSample{" +
                "Date=" + Date +
                ", Time=" + Time +
                ", Ping=" + Ping +
                ", Upload=" + Upload +
                ", Down=" + Down +
                '}';
    }
}


