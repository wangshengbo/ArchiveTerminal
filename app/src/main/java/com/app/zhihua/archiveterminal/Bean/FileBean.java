package com.app.zhihua.archiveterminal.bean;


import com.app.zhihua.archiveterminal.utils.annotation.TreeNodeId;
import com.app.zhihua.archiveterminal.utils.annotation.TreeNodeLabel;
import com.app.zhihua.archiveterminal.utils.annotation.TreeNodePid;

import java.io.Serializable;

/**
 * Created by Oliver on 2016/3/2.
 */
public class FileBean implements Serializable{

    // 添加serialVersionUID
    private static final long serialVersionUID = 1090268812417986114L;

    @TreeNodeId
    private int id;
    @TreeNodePid
    private int pId;
    @TreeNodeLabel
    private String label;

    public FileBean(int id, int pId, String label) {
        this.id = id;
        this.pId = pId;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
