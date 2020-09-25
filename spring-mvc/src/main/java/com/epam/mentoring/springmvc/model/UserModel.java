package com.epam.mentoring.springmvc.model;

import com.epam.mentoring.springmvc.entity.Role;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
**/
public class UserModel implements Serializable {

    @NotBlank(message = "{form.error.required}")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "{form.error.required}")
    @Size(max = 50)
    private String password;

    @NotBlank(message = "{form.error.required}")
    @Size(max = 50)
    private String address;

    private String photo;

    private Role role;

    public UserModel() {
    }

    public UserModel(final String username, final String password, final String address, final String photo, final Role role) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.photo = photo;
        this.role = role;
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
        final UserModel userModel = (UserModel) o;
        return Objects.equals(getUsername(), userModel.getUsername()) &&
                Objects.equals(getPassword(), userModel.getPassword()) &&
                Objects.equals(getAddress(), userModel.getAddress()) &&
                Objects.equals(getPhoto(), userModel.getPhoto()) &&
                getRole() == userModel.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getAddress(), getPhoto(), getRole());
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", role=" + role +
                '}';
    }
}
