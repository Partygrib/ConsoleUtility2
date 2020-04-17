package MyUtility;

import java.io.*;

public class Ls {

    public boolean rev;

    public boolean lon;

    public String out;

    public Ls(boolean rev, boolean lon) {
        this.rev = rev;
        this.lon = lon;
    }

    public File[] respect(String dir) {
        File f = new File(dir);
        if(f.isDirectory()) {
            return f.listFiles();
        }
        else return null;
    }
}