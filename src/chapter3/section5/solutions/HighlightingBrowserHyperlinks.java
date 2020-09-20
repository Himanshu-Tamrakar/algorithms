package chapter3.section5.solutions;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class HighlightingBrowserHyperlinks {
    private HashMap<String, Date> st;
    public HighlightingBrowserHyperlinks() {
        st = new HashMap<>();
    }

    /**
     * put
     * @param key
     * @param lastVisited
     */
    public void put(String key, Date lastVisited) {
        st.put(key, lastVisited);
    }

    /**
     * get
     * @param key
     * @return
     */
    public Date get(String key) {
        return st.get(key);
    }

    public Iterator<String> keys() {
        return st.keySet().iterator();
    }

    /**
     * isVisitedInLastDDays
     * @param key
     * @param days
     * @return
     */
    public boolean isVisitedInLastDDays(String key, int days) {
        Date date = get(key);
        Date now = new Date();
        long mills = Math.abs(now.getTime() - date.getTime());
        long diff = TimeUnit.DAYS.convert(mills,TimeUnit.MILLISECONDS);
        return diff <= days;
    }


    public static void main(String[] args) {
        Scanner scanner = null;
        String[] urls = null;
        try {
            scanner = new Scanner(new File("src/chapter3/section5/data/craft-popular-urls.txt"));
            urls = Pattern.compile("\\p{javaWhitespace}+").split(scanner.useDelimiter("\\A").next());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HighlightingBrowserHyperlinks highting = new HighlightingBrowserHyperlinks();

        for (int i = 0; i < urls.length; i++) {
            int day = (int) Math.abs((Math.random() * 100) % 30);
            int month =  (int) Math.abs((Math.random() * 100) % 10);
            int year = 2020;
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-" + month + "-" + day);
                highting.put(urls[i], date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        for (Iterator<String> it = highting.keys(); it.hasNext(); ) {
            String url = it.next();
            System.out.printf("key: %s, value: %s, is visited in last 30 days %b\n", url, String.valueOf(highting.get(url)), highting.isVisitedInLastDDays(url, 30));

        }
    }
}
