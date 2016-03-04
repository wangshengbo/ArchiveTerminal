package com.app.zhihua.archiveterminal.Avtivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.app.zhihua.archiveterminal.Adapter.SimpleTreeListViewAdapter;
import com.app.zhihua.archiveterminal.Bean.FileBean;
import com.app.zhihua.archiveterminal.R;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class DeriveActivity extends AppCompatActivity {

    private Toolbar deriveToolbar;

    public ListView deriveResultsList;     //结果汇总目录
    public ListView deriveStatisticList;    //统计分析目录

    private SimpleTreeListViewAdapter<FileBean> deriveResultsAdapter;
    private SimpleTreeListViewAdapter<FileBean> deriveStatisticAdapter;
    private List<FileBean> deriveResultsDatas;
    private List<FileBean> deriveStatisticDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derive);

        deriveResultsList = (ListView) findViewById(R.id.list_results_derive);
        deriveStatisticList = (ListView) findViewById(R.id.list_statistic_derive);

        configToolbar();  //初始化标题栏
    }


    private void configToolbar() {             //设置标题栏
        deriveToolbar = (Toolbar) findViewById(R.id.toolbar_derive);
        setSupportActionBar(deriveToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);    //开启左上角图标
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("  智华计算机终端保密检查系统归档管理终端");
        getSupportActionBar().setLogo(R.drawable.logo);
        // 设置返回按钮点击事件--销毁当前Activity
        deriveToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}