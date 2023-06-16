package de.hft_stuttgart.ip1;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {
    private int points = 0;
    private PrintStream outfile;

    public TestResultLoggerExtension() {
        try {
            File target = new File("target");
            File dot = new File(".");
            File file;
            String fileName = System.getProperty("REPORT_FILE", "report-file");
            if ( target.exists() && target.isDirectory() ) {
                file = new File(target, fileName);
            }
            else {
                file = new File(dot, fileName);
            }
            outfile = new PrintStream(new FileOutputStream(file, true));
        }
        catch(IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    public void afterAll(ExtensionContext extensionContext) {
        String student = System.getProperty("STUDENT");
        System.out.printf("AfterAll: %s %s %s %n", student, extensionContext.getDisplayName(), points);
        outfile.printf("AfterAll: %s %s %s %n", student, extensionContext.getDisplayName(), points);
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        output("Disabled", extensionContext);
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        output("Successful", extensionContext);
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        output("Aborted", extensionContext);
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        output("Failed", extensionContext);
    }

    @TestValue(22)
    @Order(1)
    public void output(String header, ExtensionContext extensionContext) {
        Method meth = extensionContext.getTestMethod().get();
        TestValue testValue = meth.getAnnotation(TestValue.class);
        if ( testValue != null && header.equals("Successful") ) {
            points = points+testValue.value();
        }
        if ( testValue == null ) {
            System.out.printf("%s: %s 0 %n", header, extensionContext.getDisplayName());
            outfile.printf("%s: %s 0 %n", header, extensionContext.getDisplayName());
        }
        else {
            System.out.printf("%s: %s %d %n", header, extensionContext.getDisplayName(), testValue.value());
            outfile.printf("%s: %s %d %n", header, extensionContext.getDisplayName(), testValue.value());
        }
    }
}
