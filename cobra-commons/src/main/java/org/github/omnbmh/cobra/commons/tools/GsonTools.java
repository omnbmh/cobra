

package org.github.omnbmh.cobra.commons.tools;

import org.github.omnbmh.cobra.commons.utils.StringUtils;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;


public class GsonTools {
    public static String toJsonString(Object object) {
        return new Gson().toJson(object);
    }

    public static JsonObject toJson(String json) {
        return (JsonObject) new JsonParser().parse(json);
    }

    public static JsonObject toJsonObject(Object javaObj) {
        return new Gson().toJsonTree(javaObj).getAsJsonObject();
    }

    public static JsonObject toJsonObject(String jsonStr) {
        return new JsonParser().parse(jsonStr).getAsJsonObject();
    }

    public static <T> T toJavaObject(JsonObject jsonObj, Class<T> classOfT) {
        return new Gson().fromJson(jsonObj, classOfT);
    }

    public static <T> T toJavaObject(String jsonStr, Class<T> classOfT) {
        return new Gson().fromJson(jsonStr, classOfT);
    }

    public static String getJsonStringFromObject(Object javaObj) {
        Gson gson = new Gson();
        return gson.toJson(javaObj);
    }

    public static String toJsonStringWithNull(Object javaObj) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(javaObj);
    }

    public static JsonObject toJsonObjectWithNull(Object javaObj) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJsonTree(javaObj).getAsJsonObject();
    }

    public static String getJsonStringFromObjectNo(Object javaObj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String s = gson.toJson(javaObj);
        s.replaceAll("\"", "");
        return gson.toJson(javaObj);
    }

    public static String getRemoveQuotesJsonStringFromObject(Object javaObj) {
        Gson gson = new Gson();
        return gson.toJson(javaObj) == null ? gson.toJson(javaObj)
                : gson.toJson(javaObj).toString().replaceAll("\"", "").trim();
    }

    /**
     * Created With IntelliJ IDEA CE
     * translate 实现两个实体类的互转
     * <p>More Info!</p>
     *
     * @param classOfT
     * @return T
     * @date 2017/5/3
     * @time 下午6:30 </PRE>
     * @since 1.7
     * <p>
     * <PRE>
     */
    public static <T> T translate(Object obj1, Class<T> classOfT) {
        // 源转JsonObject
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj1);
        return new Gson().fromJson(jsonStr, classOfT);
    }

    public static String getAsString(JsonObject jsonObject, String property) {
        if (null == jsonObject || StringUtils.isEmpty(property)) {
            return "";
        }
        if (!jsonObject.has(property)) {
            return "";
        }
        if (jsonObject.get(property).isJsonNull()) {
            return "";
        }
        return jsonObject.get(property).getAsString();
    }

    public static JsonArray getAsJsonArray(JsonObject jsonObject, String property) {
        return jsonObject.getAsJsonArray(property);
    }

    public static int getAsInt(JsonObject jsonObject, String property) {
        return jsonObject.get(property).getAsInt();
    }

    public static long getAsLong(JsonObject jsonObject, String property) {
        return jsonObject.get(property).getAsLong();
    }

    public static void sort(JsonElement e) {
        if (e.isJsonNull()) {
            return;
        }

        if (e.isJsonPrimitive()) {
            return;
        }

        if (e.isJsonArray()) {
            JsonArray a = e.getAsJsonArray();
            for (Iterator<JsonElement> it = a.iterator(); it.hasNext(); ) {
                sort(it.next());
            }
            return;
        }

        if (e.isJsonObject()) {
            Map<String, JsonElement> tm = new TreeMap<String, JsonElement>(getComparator());
            for (Map.Entry<String, JsonElement> en : e.getAsJsonObject().entrySet()) {
                tm.put(en.getKey(), en.getValue());
            }

            for (Map.Entry<String, JsonElement> en : tm.entrySet()) {
                e.getAsJsonObject().remove(en.getKey());
                e.getAsJsonObject().add(en.getKey(), en.getValue());
                sort(en.getValue());
            }
            return;
        }
    }

    public static Map<String, String> toMap(Object obj) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> map = gson.fromJson(toJsonStringWithNull(obj), type);
        return map;
    }

    private static Comparator<String> getComparator() {
        Comparator<String> c = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        return c;
    }

    public static boolean isBadJson(String json) {
        return !isGoodJson(json);
    }

    public static boolean isGoodJson(String json) {
        if (null == json || json.length() == 0) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            System.out.println("bad json: " + json);
            return false;
        }
    }

    /**
     * 日期格式化
     */
    private static String dateFormat(Date date) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(date);
    }

}
