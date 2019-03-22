package com.pizza.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {

	public static String createJsonString(Object value) {
		Gson gson = new Gson();
		String string = gson.toJson(value);
		return string;
	}

	public static List<?> StringFromJson(String jsondata) {
		Type listType = new TypeToken<List<?>>() {}.getType();	
		Gson gson = new Gson();
		ArrayList<?> list = gson.fromJson(jsondata, listType);
		return list;
	}
	public static Object StringFromJson2(String jsondata, Type listType) {
		
		
		Gson gson = new Gson();
		Object list = gson.fromJson(jsondata, listType);
		return list;
	}

}
