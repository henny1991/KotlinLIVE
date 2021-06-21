 package com.yhe.kotlinlive.test;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

 public class JavaMain {

    private static HashMap<Flag, String> mMap = new HashMap<Flag, String>();

    public static void main(String[] args) {
        /*Short aShort = (short) 3;
        System.out.println("" + ~-1);*/

        mMap.put(new Flag(), "aaa");
        mMap.put(new Flag(), "bbb");
        System.out.println("mMap size: " + mMap.size());//2
        for (HashMap.Entry<Flag, String> entry: mMap.entrySet()){
            System.out.println("mMap entry: " + entry);
        }
    }

    static class Flag{

        @Override
        public int hashCode() {
            int hashCode = 111; //如果在此强行将该方法返回同一个结果，则toString()的结果也将一致
            System.out.println("hashCode: " + hashCode);
            return hashCode;
        }

        @NonNull
        @NotNull
        @Override
        public String toString() {
            return super.toString();
        }
    }
}

