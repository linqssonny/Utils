package com.sonnyjack.utils.collection;

import java.util.Collection;
import java.util.Map;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class CollectionUtils {

    /**
     * return true when collection is empty,otherwise false
     *
     * @param collection, contain list、set
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        if (null == collection || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * return true when map is empty,otherwise false
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if (null == map || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * return true when objects is empty,otherwise false
     *
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object... objects) {
        if (null == objects || objects.length <= 0) {
            return true;
        }
        return false;
    }
}
