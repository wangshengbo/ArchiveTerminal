package com.app.zhihua.archiveterminal.Utils;



import com.app.zhihua.archiveterminal.R;
import com.app.zhihua.archiveterminal.Utils.Annotation.TreeNodeId;
import com.app.zhihua.archiveterminal.Utils.Annotation.TreeNodeLabel;
import com.app.zhihua.archiveterminal.Utils.Annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 2016/3/2.
 */
public class TreeHelper {
    public static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;
        for (T t:datas){
            int id = -1;
            int pid = -1;
            String label = null;

            node = new Node();
            Class tClass = t.getClass();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field:fields){
                if(field.getAnnotation(TreeNodeId.class) !=null){
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if(field.getAnnotation(TreeNodePid.class) !=null){
                    field.setAccessible(true);
                    pid = field.getInt(t);
                }
                if(field.getAnnotation(TreeNodeLabel.class) !=null){
                    field.setAccessible(true);
                    label = (String)field.get(t);
                }
            }
            node = new Node(id,pid,label);
            nodes.add(node);
        }
        for (int i = 0;i<nodes.size();i++)
        {
            Node n = nodes.get(i);
            for (int j=i+1;j<nodes.size();j++){
                Node m = nodes.get(j);

                if (m.getpId() == n.getId()){
                    n.getChildren().add(m);
                    m.setParent(n);
                }else if (n.getpId() == m.getId()){
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }
        for (Node n:nodes){
            setNodeIcon(n);
        }
        return nodes;
    }

    private static void setNodeIcon(Node n) {
        if(n.getChildren().size()>0 && n.isExpand()){
            n.setIcon(R.drawable.tree_ex);
        }else if(n.getChildren().size()>0 && !n.isExpand()){
            n.setIcon(R.drawable.tree_ec);
        }else {
            n.setIcon(-1);
        }
    }

    public static <T> List<Node> getSortedNodes(List<T> datas,int defaultExpandLevel) throws IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = convertDatas2Nodes(datas);
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node:rootNodes){
            addNode(result,node,defaultExpandLevel,1);
        }
        return result;
    }

    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {
        result.add(node);
        if(defaultExpandLevel>=currentLevel){
            node.setExpand(true);
        }
        if(node.isLeaf()) return;
        for (int i = 0;i<node.getChildren().size();i++){
            addNode(result,node.getChildren().get(i),defaultExpandLevel,currentLevel+1);
        }
    }

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();
        for (Node node:nodes){
            if(node.isRoot()){
                root.add(node);
            }
        }
        return root;
    }

    public static List<Node> filterVisibleNodes(List<Node> nodes){
        List<Node> result = new ArrayList<Node>();
        for (Node node:nodes){
            if(node.isRoot() || node.isParentExpand()){
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

}
