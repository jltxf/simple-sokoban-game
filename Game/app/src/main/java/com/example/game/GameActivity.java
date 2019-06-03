package com.example.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private TextView tvtitle;
    private int level;
    private GameView gv;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        level = getIntent().getIntExtra("position", 1);//有值写值，默认为1
        initView();
        gv.setLevel(level);
        initEvent();

    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.tv_title);
        tvtitle.setText("推箱子游戏第" + String.valueOf(level) + "关");
        gv = (GameView) findViewById(R.id.gv);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //把toolbar变成标题栏
        //要导入import android.support.v7.widget.Toolbar;包
        ActionBar actionBar = getSupportActionBar();//取得标题
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);//设置隐藏
        }
    }

    private void initEvent() {
        gv.setOnWinListener(new GameView.OnWinListener() {
            @Override
            public void onWin() {//复写GameView里面的onWin方法
                if (level < 4) {
                    //弹出提示对话框
                    new AlertDialog.Builder(GameActivity.this).setTitle("推箱子游戏").setMessage("恭喜你完成任务，恭喜恭喜！")
                            .setPositiveButton("下一关", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    level++;
                                    tvtitle.setText("推箱子游戏第" + String.valueOf(level) + "关");
                                    gv.setLevel(level);
                                    //对menu进行更新
                                    invalidateOptionsMenu();//回调
                                }
                            }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                } else if (level >= 4) {
                    new AlertDialog.Builder(GameActivity.this).setTitle("推箱子游戏").setMessage("恭喜你完成任务，恭喜恭喜！")
                            .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).show();
                }


            }
        });
    }

    //加载otption_menu.xml
//一定要返回true
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载资源文件
        getMenuInflater().inflate(R.menu.option_menu, menu);
        if(level==1){
            menu.getItem(1).setVisible(false);//隐藏item为1的item,item从0开始
        }else if(level==4){
            menu.getItem(2).setVisible(false);
        }
        return true;
    }

    //点击item下拉框做出反应item就是你点击的那个项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_restart:
                gv.setLevel(level);
                break;
            case R.id.item_previous:
                level--;
                tvtitle.setText("推箱子游戏第" + String.valueOf(level) + "关");
                gv.setLevel(level);
                //对menu进行更新
                invalidateOptionsMenu();//回调
                break;
            case R.id.item_next:
                level++;
                tvtitle.setText("推箱子游戏第" + String.valueOf(level) + "关");
                gv.setLevel(level);
                //对menu进行更新
                invalidateOptionsMenu();//回调
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
