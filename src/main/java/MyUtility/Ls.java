package MyUtility;

import java.io.*;
import java.util.Date;

public class Ls {

    public File[] info(File f) {
        if(f.isDirectory()) {
            return f.listFiles();
        }
        else {
            try {
                if (f.exists()) return new File[]{f};
                else throw new FileNotFoundException("Такого файла/директории не существует!");
            } catch (FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public String name(File file) { return "Name: " + file.getName(); }

    public String longer(File file) {
        Date last = new Date(file.lastModified());
        return "lastModifiedTime: " + last + System.lineSeparator()
                + "Size: " + file.length() + " bytes";
    }

    public String human(File file) {
        String str1 = "";
        String str2;
        long r = file.length();
        if (r > 1024 * 1024 * 1024) {
            r = r / (1024 * 1024 * 1024);
            str2 = "Size ~ " + r + " gigabytes" + System.lineSeparator();
        }
        else {
            if (r > 1024 * 1024) {
                r = r / (1024 * 1024);
                str2 = "Size ~ " + r + " megabytes" + System.lineSeparator();
            }
            else {
                r = r / 1024;
                if (r == 0) r = 1;
                if (file.length() == 0) r = 0;
                str2 = "Size ~ " + r + " kilobytes" + System.lineSeparator();
            }
        }
        str1 = str1.concat(str2);
        if (file.canRead()) str1 = str1.concat("File can read" + System.lineSeparator());
        else str1 = str1.concat("File can not read" + System.lineSeparator());
        if (file.canWrite()) str1 = str1.concat("File can write" + System.lineSeparator());
        else str1 = str1.concat("File can not write" + System.lineSeparator());
        if (file.canExecute()) str1 = str1.concat("File can execute");
        else str1 = str1.concat("File can not execute");
        return str1;
    }
}