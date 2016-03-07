package com.app.zhihua.archiveterminal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.app.zhihua.archiveterminal.adapter.DeriveTreeListViewAdapater;
import com.app.zhihua.archiveterminal.bean.FileBean;
import com.app.zhihua.archiveterminal.R;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class DeriveActivity extends AppCompatActivity {

    private static final String TAG = "DeriveActivity";

    public static final String KEY_RESULT_DATAS = "key_result_datas";
    public static final String KEY_STATISTIC_DATAS = "key_statistic_datas";

    private Toolbar deriveToolbar;

    public ListView deriveResultsList;     //结果汇总目录
    public ListView deriveStatisticList;    //统计分析目录
    public Button btnAllSelect,btnReSelect,btnConfirm;

    private DeriveTreeListViewAdapater<FileBean> deriveResultsAdapter;
    private DeriveTreeListViewAdapater<FileBean> deriveStatisticAdapter;
    private List<FileBean> deriveResultsDatas;
    private List<FileBean> deriveStatisticDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derive);

        deriveResultsList = (ListView) findViewById(R.id.list_results_derive);
        deriveStatisticList = (ListView) findViewById(R.id.list_statistic_derive);

        btnAllSelect = (Button)findViewById(R.id.btn_all_select);
        btnReSelect = (Button)findViewById(R.id.btn_re_select);
        btnConfirm = (Button)findViewById(R.id.btn_confirm);

        configToolbar();  //初始化标题栏

        getDataPassed();  //获得从MainActivity传递过来的两个数据

        try {
            deriveResultsAdapter = new DeriveTreeListViewAdapater<FileBean>(deriveResultsList,this, deriveResultsDatas,1);
            deriveResultsList.setAdapter(deriveResultsAdapter);
            deriveStatisticAdapter = new DeriveTreeListViewAdapater<FileBean>(deriveStatisticList,this, deriveStatisticDatas,1);
            deriveStatisticList.setAdapter(deriveStatisticAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAllSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < deriveResultsDatas.size(); i++) {
                    deriveResultsAdapter.getIsSelected().put(i, true);
                }
                for (int i = 0; i < deriveStatisticDatas.size(); i++) {
                    deriveStatisticAdapter.getIsSelected().put(i, true);
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });
        btnReSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 遍历list的长度，将MyAdapter中的map值全部设为false
                for (int i = 0; i < deriveResultsDatas.size(); i++) {
                    deriveResultsAdapter.getIsSelected().put(i, false);
                }
                for (int i = 0; i < deriveStatisticDatas.size(); i++) {
                    deriveStatisticAdapter.getIsSelected().put(i, false);
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                增加确定导出功能
                 */
            }
        });
    }

    private void dataChanged() {
        // 通知listView刷新
        deriveResultsAdapter.notifyDataSetChanged();
        deriveStatisticAdapter.notifyDataSetChanged();
    }


    /**
     * 接收上一个Activity传递来的数据
     */
    private void getDataPassed() {
        Intent intent = getIntent();
        // 防空指针先判断
        if (intent.hasExtra(KEY_RESULT_DATAS) && intent.hasExtra(KEY_STATISTIC_DATAS)) {
            deriveResultsDatas = (List<FileBean>) intent.getSerializableExtra(KEY_RESULT_DATAS);
            deriveStatisticDatas = (List<FileBean>) intent.getSerializableExtra(KEY_STATISTIC_DATAS);
        }
    }


    private void configToolbar() {             //设置标题栏
        deriveToolbar = (Toolbar) findViewById(R.id.toolbar_derive);
        setSupportActionBar(deriveToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);    //开启左上角图标
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("  智华计算机终端保密检查系统归档管理终端");
        getSupportActionBar().setLogo(R.drawable.logo);
        // 设置返回按钮点击事件--销毁当前Activity--
        /*
        是否要传递数据回MainActivity？？？？？？？？？？？？？
         */
        deriveToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}