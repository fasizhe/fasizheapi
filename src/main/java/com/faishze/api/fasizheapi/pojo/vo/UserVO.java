package com.faishze.api.fasizheapi.pojo.vo;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
public class UserVO implements Serializable {

    private static final long serialVersionUID = -6716218810484315639L;

    private Integer id;

    private String username;

    public UserVO() {
    }

    public UserVO(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
