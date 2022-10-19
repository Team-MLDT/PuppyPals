package com.mldt.puppypals.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Dog;
import com.mldt.puppypals.R;

import java.util.List;

public class MyPetsRecyclerViewAdapter extends RecyclerView.Adapter<MyPetsRecyclerViewAdapter.MyPetsViewHolder> {

    List<Dog> dogList;
    Context callingActivity;

    public MyPetsRecyclerViewAdapter(List<Dog> dogList, Context callingActivity){
        this.dogList = dogList;
        this. callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public MyPetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View myPetsFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_pets, parent, false);
        return new MyPetsViewHolder(myPetsFragment);
    }

    public static class MyPetsViewHolder extends RecyclerView.ViewHolder {
        public MyPetsViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
