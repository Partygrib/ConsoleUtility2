package MyUtility;

import java.io.*;
import java.util.Date;

public class Ls {

    public static File[] info(File f) {
        if (f.isDirectory()) {
            return f.listFiles();
        } else {
            if (f.exists()) return new File[]{f};
            return null;
        }
    }

    public static String name(File file) { return "Name: " + file.getName(); }

    public static String longer(File file, boolean mod) {
        Date last = new Date(file.lastModified());
        if (mod) return "lastModifiedTime: " + last;
        else return "lastModifiedTime: " + last + System.lineSeparator()
                + "Size: " + size(file) + " bytes"
                + System.lineSeparator() + root(file, "1", "1", "1", "0");
    }

    public static String root(File file, String r, String w, String x, String z) {
        String str = "";
        if (file.canRead()) str = str.concat(r);
        else str = str.concat(z);
        if (file.canWrite()) str = str.concat(w);
        else str = str.concat(z);
        if (file.canExecute()) str = str.concat(x);
        else str = str.concat(z);
        return str;
    }

    public static long size(File file) {
        if (file.isDirectory()) {
            long l = 0;
            File[] files = file.listFiles();
            for (File value : files) {
                if (value.isFile())
                    l += value.length();
                else l += size(value);
            }
            return l;
        }
        else return file.length();
    }

    public static String human(File file) {
        int k = 0;
        int n = 1024;
        String p = "";
        long r = size(file);
        if (r < n && r!= 0) r = 1;
        while (r >= n) {
            r /= n;
            k++;
        }
        switch (k) {
            case 0:
            case 1:
                p = " kilo";
                break;
            case 2:
                p = " mega";
                break;
            case 3:
                p = " giga";
                break;
        }
        return "Size ~ " + r + p + "bytes" + System.lineSeparator()
                + root(file, "r", "w", "x", "-");
    }
}