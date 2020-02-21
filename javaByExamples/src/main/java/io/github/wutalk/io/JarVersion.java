package io.github.wutalk.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JarVersion {
    public static void main(String[] args) throws IOException {
        String file = "C:\\Temp\\jar_list.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<String, String> jarVer = new TreeMap<String, String>();
        String ln = "";
        while ((ln = br.readLine()) != null) {
            if (
                    ln.contains("nsn") ||
                            ln.contains("nokia") ||
                            ln.contains("20.0.0") ||
                            ln.contains("19.6.0.17") ||
                            ln.contains("19.2.0.142")||
                            ln.contains("19.1.0")

            ) {
                continue;
            }
            if (ln.startsWith("#")) {
                jarVer.put(ln, ln);
                continue;
            }
            int sep = ln.lastIndexOf("-");
            if (sep < 0) {
                System.out.println(ln);
                continue;
            }
            String name = ln.substring(0, sep);
            String ver = ln.substring(sep + 1);
//            System.out.printf("%s\t%s\n", name, ver);
            if (jarVer.keySet().contains(name)) {
                if (!jarVer.get(name).equals(ver)) {
                    ver = jarVer.get(name) + "; " + ver;
                }
            }
            jarVer.put(name, ver);
        }
        br.close();
        Set<Map.Entry<String, String>> entries = jarVer.entrySet();
        for (Map.Entry<String, String> e : entries) {
            System.out.printf("%s,%s\n", e.getKey(), e.getValue());
        }
    }
}
