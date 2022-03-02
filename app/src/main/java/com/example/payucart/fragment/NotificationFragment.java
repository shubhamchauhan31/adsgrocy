package com.example.payucart.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.payucart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationFragment extends Fragment {

    private RecyclerView recyclerViewNotification;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerViewNotification=view.findViewById(R.id.notification_reyclerview);
        recyclerViewNotification.setHasFixedSize(true);
        recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));



        FirebaseMessaging.getInstance().subscribeToTopic("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "DONE";
                        if (!task.isSuccessful()) {
                            msg = "FAILED";
                        }
                        Log.d("TAG", msg);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

       // Intent intent=new Intent(getContext(),)

        return view;
    }
}