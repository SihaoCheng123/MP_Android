package com.example.mealplanner.model.data;

public class Users {
    private Long id;
    private String email;
    private String password;
    private User_Data user_data;
    private String token;

    public Users(String email, String password, User_Data user_data) {
        this.email = email;
        this.password = password;
        this.user_data = user_data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User_Data getUser_data() {
        return user_data;
    }

    public void setUser_data(User_Data user_data) {
        this.user_data = user_data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

