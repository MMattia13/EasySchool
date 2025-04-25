package com.easyschool.backend.model;

public enum ERole {
    ADMIN("R001"),
    MODERATOR("R002"),
    USER("R003");

    private String code;

    private ERole(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
