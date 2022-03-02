package com.example.payucart.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.payucart.R;
import com.example.payucart.adapter.CompletedTaskAdapter;
import com.example.payucart.model.completed.CompletedTask;

import java.util.ArrayList;

public class CompletedTaskFragment extends Fragment {

    private RecyclerView recyclerViewCompletedTask;
    private ArrayList<CompletedTask> completedTasks;
    private CompletedTaskAdapter completedTaskAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_completed_task, container, false);

        recyclerViewCompletedTask=view.findViewById(R.id.completed_task_recyclerview);

        recyclerViewCompletedTask.setHasFixedSize(true);
        recyclerViewCompletedTask.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        completedTasks=new ArrayList<>();

        completedTasks.add(new CompletedTask(R.drawable.logo,"Shubham","12:2:12"));

        completedTasks.add(new CompletedTask(R.drawable.logo,"Shubham","12:2:12"));

        completedTasks.add(new CompletedTask(R.drawable.logo,"Shubham","12:2:12"));

        completedTasks.add(new CompletedTask(R.drawable.logo,"Shubham","12:2:12"));

        completedTasks.add(new CompletedTask(R.drawable.logo,"Shubham","12:2:12"));

        completedTasks.add(new CompletedTask(R.drawable.logo,"Shubham","12:2:12"));

        completedTaskAdapter=new CompletedTaskAdapter(completedTasks,getContext());

        recyclerViewCompletedTask.setAdapter(completedTaskAdapter);


        return view;
    }
}