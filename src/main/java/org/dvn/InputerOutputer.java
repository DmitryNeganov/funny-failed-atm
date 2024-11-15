package org.dvn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputerOutputer {

    public static String[] readLinesFromConsole(int lineCount) {
        String[] input = new String[lineCount];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < lineCount; i++) {
                input[i] = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    public static void writeLinesToConsole(String[] lines) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (String line: lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
