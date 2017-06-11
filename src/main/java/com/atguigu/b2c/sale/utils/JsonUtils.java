package com.atguigu.b2c.sale.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	public static <T> String listToJson(List<T> shopcars) {
		JSONArray array = JSONArray.fromObject(shopcars);
		String json = null;
		try {
			json = URLEncoder.encode(array.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	public static <T> List<T> jsonToList(String json,Class<T> t){
		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<T> list = (List<T>)jsonArray.toCollection(jsonArray, t);
		return list;
	}
	public static String objToJson(Object obj) {
		JSONObject object = JSONObject.fromObject(obj);
		String json = null;
		try {
			json = URLEncoder.encode(object.toString(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	public static <T> T jsonToObj(String json,Class<T> t) {
		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(json);
		T bean = (T)jsonObject.toBean(jsonObject, t);
		return bean;
	}
}
