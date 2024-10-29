package com.example.myapplication6;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {

    ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        navigateUrl();
    }

    private void navigateUrl() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String url = extras.getString("url");
            if(!TextUtils.isEmpty(url)){
                binding.webView.loadUrl(url);
            }
        }
    }

    private void initBinding() {
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}