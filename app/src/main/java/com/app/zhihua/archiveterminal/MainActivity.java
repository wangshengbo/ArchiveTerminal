package com.app.zhihua.archiveterminal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.app.zhihua.archiveterminal.Adapter.SimpleTreeListViewAdapter;
import com.app.zhihua.archiveterminal.Bean.FileBean;
import com.app.zhihua.archiveterminal.Fragment.RightJieguoFragment;
import com.app.zhihua.archiveterminal.Fragment.RightTongjiFragment;
import com.app.zhihua.archiveterminal.Utils.Adapter.TreeListviewAdapter;
import com.app.zhihua.archiveterminal.Utils.Node;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    public View fragmentdivider;
    public View left_container;
    public View right_container;
    public ListView resultsList;     //结果汇总目录
    public ListView statisticList;    //统计分析目录

    private SimpleTreeListViewAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;

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

        initDatas();   //初始化目录数据
        try {
            mAdapter = new SimpleTreeListViewAdapter<FileBean>(resultsList,this,mDatas,1);
            resultsList.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initClickEvent();     //初始化点击事件
    }

    private void initClickEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListviewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if(node.isLeaf()){

                    /********************************
                     添加点击事件展示报告
                     */
                    Toast.makeText(MainActivity.this,node.getName(),Toast.LENGTH_SHORT).show();
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

    private void initDatas() {
        /*
        获取扫描得到的报告数据
         */


        mDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1,0,"根目录1");mDatas.add(bean);
        bean = new FileBean(2,0,"根目录2");mDatas.add(bean);
        bean = new FileBean(3,0,"根目录3");mDatas.add(bean);
        bean = new FileBean(4,1,"根目录1-1");mDatas.add(bean);
        bean = new FileBean(5,1,"根目录1-2");mDatas.add(bean);
        bean = new FileBean(6,5,"根目录1-2-1");mDatas.add(bean);
        bean = new FileBean(7,3,"根目录3-1");mDatas.add(bean);
        bean = new FileBean(8,3,"根目录3-2");mDatas.add(bean);

    }

    private void configToolbar() {             //设置标题栏
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);    //开启左上角图标
//        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("  智华计算机终端保密检查系统归档管理终端");
        getSupportActionBar().setLogo(R.drawable.logo);
        // 设置返回按钮点击事件--销毁当前Activity
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
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
                /*
                要修改成跳转到导出功能
                 */
                Toast.makeText(MainActivity.this, "push derive", Toast.LENGTH_SHORT).show();
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

//    @Override
//    public void onClick(View v) {                  //要修改为ListView点击事件
//        switch (v.getId()){
//            case R.id.button_lefttop:
//                RightJieguoFragment rightJieguoFragment = new RightJieguoFragment();
//                FragmentManager fragmentManager_t = getFragmentManager();
//                FragmentTransaction transaction_t = fragmentManager_t.beginTransaction();
//                transaction_t.replace(R.id.right_container, rightJieguoFragment);
//                transaction_t.commit();
//                break;
//            case R.id.button_leftdown:
//                RightTongjiFragment rightTongjiFragment = new RightTongjiFragment();
//                FragmentManager fragmentManager_d = getFragmentManager();
//                FragmentTransaction transaction_d = fragmentManager_d.beginTransaction();
//                transaction_d.replace(R.id.right_container, rightTongjiFragment);
//                transaction_d.commit();
//        }
//    }
}