package de.hft_stuttgart.ip1;

import com.sun.tools.attach.VirtualMachine;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class TestHelpers {
    private static class ClassWriterHelper extends Writer {
        List<String> output = new ArrayList<>();
        public String[] getOutput() {
            return output.toArray(new String[output.size()]);
        }

        @Override
        public void write(char[] chars, int from, int to) throws IOException {
            String value = new String(chars, from, to);
            System.err.println(value);
            if ( value.matches("\\s*NEW\\s*.*\\n") ) {
                value = value.replaceFirst("\\s*NEW\\s*", "");
                output.add(value.replace("\n", ""));
            }
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() {
        }
    }

    static String [] listClasses(Class clasz) throws IOException {
        ClassReader classReader = new ClassReader(clasz.getName());
        ClassWriterHelper writerHelper = new ClassWriterHelper();
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(writerHelper));
        classReader.accept(traceClassVisitor,  ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        return writerHelper.getOutput();
    }

    static int[] makeData(int length, int[] data) {
        int[] result = new int[length];
        for (int i = 0; i < data.length; i+=2) {
            result[data[i]] = data[i+1];
        }
        return result;
    }

    static void expectException(Class<? extends Throwable> clazz, boolean hasArgument, String argument, Callable callable) {
        String message = null;
        try {
            callable.call();
        } catch ( Throwable t ) {
            if ( clazz.isInstance(t) ) {
                if ( !hasArgument || Objects.equals(argument, t.getMessage()) ) {
                    return;
                }
                message = String.format("Found message %s, expected %s",
                        t.getMessage(), argument);
            }
            else {
                message = String.format("Found instance of %s, expected %s",
                        t.getClass().getName(), clazz.getName());
            }
        }
        if (message == null) {
            message = String.format("Found no instance, expected %s",
                    clazz.getName());
        }
        try {
            Class<? extends Error> errClass = (Class<? extends Error>) Class.forName("org.opentest4j.AssertionFailedError");
            Constructor<? extends Error> constructor = errClass.getConstructor(String.class);
            Error error = constructor.newInstance(message);
            error.fillInStackTrace();
            StackTraceElement []ste = error.getStackTrace();
            error.setStackTrace(Arrays.copyOfRange(ste, 1, ste.length));
            throw error;
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | NoSuchMethodException | InvocationTargetException ex) {
            throw new Error(ex);
        }
    }

    public static void expectException(Class<? extends Throwable> clazz, String message, Runnable runnable) {
        expectException(clazz, true, message, ()->{ runnable.run(); return null; });
    }

    public static void expectException(Class<? extends Throwable> clazz, String message, Callable<Void> callable) {
        expectException(clazz, true, message, ()->{ callable.call(); return null; });
    }

    public static long measureRuntime(Callable<Void> callable) {
        long then = System.currentTimeMillis();
        try {
            callable.call();
        }
        catch(Throwable t) {
            return then-System.currentTimeMillis()-1;
        }
        return System.currentTimeMillis()-then;
    }

    public static long measureRuntime(Runnable runnable) {
        long then = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis()-then;
    }
}
