/*
 * @(#)	2016年6月30日
 * Copyright (c) 2016 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author wutalk
 */
public class ReadFile {

    /**
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {

        // extractStartStopTime();
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String t = "2016-06-23T09:12:20.438";
        Date date = df.parse(t);
        System.out.println(date.getTime());
        System.out.println(df.parse("2016-06-23T12:16:54.580").getTime()
                - df.parse("2016-06-23T12:16:26.750").getTime());

//        timeMinus();
    }

    private static void extractStartStopTime() throws FileNotFoundException, IOException {
        String[] x = { "nbine3s", "nbisnmp", "nbi3gc", "nbi3gcpm", "nbiim", "fmascii",
                "pmFilemergerController" };
        Set<String> keeps = new HashSet<String>();
        keeps.addAll(Arrays.asList(x));
        System.out.println(keeps);

        String file = "D:\\userdata\\owu\\Desktop\\N17 upgrade down time\\start time raw data.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            for (String e : keeps) {
                if (line.contains(e)) {
                    System.out.println(line);
                    break;
                }
            }
        }
        br.close();
    }

    private static void timeMinus() throws FileNotFoundException, IOException, ParseException {
        String file = "D:\\userdata\\owu\\Desktop\\N17 upgrade down time\\nbi upgrade time raw data.txt";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        long previousTimeInMs = 0L;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            String time = "null";
            if (line.startsWith("2016")) {
                time = line.split(" ")[0];
                long now = df.parse(time).getTime();
                // System.out.println("between previous time ==>>: " + (now - previousTimeInMs));
                System.out.println(now - previousTimeInMs);
                previousTimeInMs = now;
            }
            System.out.println(line);
            System.out.println(time);
        }
        br.close();
    }

}
