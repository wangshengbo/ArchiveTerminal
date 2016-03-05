package com.app.zhihua.archiveterminal.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.app.zhihua.archiveterminal.adapter.SimpleTreeListViewAdapter;
import com.app.zhihua.archiveterminal.bean.FileBean;
import com.app.zhihua.archiveterminal.fragment.ResultsShowFragment;
import com.app.zhihua.archiveterminal.fragment.StatisticShowFragment;
import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.utils.adapter.TreeListviewAdapter;
import com.app.zhihua.archiveterminal.utils.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    public View fragmentdivider;
    public View left_container;
    public View right_container;
    public ListView resultsList;     //结果汇总目录
    public ListView statisticList;    //统计分析目录

    private SimpleTreeListViewAdapter<FileBean> resultsAdapter;
    private SimpleTreeListViewAdapter<FileBean> statisticAdapter;
    private List<FileBean> resultsDatas;
    private List<FileBean> statisticDatas;

    public LinearLayout.LayoutParams lp_left,lp_right;
    int lastX;
    int screenWidth;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display dis=this.getWindowManager().getDefaultDisplay();
        screenWidth=dis.getWidth();

        resultsList = (ListView) findViewById(R.id.list_results);
        statisticList = (ListView) findViewById(R.id.list_statistic);

        fragmentdivider = (View)findViewById(R.id.fragment_divider);
        fragmentdivider.setOnTouchListener(this);

        left_container = (View)findViewById(R.id.left_container);
        right_container = (View)findViewById(R.id.right_container);

        lp_left = (LinearLayout.LayoutParams) left_container.getLayoutParams();
        lp_right = (LinearLayout.LayoutParams) right_container.getLayoutParams();

        configToolbar();   //设置标题栏

        initResultsDatas();   //初始化结果汇总目录数据
        initStatisticDatas();   //初始化统计分析目录数据

        try {
            resultsAdapter = new SimpleTreeListViewAdapter<FileBean>(resultsList,this, resultsDatas,1);
            resultsList.setAdapter(resultsAdapter);
            statisticAdapter = new SimpleTreeListViewAdapter<FileBean>(statisticList,this, statisticDatas,1);
            statisticList.setAdapter(statisticAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        initResultsClickEvent();     //初始化结果汇总点击事件
        initStatisticClickEvent();     //初始化统计分析点击事件
    }

    private void initStatisticClickEvent() {                                //初始化统计分析点击事件
        statisticAdapter.setOnTreeNodeClickListener(new TreeListviewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    /********************************
                     添加点击事件展示报告
                     */
                    StatisticShowFragment statisticShowFragment = new StatisticShowFragment(node);
                    FragmentManager fragmentManager_s = getFragmentManager();
                    FragmentTransaction transaction_s = fragmentManager_s.beginTransaction();
                    transaction_s.replace(R.id.right_container, statisticShowFragment);
                    transaction_s.commit();
                }
            }
        });
        resultsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*****************************
                 添加长按删除功能
                 */
                return true;
            }
        });
    }

    private void initResultsClickEvent() {                      //初始化结果汇总点击事件
        resultsAdapter.setOnTreeNodeClickListener(new TreeListviewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    /********************************
                     添加点击事件展示报告
                     */
                    ResultsShowFragment resultsShowFragment = new ResultsShowFragment(node);
                    FragmentManager fragmentManager_r = getFragmentManager();
                    FragmentTransaction transaction_r = fragmentManager_r.beginTransaction();
                    transaction_r.replace(R.id.right_container, resultsShowFragment);
                    transaction_r.commit();
                }
            }
        });
        resultsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*****************************
                 添加长按删除功能
                 */
                return true;
            }
        });
    }

    private void initStatisticDatas() {
        /*
        获取扫描得到的统计分析报告数据
         */
        statisticDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1,0,"根目录1");
        statisticDatas.add(bean);
        bean = new FileBean(2,0,"根目录2");
        statisticDatas.add(bean);
        bean = new FileBean(3,0,"根目录3");
        statisticDatas.add(bean);
    }

    private void initResultsDatas() {
        /*
        获取扫描得到的结果汇总报告数据
         */
        resultsDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1,0,"根目录1");
        resultsDatas.add(bean);
        bean = new FileBean(2,0,"根目录2");
        resultsDatas.add(bean);
        bean = new FileBean(3,0,"根目录3");
        resultsDatas.add(bean);
        bean = new FileBean(4,1,"根目录1-1");
        resultsDatas.add(bean);
        bean = new FileBean(5,1,"根目录1-2");
        resultsDatas.add(bean);
        bean = new FileBean(6,5,"根目录1-2-1");
        resultsDatas.add(bean);
        bean = new FileBean(7,3,"根目录3-1");
        resultsDatas.add(bean);
        bean = new FileBean(8,3,"根目录3-2");
        resultsDatas.add(bean);
    }

    private void configToolbar() {             //设置标题栏
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("  智华计算机终端保密检查系统归档管理终端");
        getSupportActionBar().setLogo(R.drawable.logo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_bar_scan:
                /*
                要修改成跳转到扫描功能
                 */
                Toast.makeText(MainActivity.this, "push scan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_bar_derive:
                Intent intentToDerive = new Intent(MainActivity.this,DeriveActivity.class);
                intentToDerive.putExtra(DeriveActivity.KEY_RESULT_DATAS, (Serializable) resultsDatas);
                startActivity(intentToDerive);
                break;
            default:
                break;
        }
        return true;
    }

    public boolean onTouch(View v, MotionEvent event)     //实现分割线拖动功能
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int dx=(int)event.getRawX()-lastX;
                int newWidth_left = left_container.getWidth() + dx;
                int newWidth_right = screenWidth - newWidth_left - fragmentdivider.getWidth();
                if(newWidth_left>=200 && newWidth_right>=200){           //滑动阈值
                    lp_left.width = newWidth_left;
                    lp_right.width = newWidth_right;
                    left_container.setLayoutParams(lp_left);
                    right_container.setLayoutParams(lp_right);
                }
                lastX = (int)event.getRawX();
                break;
            case MotionEvent.ACTION_DOWN:
                lastX = (int)event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}