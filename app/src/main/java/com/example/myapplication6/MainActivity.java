package com.example.myapplication6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication6.adapter.MyAdapter;
import com.example.myapplication6.databinding.ActivityMainBinding;
import com.example.myapplication6.interfaces.IRecyclerItemClickListener;
import com.example.myapplication6.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements IRecyclerItemClickListener {

    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initViewModel();
        initViews();
        initObserve();
    }

    private void initObserve() {
        binding.recyclerRes.showShimmer();
        viewModel.getRss().observe(this, rss -> {
            MyAdapter adapter = new MyAdapter(rss.channel.items, this);
            binding.recyclerRes.setAdapter(adapter);
            binding.recyclerRes.hideShimmer();
        });
        viewModel.getError().observe(this, error -> Toast.makeText(MainActivity.this,error,Toast.LENGTH_LONG).show());
    }

    private void initViews() {
        binding.recyclerRes.setHasFixedSize(true);
        binding.recyclerRes.setLayoutManager(new LinearLayoutManager(this));
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.recyclerRes.showShimmer();
                viewModel.getRss();
            }
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void initBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onItemClick(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}