package com.pvzer.furniture.utils;

public class CurrentHolder {
    public static final ThreadLocal<Integer> CURRENT_HOLDER = new ThreadLocal<>();

    public static void setCurrentId(Integer userId){
        CURRENT_HOLDER.set(userId);
    }

    public static Integer getCurrentId(){
        return CURRENT_HOLDER.get();
    }
    public static  void clear(){
        CURRENT_HOLDER.remove();
    }
}
