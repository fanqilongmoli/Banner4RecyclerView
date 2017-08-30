package com.fanqilong.banner4recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerBanner recyclerBanner;

    private ArrayList<BannerEntity> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerBanner = ((RecyclerBanner) findViewById(R.id.recyclerBanner));

        initData();
    }

    private void initData() {

    }

    int i;

    public void update(View view) {
        i++;
        urls.clear();
        if (i % 2 == 0) {
            urls.add(new BannerEntity("http://pic.58pic.com/58pic/12/46/13/03B58PICXxE.jpg"));
            urls.add(new BannerEntity("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg"));
            urls.add(new BannerEntity("http://pic34.nipic.com/20131025/2531170_132447503000_2.jpg"));
            urls.add(new BannerEntity("http://img5.imgtn.bdimg.com/it/u=3462610901,3870573928&fm=206&gp=0.jpg"));
        } else {
            urls.add(new BannerEntity("http://img0.imgtn.bdimg.com/it/u=726278301,2143262223&fm=11&gp=0.jpg"));
            urls.add(new BannerEntity("http://pic51.nipic.com/file/20141023/2531170_115622554000_2.jpg"));
            urls.add(new BannerEntity("http://img3.imgtn.bdimg.com/it/u=2968209827,470106340&fm=21&gp=0.jpg"));
        }

        recyclerBanner.setDatas(urls);

    }
}
