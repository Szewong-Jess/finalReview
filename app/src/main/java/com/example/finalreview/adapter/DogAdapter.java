package com.example.finalreview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalreview.databinding.LayoutDogitemBinding;
import com.example.finalreview.model.Dog;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {
    List<Dog> DogList;

    public DogAdapter() {
    }

    public DogAdapter(List<Dog> dogList) {
        DogList = dogList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutDogitemBinding binding = LayoutDogitemBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        DogViewHolder holder = new DogViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.binding.txtViewId.setText(String.valueOf(DogList.get(position).getDogid()));
        holder.binding.txtViewBreed.setText(DogList.get(position).getDogType());
        holder.binding.txtViewName.setText(DogList.get(position).getDogName());
        holder.binding.imgViewDogPic.setText(DogList.get(position).getDogTypeTgt());
        holder.binding.txtViewDOB.setText(DogList.get(position).getDogDob());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DogViewHolder extends  RecyclerView.ViewHolder{
        LayoutDogitemBinding binding;

        public DogViewHolder(@NonNull LayoutDogitemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }

}
