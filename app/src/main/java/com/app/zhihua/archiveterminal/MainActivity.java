package com.app.zhihua.archiveterminal;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getActionBar();
//        actionBar.setSubtitle();
//        actionBar.setLogo(R.drawable.logo);

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
                Toast.makeText(MainActivity.this, "push scan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_bar_derive:
                Toast.makeText(MainActivity.this, "push derive", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


        return true;
    }
}