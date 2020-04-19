package MyUtility;

import java.io.*;
import java.util.Date;

public class Ls {

    public File[] info(String dir) {
        File file = new File(dir);
        if(file.isDirectory()) {
            return file.listFiles();
        }
        else {
            try {
                if (file.exists()) return new File[]{file};
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
        return "lastModifiedTime: " + last + "\n" + "Size: " + file.length() + " bytes";
    }

    public String human(File file) {
        String str1 = "";
        String str2;
        long r = file.length();
        if (r > 1024 * 1024 * 1024) {
            r = r / (1024 * 1024 * 1024);
            str2 = "Size ~ " + r + " gigabytes" + "\n";
        }
        else {
            if (r > 1024 * 1024) {
                r = r / (1024 * 1024);
                str2 = "Size ~ " + r + " megabytes" + "\n";
            }
            else {
                r = r / 1024;
                if (r == 0) r = 1;
                if (file.length() == 0) r = 0;
                str2 = "Size ~ " + r + " kilobytes" + "\n";
            }
        }
        str1 = str1.concat(str2);
        if (file.canRead()) str1 = str1.concat("File can read" + "\n");
        else str1 = str1.concat("File can not read" + "\n");
        if (file.canWrite()) str1 = str1.concat("File can write" + "\n");
        else str1 = str1.concat("File can not write" + "\n");
        if (file.canExecute()) str1 = str1.concat("File can execute");
        else str1 = str1.concat("File can not execute");
        return str1;
    }
}