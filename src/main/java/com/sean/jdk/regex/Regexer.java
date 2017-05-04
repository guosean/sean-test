package com.sean.jdk.regex;

import java.util.regex.Pattern;


public class Regexer {

    public static final String PATTERN_IP = "(?<![0-9])(?:(?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5]))(?![0-9])";

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile(PATTERN_IP);
        String ip = "190.168.0.111";
        System.out.println(pattern.matcher(ip).matches());

    }

}
