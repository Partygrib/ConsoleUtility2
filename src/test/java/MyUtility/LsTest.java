package MyUtility;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LsTest {

    private final File[] result = Ls.info(new File("src//test//resources"));

    @Test
    void info() {
        File empty = new File("src//test//resources//empty");
        File full = new File("src//test//resources//full");
        File file1 = new File("src//test//resources//file1.txt");
        File file2 = new File("src//test//resources//file2.txt");
        File file3 = new File("src//test//resources//file3.txt");
        File[] result1 = new File[]{empty, file1, file2, file3, full};
        assertArrayEquals(result1, result);
        assertArrayEquals(new File[]{}, Ls.info(empty));
        assertArrayEquals(null, Ls.info(new File("src//test//er")));
    }

    @Test
    void name() {
        assertEquals("Name: " + "empty", Ls.name(result[0]));
        assertEquals("Name: " + "file2.txt", Ls.name(result[2]));
        assertEquals("Name: " + "full", Ls.name(result[4]));
    }

    @Test
    void longer() {
        assertEquals("lastModifiedTime: Fri May 22 13:21:44 YEKT 2020" + System.lineSeparator()
                + "Size: 0 bytes" + System.lineSeparator() + "111", Ls.longer(result[0], false));
        assertEquals("lastModifiedTime: Sun Apr 19 20:59:33 YEKT 2020" + System.lineSeparator()
                + "Size: 431 bytes" + System.lineSeparator() + "111", Ls.longer(result[1], false));
        assertEquals("lastModifiedTime: Sat Apr 18 01:12:21 YEKT 2020" + System.lineSeparator()
                + "Size: 218 bytes" + System.lineSeparator() + "111", Ls.longer(result[2], false));
        assertEquals("lastModifiedTime: Sat May 23 15:20:11 YEKT 2020" + System.lineSeparator()
                + "Size: 4440 bytes" + System.lineSeparator() + "111", Ls.longer(result[4], false));
    }

    @Test
    void human() {
        assertEquals("Size ~ 0 kilobytes" + System.lineSeparator() +
                "rwx", Ls.human(result[0]));
        assertEquals("Size ~ 1 kilobytes" + System.lineSeparator() +
                "rwx", Ls.human(result[1]));
        assertEquals("Size ~ 4 kilobytes" + System.lineSeparator() +
                "rwx", Ls.human(result[4]));
    }
}