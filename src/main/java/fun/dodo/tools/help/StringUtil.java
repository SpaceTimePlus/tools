package fun.dodo.tools.help;

import io.vertx.core.MultiMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class StringUtil {
    private StringUtil() {
    }

    public static boolean isNullOrEmpty(String value) {
        return null == value || value.trim().isEmpty() || Objects.equals("null", value.trim().toLowerCase()) || Objects.equals("undefined", value.trim().toLowerCase());
    }

    public static String expressMultiMap(MultiMap multiMap) {
        StringBuilder builder = new StringBuilder();
        Iterator var2 = multiMap.iterator();

        while (var2.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var2.next();
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }

        return builder.toString();
    }

    public static String expressHashMap(Map hashMap) {
        StringBuilder builder = new StringBuilder();
        Iterator var2 = hashMap.entrySet().iterator();

        while (var2.hasNext()) {
            Object item = var2.next();
            Map.Entry entry = (Map.Entry) item;
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }

        return builder.toString();
    }

}
