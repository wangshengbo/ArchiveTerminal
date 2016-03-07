package com.app.zhihua.archiveterminal.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.Utils.Adapter.DeriveBaseTreeListviewAdapter;
import com.app.zhihua.archiveterminal.Utils.Node;
import com.app.zhihua.archiveterminal.Utils.Adapter.MainBaseTreeListviewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class DeriveTreeListViewAdapater<T> extends DeriveBaseTreeListviewAdapter<T> {
    public DeriveTreeListViewAdapater(ListView tree, Context context, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        super(tree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.derivetreelistview_item,parent,false);
            holder = new ViewHolder();
            holder.mIcon = (ImageView)convertView.findViewById(R.id.item_icon);
            holder.mText = (TextView) convertView.findViewById(R.id.item_text);
            holder.mCheckBox = (CheckBox)convertView.findViewById(R.id.checkbox_derive);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(node.getIcon() == -1){
            holder.mIcon.setVisibility(View.INVISIBLE);
        }else {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(R.drawable.tree_ex);
        }
        holder.mText.setText(node.getName());
        return convertView;
    }

    private class ViewHolder{
        ImageView mIcon;
        TextView mText;
        CheckBox mCheckBox;
    }


}
