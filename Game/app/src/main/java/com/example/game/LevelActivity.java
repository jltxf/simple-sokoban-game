package com.example.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {
private GridView gv_level;
private List<String> levels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        initView();
        initEvents();
    }

    private void initEvents() {
        gv_level.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //view当前的布局，position位置0开始 id 位置a b....
                //AdapterView<?> parent 代表gv_level

                Intent intent = new Intent(LevelActivity.this,GameActivity.class);
                intent.putExtra("position",position+1);//position最低值为0，我们要的数据最低为1
                startActivity(intent);
            }
        });
    }

    private void initView(){
        gv_level= findViewById(R.id.gv_level);
        for (int i =0;i<MapData.MAX_LEVEL;i++){
            levels.add("第"+String.valueOf(i+1)+"关");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.gv_item,R.id.tv_level,levels);
//        MyGVAdapter adapter = new MyGVAdapter(this,levels);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,levels);
       gv_level.setAdapter(adapter);
    }

}
