package com.lyoto.zhonghai.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyoto.zhonghai.Bean.Annotation.NeedEncryption;
import lombok.Data;

/**
 * @author Justmemoryl Email: justmemoryl@foxmail.com
 * @version 1.0 created at 2019/08/16 10:40
 **/
@Data
@TableName("sys_manager")
@NeedEncryption
public class ManagerPO implements Serializable {
    @TableId(type = IdType.AUTO)
    @Column(name = "manager_id", nullable = false)
    private Integer managerId;
    @Column(name = "manager_name", nullable = false, length = 50)
    private String  managerName;
    @NeedEncryption
    @Column(name = "account", unique = true, nullable = false, length = 80)
    private String  account;
    @Column(name = "password", nullable = false, length = 100)
    private String  password;
    @Column(name = "email", length = 50)
    private String  email;
    @Column(name = "description", length = 80)
    private String  description;
    @Column(name = "type", nullable = false, length = 20)
    private Integer type;
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 管理员状态,true-启用 false-停用
     */
    @Column(name = "manager_status", nullable = false)
    private Boolean managerStatus;
    @Column(name = "group_code", nullable = false, length = 32)
    private String  groupCode;
    @Column(name = "parent_id", nullable = false)
    private Integer parentId;
    @Column(name = "risk_warning", nullable = false, columnDefinition = "int default 0")
    private Integer riskWarning;
    @Column(name = "create_time", updatable = false, nullable = false)
    private Date    createTime;
    @Column(name = "update_time", insertable = false)
    private Date    updateTime;

}
