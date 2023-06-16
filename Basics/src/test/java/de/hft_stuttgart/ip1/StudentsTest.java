package de.hft_stuttgart.ip1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({TestResultLoggerExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsTest {

    @Test
    void getIndex() {
    }

    @Test
    void getPort() {
        assertEquals(28699, Students.getPort(" "), "Wrong port for nonexistent entry");
        assertEquals(28700, Students.getPort(Students.NAMES[0]), "Wrong port for first entry");
        assertEquals(28711, Students.getPort(Students.NAMES[11]), "Wrong port for first sample entry");
        assertEquals(28712, Students.getPort(Students.NAMES[12]), "Wrong port for secnd sample entry");
        assertEquals(28765, Students.getPort("heusch"), "Wrong port for last entry");
        for (int i = 0; i < Students.NAMES.length; i += 3) {
            String name1 = Students.NAMES[i];
            String name2 = Students.NAMES[i + 1];
            String name3 = Students.NAMES[i + 2];
            System.out.printf("|%s|%d|%s|%d|%s|%d|%n",
                    name1, Students.getPort(name1),
                    name2, Students.getPort(name2),
                    name3, Students.getPort(name3));

        }
    }

    @Test
    public void testVoid() {
        for (var name : Students.NAMES) {
            int i = 0;
            for (var c : name.toCharArray()) {
                i += c;
            }
            System.out.printf("%s: %d", name, i % 3);
        }
    }
}

