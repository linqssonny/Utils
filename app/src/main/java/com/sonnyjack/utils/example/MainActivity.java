package com.sonnyjack.utils.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sonnyjack.utils.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> strs = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        String[] arrays = {"1", "2"};
        CollectionUtils.isEmpty(arrays);
    }
}
