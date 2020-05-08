package MyUtility;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LsLauncher {

    @Option(name = "-h", usage = "human-readable")
    private boolean hum;

    @Option(name = "-l", usage = "long")
    private boolean lon;

    @Option(name = "-r", usage = "reverse")
    private boolean rev;

    @Option(name="-o",usage="output")
    private File out;

    @Argument(required = true,metaVar = "InputName", usage = "Input dir")
    private File f;

    public static void main(String[] args) throws IOException {
        new LsLauncher().launch(args);
    }

    public void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Ls.jar [-l] [-h] [-r] [-o output.file] directory_or_file");
            parser.printUsage(System.err);
            return;
        }

        Ls ls = new Ls();
        File[] result = ls.info(f);
        if (out != null) {
            try (FileWriter writer = new FileWriter(out)) {
                if(rev) {
                    for (int i = result.length - 1; i >= 0; i--) {
                        writer.write(ls.name(result[i]) + System.lineSeparator());
                        if (lon) writer.write(ls.longer(result[i]) + System.lineSeparator());
                        if (hum) writer.write(ls.human(result[i]) + System.lineSeparator());
                    }
                }
                else for (File file : result) {
                    writer.write(ls.name(file) + System.lineSeparator());
                    if (lon) writer.write(ls.longer(file) + System.lineSeparator());
                    if (hum) writer.write(ls.human(file) + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException();
            }
        }
        else {
            if(rev) {
                for (int i = result.length - 1;i >= 0;i--) {
                    System.out.println(ls.name(result[i]));
                    if (lon) System.out.println(ls.longer(result[i]));
                    if (hum) System.out.println(ls.human(result[i]));
                }
            }
            else for (File file : result) {
                System.out.println(ls.name(file));
                if (lon) System.out.println(ls.longer(file));
                if (hum) System.out.println(ls.human(file));
            }
        }
    }
}
