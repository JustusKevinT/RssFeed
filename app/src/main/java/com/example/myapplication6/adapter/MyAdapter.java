package com.example.myapplication6.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication6.databinding.LayoutItemBinding;
import com.example.myapplication6.interfaces.IRecyclerItemClickListener;
import com.example.myapplication6.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Item> items;
    private IRecyclerItemClickListener listener;

    public MyAdapter(List<Item> items, IRecyclerItemClickListener listener){
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemBinding binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.binding.txtTitle.setText(items.get(position).title);
        holder.binding.txtPublished.setText(items.get(position).pubDate);
        holder.binding.txtAuthor.setText(items.get(position).creator);
        Picasso.get()
                .load(items.get(position).content.url)
                .resize(80,80)
                .centerCrop()
                .into(holder.binding.image);
        holder.binding.card.setOnClickListener(view -> listener.onItemClick(items.get(position).link));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LayoutItemBinding binding;
        public MyViewHolder(@NonNull LayoutItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
