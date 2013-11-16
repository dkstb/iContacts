package com.iContacts.bitschool.domain;

import java.io.Serializable;

public class  Users implements Serializable{
    
    ///Field
    private int id;
    private String name;
    private String email;
    private String password;

    ///Constructor
    public Users(){
            System.out.println("UserVO Default Constructor");
    }

    ///Method(getter/setter Method)
    public final int getId() {
            return id;
    }
    
    public final void setId(int id) {
            this.id = id;
    }
    public String getPassword() {
            return password;
    }
    public void setPassword(String password) {
            this.password = password;
    }
    public final String getName() {
            return name;
    }

    public final void setName(String name) {
            this.name = name;
    }

    public final String getEmail() {
            return email;
    }

    public final void setEmail(String email) {
            this.email = email;
    }

    @Override
    public String toString() {
            return "User [name=" + name + ", email=" + email + ", password="
                            + password + "]";
    }
}//end of class