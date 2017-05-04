package com.hardToFind.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.Column;

/**
 * Created by zdrillings on 5/4/17.
 */
public class User {

    @Column(name = "ID")
    @JsonProperty("id")
    public int id;

    @JsonProperty("email")
    @Column(name = "EMAIL")
    public String email;

    public String toString(){
        return email + "--" + id;
    }

    public static class UserEnvelope {

        @JsonProperty("user")
        private User user;

        public UserEnvelope() {
        }

        public User getUser() {
            return this.user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
