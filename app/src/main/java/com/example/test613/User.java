package com.example.test613;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String data1, data2, data3;

    public User(){

    }

    public User(String data1, String data2, String data3){
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public String getData1(){
        return data1;
    }
    public void setData1(String data1){
        this.data1 = data1;
    }

    public String getData2(){
        return data2;
    }
    public void setData2(String data2){
        this.data2 = data2;
    }

    public String getData3(){
        return data3;
    }
    public void setData3(String data3){
        this.data3 = data3;
    }

    @Override
    public String toString(){
        return "Menu{" +
                "아침: '" + data1 + '\'' +
                ", 점심: '" + data2 + '\''+
                ", 저녁: '" + data3 +'\''+
                '}';
    }
}
