package com.app.zhihua.archiveterminal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.utils.Node;

/**
 * Created by Administrator on 2016/3/1.
 */
public class ResultsShowFragment extends android.app.Fragment {
    private Node n;
    private TextView textView;
    public ResultsShowFragment(Node node) {
        n = node;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_show_fragment,container,false);
        textView = (TextView) view.findViewById(R.id.result_show);
        textView.setText(n.getName());
        return view;
    }
}
