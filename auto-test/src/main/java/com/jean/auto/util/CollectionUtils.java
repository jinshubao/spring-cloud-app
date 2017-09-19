package com.jean.auto.util;

public class CollectionUtils {

    /**
     * Concatenates the <code>toString()</code> representation of each
     * item in this Iterable, with the given String as a separator between each item.
     *
     * @param self      an Iterable of objects
     * @param separator a String separator
     * @return the joined String
     * @since 1.0
     */
    public static String join(Iterable self, String separator) {
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        if (separator == null) separator = "";
        for (Object value : self) {
            if (first) {
                first = false;
            } else {
                buffer.append(separator);
            }
            buffer.append(value);
        }
        return buffer.toString();
    }

    /**
     * Concatenates the <code>toString()</code> representation of each
     * items in this array, with the given String as a separator between each
     * item.
     *
     * @param self      an array of Object
     * @param separator a String separator
     * @return the joined String
     * @since 1.0
     */
    public static String join(Object[] self, String separator) {
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        if (separator == null) separator = "";
        for (Object next : self) {
            if (first) {
                first = false;
            } else {
                buffer.append(separator);
            }
            buffer.append(next);
        }
        return buffer.toString();
    }
}
