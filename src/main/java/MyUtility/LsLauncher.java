package MyUtility;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.util.Arrays;

public class LsLauncher {

    @Option(name = "-l", usage = "long")
    private boolean lon;

    @Option(name = "-r", usage = "reverse")
    private boolean rev;

    @Option(name="-o",usage="output")
    private String out;

    @Argument(required = true,metaVar = "InputName", usage = "Input dir")
    public String dir;

    public static void main(String[] args) {
        new LsLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Ls.jar dir");
            parser.printUsage(System.err);
            return;
        }

        Ls ls = new Ls(rev, lon);
        File[] result = ls.respect(dir);
        if (out != null) {
            try{
                FileWriter writer = new FileWriter(out);
                if(rev) {
                    for (int i = result.length - 1; i >= 0; i--) {
                        writer.write(result[i].getName()+ "\n");
                        if (lon) {
                            writer.write("lastModifiedTime: " + result[i].lastModified() + "\n");
                        }
                    }
                }
                else for (File file : result) {
                    writer.write(file.getName() + "\n");
                    if (lon) {
                        writer.write("lastModifiedTime: " + file.lastModified() + "\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            if(rev) {
                for (int i = result.length - 1;i >= 0;i--) {
                    System.out.println(result[i].getName());
                    if (lon) {
                        System.out.println("lastModifiedTime: " + result[i].lastModified() + "\n");
                    }
                }
            }
            else for (File file : result) {
                System.out.println(file.getName());
                if (lon) {
                    System.out.println("lastModifiedTime: " + file.lastModified() + "\n");
                }
            }
        }
    }
}
