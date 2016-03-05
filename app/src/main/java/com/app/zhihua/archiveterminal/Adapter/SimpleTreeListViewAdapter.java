package com.app.zhihua.archiveterminal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.utils.adapter.TreeListviewAdapter;
import com.app.zhihua.archiveterminal.utils.Node;

import java.util.List;

/**
 * Created by Oliver on 2016/3/2.
 */
public class SimpleTreeListViewAdapter<T> extends TreeListviewAdapter<T> {
    public SimpleTreeListViewAdapter(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        super(tree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.treelistview_item,parent,false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView)convertView.findViewById(R.id.item_icon);
            holder.mText = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(node.getIcon() == -1){
            holder.mIcon.setVisibility(View.INVISIBLE);
        }else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(node.getIcon());
        }
        holder.mText.setText(node.getName());
        return convertView;
    }

    private class ViewHolder{
        ImageView mIcon;
        TextView mText;
    }
}
