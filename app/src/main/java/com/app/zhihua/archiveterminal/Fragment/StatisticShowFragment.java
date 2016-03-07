package com.app.zhihua.archiveterminal.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.Utils.Node;

/**
 * Created by Administrator on 2016/3/1.
 */
public class StatisticShowFragment extends android.app.Fragment {
        private Node n;
        private TextView textView;
        public StatisticShowFragment(Node node) {
                n = node;
        }

        @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_show_fragment,container,false);
                textView = (TextView) view.findViewById(R.id.statistic_show);
                textView.setText(n.getName());
        return view;
        }
}
