package com.app.zhihua.archiveterminal.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.utils.Node;
import com.app.zhihua.archiveterminal.utils.TreeHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oliver on 2016/3/2.
 */
public abstract class DeriveBaseTreeListviewAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;
    protected LayoutInflater mInflater;
    protected ListView mTree;
    protected CheckBox mCheckBox = null;
    private static HashMap<Integer, Boolean> isCheckBoxSelected;        // 用来控制CheckBox的选中状况

    public DeriveBaseTreeListviewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        mContext = context;
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        mVisibleNodes = mAllNodes;
        mInflater = LayoutInflater.from(context);
        isCheckBoxSelected = new HashMap<Integer, Boolean>();
        initMapData();

        tree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCheckBox = (CheckBox) view.findViewById(R.id.checkbox_derive);
                mCheckBox.setChecked(!mCheckBox.isChecked());
                if (mVisibleNodes.get(position).getChildren().size() != 0){

                }


            }
        });
    }

    protected void initMapData(){
        for (int i=0;i < mVisibleNodes.size();i++){
            getIsSelected().put(i,false);
        }
    };


    @Override
    public int getCount() {
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;            //mVisibleNodes.get(position).getId()
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisibleNodes.get(position);
        convertView = getConvertView(node,position,convertView,parent);
        convertView.setPadding(node.getLevel()*30,3,3,3);

        return convertView;
    }

    public abstract View getConvertView(Node node,int position,View convertView,ViewGroup parent);
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isCheckBoxSelected;
    }
    public static void setIsSelected(HashMap<Integer, Boolean> isCheckBoxSelected) {
        DeriveBaseTreeListviewAdapter.isCheckBoxSelected = isCheckBoxSelected;
    }
}
