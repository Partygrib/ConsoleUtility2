package MyUtility;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LsTest {
    private final Ls main = new Ls();
    private final File[] result = main.info("C://Users//ACER//IdeaProjects//ConsoleUtility2//proverka");


    @Test
    void info() {
        File file1 = new File("C://Users//ACER//IdeaProjects//ConsoleUtility2//proverka//file1.txt");
        File file2 = new File("C://Users/ACER//IdeaProjects//ConsoleUtility2//proverka//file2.txt");
        File file3 = new File("C://Users//ACER//IdeaProjects//ConsoleUtility2//proverka//file3.txt");
        File[] result1 = new File[]{file1, file2, file3};
        assertArrayEquals(result1, result);
        assertArrayEquals(null, main.info("C://Users//ACER//IdeaProjects//ConsoleUtility2//pro"));
    }

    @Test
    void name() {
        assertEquals("Name: " + "file1.txt", main.name(result[0]));
        assertEquals("Name: " + "file3.txt", main.name(result[2]));
    }

    @Test
    void longer() {
        assertEquals("lastModifiedTime: Sat Apr 18 01:12:21 YEKT 2020\nSize: 218 bytes", main.longer(result[1]));
        assertEquals("lastModifiedTime: Sun Apr 19 21:10:10 YEKT 2020\nSize: 0 bytes", main.longer(result[2]));
    }

    @Test
    void human() {
        assertEquals("Size ~ 1 kilobytes\n" +
                "File can read\n" +
                "File can write\n" +
                "File can execute", main.human(result[1]));
        assertEquals("Size ~ 0 kilobytes\n" +
                "File can read\n" +
                "File can write\n" +
                "File can execute", main.human(result[2]));
    }
}