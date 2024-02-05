package com.vms.server.util;

public class StringCaseConverter {
    public static String toSnakeCase(String input) {
        // 카멜 케이스를 스네이크 케이스로 변환하는 로직
        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(input.charAt(0)));

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String toCamelCase(String input) {
        // 스네이크 케이스를 카멜 케이스로 변환하는 로직
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '_') {
                nextUpper = true;
            } else if (nextUpper) {
                result.append(Character.toUpperCase(c));
                nextUpper = false;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
