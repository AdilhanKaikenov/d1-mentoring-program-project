package com.epam.mentoring.springmvc.vo;

import com.epam.mentoring.springmvc.entity.Role;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
**/
public class UserVO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String address;

    private String photo;

    private Role role;

    public UserVO() {
    }

    public UserVO(final Long id, final String username, final String password, final String address, final String photo, final Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.photo = photo;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserVO userVO = (UserVO) o;
        return Objects.equals(getId(), userVO.getId()) &&
                Objects.equals(getUsername(), userVO.getUsername()) &&
                Objects.equals(getPassword(), userVO.getPassword()) &&
                Objects.equals(getAddress(), userVO.getAddress()) &&
                Objects.equals(getPhoto(), userVO.getPhoto()) &&
                getRole() == userVO.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getAddress(), getPhoto(), getRole());
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", role=" + role +
                '}';
    }
}
