package com.mancala.api.mapper;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PitsUtil {

    private static final Gson gson = new Gson();

    public static String toString(List<Integer> pits) {
        return gson.toJson(pits);
    }
    public static List<Integer> toList(String pitStr) {
        final Type type = TypeToken.getParameterized(List.class, Integer.class).getType();
        return gson.fromJson(pitStr, type);
    }
}
