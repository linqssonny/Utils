package com.sonnyjack.utils.json;

import android.text.TextUtils;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class JsonUtils {

    /**
     * build String by keys and values
     *
     * @param values mean：key1，value1，key2，value2...
     * @return
     */
    public static String parseStrByArray(Object... values) {
        JSONStringer jsonStringer = parseJSONStrByArray(values);
        if (null == jsonStringer) {
            return "";
        }
        return jsonStringer.toString();
    }

    /**
     * build JSONStringer by keys and values
     *
     * @param values mean：key1，value1，key2，value2...
     * @return
     */
    public static JSONStringer parseJSONStrByArray(Object... values) {
        if (null == values || values.length <= 0) {
            return null;
        }
        int size = values.length;
        if (size % 2 != 0) {
            values = Arrays.copyOf(values, size + 1);
        }
        JSONStringer jsonStringer = new JSONStringer();
        try {
            jsonStringer.object();
            for (int i = 0; i < values.length - 1; i += 2) {
                if (null == values[i] || values[i].toString().trim().length() <= 0) {
                    //key为空时  不添加
                    continue;
                }
                jsonStringer.key(values[i].toString()).value((null == values[i + 1] || "null".equals(values[i + 1].toString())) ? "" : values[i + 1]);
            }
            jsonStringer.endObject();
        } catch (Exception e) {
        }
        return jsonStringer;
    }

    /**
     * build String by Map
     *
     * @param param
     * @return
     */
    public static String parseStrByMap(Map<String, Object> param) {
        if (null == param || param.size() <= 0) {
            return "";
        }
        Object[] objects = new Object[param.size() * 2];
        int position = 0;
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (null == entry) {
                throw new NullPointerException("the map have item is null");
            }
            objects[2 * position] = entry.getKey();
            objects[2 * position + 1] = entry.getValue();
            position++;
        }
        return parseStrByArray(objects);
    }

    /**
     * return value by key
     *
     * @param value
     * @param key
     * @return
     */
    public static String optString(String value, String key) {
        Map<String, String> result = optJson(value, key);
        if (null != result && result.size() > 0) {
            return result.get(key);
        }
        return null;
    }

    /**
     * return map that contain keys and values by keys
     *
     * @param value
     * @param keys
     * @return
     */
    public static Map<String, String> optJson(String value, String... keys) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = toJSONObject(value);
        if (null != jsonObject && null != keys && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                map.put(keys[i], jsonObject.optString(keys[i]));
            }
        }
        return map;
    }

    /**
     * string convert JSONObject
     *
     * @param value
     * @return
     */
    public static JSONObject toJSONObject(String value) {
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        if ("null".equals(value)) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(value);
            return jsonObject;
        } catch (Exception e) {
        }
        return null;
    }
}
