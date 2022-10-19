package com.mldt.puppypals.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Dog;
import com.mldt.puppypals.R;

import org.w3c.dom.Text;

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

    @Override
    public void onBindViewHolder(@NonNull MyPetsRecyclerViewAdapter.MyPetsViewHolder holder, int position){
        TextView myPetsNameTV = holder.itemView.findViewById(R.id.myPetsFragmentName);
        String petName = dogList.get(position).getDogName();
        myPetsNameTV.setText(petName);

        TextView myPetsBreedTV = holder.itemView.findViewById(R.id.myPetsFragmentBreed);
        String petBreed = dogList.get(position).getDogBreed();
        myPetsBreedTV.setText(petBreed);

        TextView myPetsBioTV = holder.itemView.findViewById(R.id.myPetsFragmentBio);
        String petBio = dogList.get(position).getDogBio();
        myPetsBioTV.setText(petBio);

    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }

    public static class MyPetsViewHolder extends RecyclerView.ViewHolder {
        public MyPetsViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
