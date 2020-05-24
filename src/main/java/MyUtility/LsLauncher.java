package MyUtility;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

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

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Ls.jar [-l] [-h] [-r] [-o output.file] directory_or_file");
            parser.printUsage(System.err);
            return;
        }

        if (Ls.info(f).length != 0) {
            File[] result = Ls.info(f);
            if (rev) Collections.reverse(Arrays.asList(result));
            if (out != null) {
                try (FileWriter writer = new FileWriter(out)) {
                    for (File file : result) {
                        writer.write(Ls.name(file) + System.lineSeparator());
                        if (lon) writer.write(Ls.longer(file, hum) + System.lineSeparator());
                        if (hum) writer.write(Ls.human(file) + System.lineSeparator());
                    }
                }
            } else {
                for (File file : result) {
                    System.out.println(Ls.name(file));
                    if (lon) System.out.println(Ls.longer(file, hum));
                    if (hum) System.out.println(Ls.human(file));
                }
            }
        }
        else System.out.println("Директория пуста!");
    }
}
