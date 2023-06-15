package com.example.test613;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Fragment2 extends Fragment {

    UIThread UT;
    UIHandler UH;
    String state;

    Button btn;
    String data;

    final String [] recst = {"가지", "오징어", "양파", "동태", "떡", "닭고기", "소고기",
            "돼지고기", "만두"};
    final String [] recint= {"10","10","05","09","23","10","10","05","10","03"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout2, container, false);
        btn = (Button) view.findViewById(R.id.btn);

        UH = new UIHandler();
        UT = new UIThread();

        return view;

    }
    public void buttonClicked(View view){
        switch (view.getId()){
            case R.id.btn:

                UT.start();

                state = "Active";

                break;
        }
    }

    String getData(){
        StringBuffer buffer = new StringBuffer();

        double random = Math.random();
        int num = (int)Math.round(random * (recst.length -1));
        String rec = recst[num];
        String rei = recint[num];

        String queryUrl="https://apis.data.go.kr/1390802/AgriFood/MzenFoodCode/getKoreanFoodList?" +
                "serviceKey=EMYVbBgVP6gLDp4c7aVazsdZLT9lZAu5XM%2BX1vGvwvEymIOcQHBy85VOH0SoFhLXtO6xpIAFxQgT4WtwafzEKw%3D%3D&Page_No=1&Page_Size=30&" +
                "food_Group_Code="+rei+"&food_Name="+rec;

        try{
            URL url = new URL(queryUrl);
            InputStream Is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new InputStreamReader(Is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if(tag.equals("item"));
                        else if(tag.equals("middle_Name")){
                            buffer.append("분류: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }else if(tag.equals("food_Name")){
                            buffer.append("추천 음식: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if(tag.equals("item")) buffer.append("\n");
                        break;
                }
                eventType = xpp.next();
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return buffer.toString();

    }
    private class UIThread extends Thread{
        Message msg;
        boolean loop = true;

        public void run() {
            try {
                while (loop) {

                    data = getData();

                    Thread.sleep(100);

                    UH.post(new Runnable() {
                        @Override
                        public void run() {
                            btn.setText(data);
                        }
                    });

                    if(Thread.interrupted()){
                        loop = false;
                        break;
                    }

                    msg = UH.obtainMessage();
                    msg.arg1 = 1;

                    UH.sendMessage(msg);
                }

            } catch (InterruptedException e) {
                loop = false;
            }

        }
    }

    private class UIHandler extends Handler {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.arg1) {
                case 1:
                    if(state.equals("DeActive"))
                        break;
            }
        }
    }

    public void onStop(){
        super.onStop();
        state = "DeActive";
        UT.interrupt();
    }

    public void onResume(){
        super.onResume();
        state = "Active";
    }
}
