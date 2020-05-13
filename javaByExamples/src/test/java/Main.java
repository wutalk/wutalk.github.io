import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanIn = null;
        try {
            scanIn = new Scanner(System.in);
            // scanIn.nextInt();
            int lc = 0;
            while (scanIn.hasNextLine()) {
                String line = scanIn.nextLine().trim();
                System.out.printf("line#%d: [%s]\n", ++lc, line);
                String[] items = line.split(" ");
                if (items.length == 2) {
                    int personNumber = Integer.parseInt(items[0]);
                    int caseNumber = Integer.parseInt(items[1]);

                    List<String[]> cases = new ArrayList<>();
                    for (int i = 0; i < caseNumber; i++) {
                        line = scanIn.nextLine().trim();
                        System.out.printf("line#%d: [%s]\n", ++lc, line);
                        items = line.split(" ");
                        if (!"0".equals(items[2].trim())) {
                            cases.add(new String[]{items[0].trim(), items[1].trim()});
                        }
                    }
                }
            }
        } finally {
            if (scanIn != null) {
                scanIn.close();
            }
        }
    }


}