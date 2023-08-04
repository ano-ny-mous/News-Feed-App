package com.example.android.instantnews;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class LocalFragment extends Fragment {
    private DatabaseReference mDatabase;

    private Button btnAdd;
    private EditText etText;
    private ListView listView;
    private ToggleButton toggleButton;
    boolean toggleValue=true;



    ArrayList<NewsFeedLocal>  feedlocallist = new ArrayList<>();
    NewsFeedLocalAdapter adapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("News Feed Added");


        adapter = new NewsFeedLocalAdapter(getContext(),feedlocallist);

        btnAdd = (Button)view.findViewById(R.id.btnAdd);
        etText = (EditText)view.findViewById(R.id.etDatabase);
        listView = (ListView)view.findViewById(R.id.database_list_view);
        toggleButton= (ToggleButton) view.findViewById(R.id.toggle);




        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g =new GPSTracker(getActivity().getApplicationContext());
                Location l= g.getLocation();
                if(l!=null)
                {
                    double lat=l.getLatitude();
                    double lon=l.getLongitude();
                }
                mDatabase.push().setValue(etText.getText().toString());
            }
        });

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String string = dataSnapshot.getValue(String.class);
                NewsFeedLocal temp = new NewsFeedLocal(string);
                feedlocallist.add(temp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        SeekBar seekBar = view.findViewById(R.id.seek_bar);
        final TextView textView =view.findViewById(R.id.text_view);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int distance=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distance = progress;
                textView.setText(""+distance+" m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked())
                {
                    toggleValue=false;
                }
                else
                {
                    toggleValue=true;
                }
            }
        });
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return toggleValue;
            }
        });








        // Inflate the layout for this fragment
        return view;
    }

}