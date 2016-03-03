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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.zhihua.archiveterminal.Fragment.RightJieguoFragment;
import com.app.zhihua.archiveterminal.Fragment.RightTongjiFragment;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener{

    public View fragmentdivider;
    public View left_container;
    public View right_container;
    public Button btn_leftdown,btn_lefttop;    //要修改
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

        /*
        要修改成两个ListView
         */
        btn_leftdown = (Button)findViewById(R.id.button_leftdown);
        btn_leftdown.setOnClickListener(this);
        btn_lefttop = (Button)findViewById(R.id.button_lefttop);
        btn_lefttop.setOnClickListener(this);

        fragmentdivider = (View)findViewById(R.id.fragment_divider);
        fragmentdivider.setOnTouchListener(this);

        left_container = (View)findViewById(R.id.left_container);
        right_container = (View)findViewById(R.id.right_container);

        lp_left = (LinearLayout.LayoutParams) left_container.getLayoutParams();
        lp_right = (LinearLayout.LayoutParams) right_container.getLayoutParams();

        configToolbar();
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

    @Override
    public void onClick(View v) {                  //要修改为ListView点击事件
        switch (v.getId()){
            case R.id.button_lefttop:
                RightJieguoFragment rightJieguoFragment = new RightJieguoFragment();
                FragmentManager fragmentManager_t = getFragmentManager();
                FragmentTransaction transaction_t = fragmentManager_t.beginTransaction();
                transaction_t.replace(R.id.right_container, rightJieguoFragment);
                transaction_t.commit();
                break;
            case R.id.button_leftdown:
                RightTongjiFragment rightTongjiFragment = new RightTongjiFragment();
                FragmentManager fragmentManager_d = getFragmentManager();
                FragmentTransaction transaction_d = fragmentManager_d.beginTransaction();
                transaction_d.replace(R.id.right_container, rightTongjiFragment);
                transaction_d.commit();
        }
    }
}