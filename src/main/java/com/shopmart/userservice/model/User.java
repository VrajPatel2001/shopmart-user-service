package com.shopmart.userservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "user")
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull
        private String name;
        @NotNull
        private String email;

        private String password;
        @NotNull
        private String phone;

        @Transient
        @NotNull
        private String passPlainText;
        @Transient
        @NotNull
        private String passPlainTextConfirm;

        public User() {
        }

        public User(String name, String email, String phone,String passPlainText,String passPlainTextConfirm) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.passPlainText = passPlainText;
            this.passPlainTextConfirm = passPlainTextConfirm;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassPlainText() {
        return passPlainText;
    }

    public void setPassPlainText(String passPlainText) {
        this.passPlainText = passPlainText;
    }

    public String getPassPlainTextConfirm() {
        return passPlainTextConfirm;
    }

    public void setPassPlainTextConfirm(String passPlainTextConfirm) {
        this.passPlainTextConfirm = passPlainTextConfirm;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
