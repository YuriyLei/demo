package com.yulei.demo.model;

import com.yulei.demo.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lei.yu on 2016/5/5.
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity<Long> implements Serializable {
    @Column
    private String permissionName;
    @Column
    private String permissionExpression;
    @Column
    private String permissionRemark;

    public String getPermissionRemark() {
        return permissionRemark;
    }

    public void setPermissionRemark(String permissionRemark) {
        this.permissionRemark = permissionRemark;
    }

    public Permission() {
    }

    public String getPermissionExpression() {
        return permissionExpression;
    }

    public void setPermissionExpression(String permissionExpression) {
        this.permissionExpression = permissionExpression;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
