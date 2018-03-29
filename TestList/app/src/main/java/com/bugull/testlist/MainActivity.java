package com.bugull.testlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    MyAdapter myAdapter;
    List<Hospital> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list);
        mDatas = new ArrayList<>();
        for (int i = 0;i<6;i++){
            Hospital hospital = new Hospital();
            hospital.setDepartment("精神科");
            hospital.setDiagnositicsPlaceId(i);
            hospital.setDoctorId("doc"+i);
            hospital.setHospotial("人民医院"+i);
            hospital.setShowDelete(false);
            mDatas.add(hospital);
        }
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        myAdapter = new MyAdapter(mDatas,this,dm.widthPixels,mListView,false);
        mListView.setAdapter(myAdapter);
        myAdapter.setContentListener(new MyAdapter.OnContentClickListener() {
            @Override
            public void setContentListener(Hospital hospital, int type) {
                if (type==1)
                Toast.makeText(MainActivity.this,hospital.getHospotial(),Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
