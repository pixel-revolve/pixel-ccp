package com.dyh.utils;

import com.dyh.entity.dto.PUserDTO;

public class UserHolder {
    private static final ThreadLocal<PUserDTO> tl = new ThreadLocal<>();

    public static void saveUser(PUserDTO user){
        tl.set(user);
    }

    public static PUserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
