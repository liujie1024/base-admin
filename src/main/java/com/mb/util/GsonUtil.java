package com.mb.util;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * GSON，对象和json互换
 */
public class GsonUtil {

	/**
	 * 将Json数据解析成相应的映射对象
	 * 
	 * @param jsonData
	 * @param type
	 * @return
	 */
	public static <T> T parseJsonWithGson(String jsonData, Class<T> cls) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, cls);
		return result;
	}

	

	/**
	 *  将Json数组解析成相应的映射对象列表
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> ArrayList<T> fromJsonList(String jsonData, Class<T> cls) {
		Gson gson = new Gson();
		ArrayList<T> mList = new ArrayList<T>();
		JsonArray array = new JsonParser().parse(jsonData).getAsJsonArray();
		for (final JsonElement elem : array) {
			mList.add(gson.fromJson(elem, cls));
		}
		return mList;
	}

}
