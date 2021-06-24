package com.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PlanAttrCheck {
    public static void main(String[] args) throws IOException {
        String fn = "C:\\work\\learngo\\utils\\planattr\\plan-attr-list.txt";
        BufferedReader br = new BufferedReader(new FileReader(fn));
        String line = null;
        int count = 0;
        Map<String, Set<String>> attrsMap = new HashMap<>();
        Set<String> neAttrs = null;
        Set<String> commonNeAttrs = new HashSet<>();
        String lastTitle = "";
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0) {
                continue;
            }
//            System.out.println(line);
            if (line.startsWith("//--")) {
//                System.out.println(line);
                if (neAttrs != null) {
                    attrsMap.put(lastTitle, neAttrs);
                }
                lastTitle = line.split("--")[1];
                neAttrs = new TreeSet<>();
                continue;
            }

            String[] items = line.split("\t");
            if (items.length != 2) {
                System.err.println(Arrays.toString(items));
            }
            neAttrs.add(String.format("%s,%s", items[0], items[1]));
//            if (count++ > 10) {
//                break;
//            }
        }
        System.out.println("*******************");
        Set<Map.Entry<String, Set<String>>> entries = attrsMap.entrySet();
        commonNeAttrs.addAll(attrsMap.get("DU"));
        for (Map.Entry<String, Set<String>> e : entries) {
//            System.out.println();
//            System.out.println("-------------" + e.getKey());
            commonNeAttrs.retainAll(e.getValue());
//            for (String attr : e.getValue()) {
//                System.out.println(attr);
//            }
        }

//        neAttrs.retainAll(null);
        System.out.println();
        System.out.println("========common Mandatory============");
        Set<String> mand = commonNeAttrs.stream().filter(s -> s.endsWith("Mandatory")).collect(Collectors.toSet());
        Set<String> mandatory = new TreeSet<>();
        mandatory.addAll(mand);
        for (String s : mandatory) {
            System.out.println(s);
        }


        System.out.println();
        System.out.println("========common Optional============");
        Set<String> commonOptional = new TreeSet<>();
        commonOptional.addAll(commonNeAttrs);
        commonOptional.removeAll(mand);
        for (String s : commonOptional) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        for (Map.Entry<String, Set<String>> e : entries) {
            System.out.println();
            System.out.println("-------------" + e.getKey());
            Set<String> attrs = e.getValue();
            attrs.removeAll(commonNeAttrs);
            for (String attr : attrs) {
                System.out.println(attr);
            }
        }

        br.close();
    }
}
