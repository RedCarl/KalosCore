import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class main {
    static List<String> a = new ArrayList<>();
    public static void main(String[] args) {
        List<String> b = new ArrayList<>();
        List<String> c = new ArrayList<>();
        List<String> d = new ArrayList<>();
        b.add("a");
        c.add("b");
        d.add("c");


        a.addAll(b);
        a.addAll(c);
        a.addAll(d);
        a.add("d");

        for (String a:a
             ) {
            System.out.println(a);
        }
    }
}
