package com.app.zhihua.archiveterminal.Bean;


import com.app.zhihua.archiveterminal.Utils.Annotation.TreeNodeId;
import com.app.zhihua.archiveterminal.Utils.Annotation.TreeNodeLabel;
import com.app.zhihua.archiveterminal.Utils.Annotation.TreeNodePid;

/**
 * Created by Oliver on 2016/3/2.
 */
public class FileBean {
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