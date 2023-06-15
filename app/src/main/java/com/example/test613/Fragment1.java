package com.example.test613;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Fragment1 extends Fragment {


    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReference = mDatabase.child("Data");

    Spinner spSeason, spAge, spmn;
    TextView textView;
    Button search;

    final String[] season = {"전체", "봄", "여름", "가을", "겨울" };
    final String[] age = {"전체", "19-29세", "30-49세", "50-64세", "65세 이상" };
    final String[] mn = {"전체", "남자", "여자" };

    ArrayAdapter<String> adapter;
    Context ct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ct = container.getContext();
        View view = inflater.inflate(R.layout.layout1, container, false);

        textView = view.findViewById(R.id.tv);
        search = view.findViewById(R.id.search);

        spSeason = view.findViewById(R.id.Season);
        adapter = new ArrayAdapter<String>(ct,
                android.R.layout.simple_spinner_item, season);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSeason.setAdapter(adapter);

        spAge = view.findViewById(R.id.Age);
        adapter = new ArrayAdapter<String>(ct,
                android.R.layout.simple_spinner_item, age);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAge.setAdapter(adapter);


        spmn = view.findViewById(R.id.mn);
        adapter = new ArrayAdapter<String>(ct,
                android.R.layout.simple_spinner_item, mn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmn.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if(i==1){
                    databaseReference.child("Spring").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //HashMap<String, String> hs = snapshot.getValue(HashMap.class);
                            Object value = snapshot.getValue(Object.class);
                            textView.setText(value.toString());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }else if(i==2){
                    databaseReference.child("Summer").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //HashMap<String, String> hs = snapshot.getValue(HashMap.class);
                            Object value = snapshot.getValue(Object.class);
                            textView.setText(value.toString());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }if(i==3){
                    databaseReference.child("Autumn").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //HashMap<String, String> hs = snapshot.getValue(HashMap.class);
                            Object value = snapshot.getValue(Object.class);
                            textView.setText(value.toString());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }if(i==4){
                    databaseReference.child("Winter").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //HashMap<String, String> hs = snapshot.getValue(HashMap.class);
                            Object value = snapshot.getValue(Object.class);
                            textView.setText(value.toString());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });

        return view;
    }
}
