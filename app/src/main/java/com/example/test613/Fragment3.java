package com.example.test613;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Fragment3 extends Fragment {
    StringBuffer buffer = new StringBuffer();

    UIThread UT;
    UIHandler UH;
    String state;

    RecyclerView recyclerView;
    SearchView sv;
    ArrayList<recTool> items = new ArrayList<>();
    String data;

    EditText ed;
    Button btn;

    MyAdapter adapter;
    Context ct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ct = container.getContext();
        View view = inflater.inflate(R.layout.layout3, container, false);

        recyclerView = view.findViewById(R.id.rv);
        sv = view.findViewById(R.id.sV);

        ed=view.findViewById(R.id.ed);
        btn=view.findViewById(R.id.btn);

        UH = new UIHandler();
        UT = new UIThread();

        LinearLayoutManager layoutManager = new LinearLayoutManager(ct,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        adapter = new MyAdapter(items, ct);
        recyclerView.setAdapter(adapter);
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

        String rec = ed.getText().toString();
        String st = "조리";

        String queryUrl="https://apis.data.go.kr/1390802/AgriFood/FdFoodCkryImage/getKoreanFoodFdFoodCkryImageList?" +
                "serviceKey=EMYVbBgVP6gLDp4c7aVazsdZLT9lZAu5XM%2BX1vGvwvEymIOcQHBy85VOH0SoFhLXtO6xpIAFxQgT4WtwafzEKw%3D%3D" +
                "&service_Type=xml&Page_No=1&Page_Size=20" +
                "&food_Name="+rec+"&ckry_Name="+st;

        try{
            URL url = new URL(queryUrl);
            InputStream Is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new InputStreamReader(Is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            recTool rect = null;

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작 단계\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if(tag.equals("item")){
                            rect = new recTool();
                        } else if(tag.equals("fd_Nm")){
                            xpp.next();
                            if(rect != null) rect.setFd_Nm(xpp.getText());
                        }else if(tag.equals("fd_Wgh")){
                            xpp.next();
                            if(rect != null) rect.setFd_Wgh(xpp.getText());
                        }else if(tag.equals("food_Cnt")){
                            xpp.next();
                            if(rect != null) rect.setFood_Cnt(xpp.getText());
                        }else if(tag.equals("food_Nm")){
                            buffer.append("재료: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\t");

                        }else if(tag.equals("ckry_Sumry_Info")){
                            xpp.next();
                            if(rect != null) rect.setCkry_Sumry_Info(xpp.getText());
                        }
                        else if(tag.equals("allrgy_Info")){
                            buffer.append("알러지 정보: ");
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

                    Thread.sleep(100);

                    UH.post(new Runnable() {
                        @Override
                        public void run() {
                            data = getData();
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



