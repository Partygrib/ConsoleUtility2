package MyUtility;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LsTest {
    private final Ls main = new Ls();

    private final File[] result = main.info(new File("src//test//resources"));

    @Test
    void info() {
        File file1 = new File("src//test//resources//file1.txt");
        File file2 = new File("src//test//resources//file2.txt");
        File file3 = new File("src//test//resources//file3.txt");
        File[] result1 = new File[]{file1, file2, file3};
        assertArrayEquals(result1, result);
        assertArrayEquals(null, main.info(new File("src//test//er")));
    }

    @Test
    void name() {
        assertEquals("Name: " + "file1.txt", main.name(result[0]));
        assertEquals("Name: " + "file3.txt", main.name(result[2]));
    }

    @Test
    void longer() {
        assertEquals("lastModifiedTime: Sat Apr 18 01:12:21 YEKT 2020" + System.lineSeparator()
                + "Size: 218 bytes", main.longer(result[1]));
        assertEquals("lastModifiedTime: Sun Apr 19 21:10:10 YEKT 2020" + System.lineSeparator()
                + "Size: 0 bytes", main.longer(result[2]));
    }

    @Test
    void human() {
        assertEquals("Size ~ 1 kilobytes" + System.lineSeparator() +
                "File can read" + System.lineSeparator() +
                "File can write" + System.lineSeparator() +
                "File can execute", main.human(result[1]));
        assertEquals("Size ~ 0 kilobytes" + System.lineSeparator() +
                "File can read" + System.lineSeparator() +
                "File can write" + System.lineSeparator() +
                "File can execute", main.human(result[2]));
    }
}