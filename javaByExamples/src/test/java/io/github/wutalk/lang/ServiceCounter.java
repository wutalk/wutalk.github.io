package io.github.wutalk.lang;

import joptsimple.internal.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceCounter {
    public static void main(String[] args) throws IOException {
        String file = "C:\\5CG5432W2K-Data\\owu\\Desktop\\springboot\\na.service.name.log";
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<String, List<String>> services = new HashMap<>();
        Set<String> names = new HashSet<>();
        String line = null;
        while ((line = br.readLine()) != null) {
            line = StringUtils.trim(line);
            if (StringUtils.isEmpty(line)) {
                continue;
            }
            String[] items = line.split("-clab1195", 2);
            if (services.get(items[0]) == null) {
                services.put(items[0], new ArrayList<>());
            }
            if (items.length == 2) {
                services.get(items[0]).add(items[1]);
            }
            names.add(items[0]);
        }
        br.close();

        System.out.println(services.keySet().size());
        System.out.println(names.size());
        for (Map.Entry<String, List<String>> e : services.entrySet()) {
            String node = "";
            if (e.getValue().size() > 0) {
//                node = ": " + Strings.join(e.getValue(), ",");
                continue;
            }
            System.out.printf("%s%s\n", e.getKey(), node);
        }

        Map<String, List<String>> multiNodeServices = services.entrySet().stream().filter(map -> map.getValue().size() > 0)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        for (Map.Entry<String, List<String>> e : multiNodeServices.entrySet()) {
            String node = "";
            if (e.getValue().size() > 0) {
                node = ": " + Strings.join(e.getValue(), ",");
            }
            System.out.printf("%s%s\n", e.getKey(), node);
        }
    }
}
