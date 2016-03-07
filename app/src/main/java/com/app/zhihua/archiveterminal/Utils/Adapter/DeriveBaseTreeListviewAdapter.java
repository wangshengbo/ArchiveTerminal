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

//    public interface OnTreeNodeClickListener{
//        void onClick(Node node, int position);
//    }
//    private OnTreeNodeClickListener mListener;
//    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
//        this.mListener = mListener;
//    }

    public DeriveBaseTreeListviewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        mContext = context;
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        mVisibleNodes = mAllNodes;
        mInflater = LayoutInflater.from(context);

        tree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCheckBox = (CheckBox) view.findViewById(R.id.checkbox_derive);
                mCheckBox.setChecked(!mCheckBox.isChecked());
                if (mVisibleNodes.get(position).getChildren().size() != 0){

                }


//                expandOrCollapse(position);
//                if(mListener != null){
//                    mListener.onClick(mVisibleNodes.get(position),position);
//                }
            }
        });
    }

//    private void expandOrCollapse(int position) {
//        Node n = mVisibleNodes.get(position);
//        if (n!= null){
//            if(n.isLeaf()) return;
//            n.setExpand(!n.isExpand());
//            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
//            notifyDataSetChanged();
//        }
//    }

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
}
