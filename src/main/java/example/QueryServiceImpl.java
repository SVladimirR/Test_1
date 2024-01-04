package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class QueryServiceImpl implements QueryService {
    private StringBuilder output;

    @Override
    public void processInputData(InputData inputData) {
        output = new StringBuilder();

        inputData.queries.parallelStream().forEachOrdered(query -> {
            char[] podS = getSubstring(query, inputData.s);
            char kthChar = getKthChar(podS, query.k);
            int countKthChar = countKthCharOccurrences(podS, kthChar, query.k);
            int charPosition = getCharPosition(podS, countKthChar, getOppositeChar(kthChar));

            output.append(charPosition).append("\n");
        });

        writeOutputToFile(output.toString(), "output.txt");
    }

    private char[] getSubstring(Query query, String s) {
        char[] result = new char[query.r - query.l + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.charAt(query.l + i - 1);
        }
        return result;
    }

    private char getKthChar(char[] substring, int k) {
        return substring[k - 1];
    }

    private int countKthCharOccurrences(char[] substring, char kthChar, int k) {
        int count = 0;
        for (char c : substring) {
            if (c == kthChar) {
                count++;
                if (count == k) {
                    break;
                }
            }
        }
        return count;
    }

    private int getCharPosition(char[] substring, int k, char kthChar) {
        int count = 0;
        int position = -1;
        for (int i = 0; i < substring.length; i++) {
            if (substring[i] == kthChar) {
                count++;
                if (count == k) {
                    position = i + 1;
                    break;
                }
            }
        }
        return position;
    }

    private char getOppositeChar(char kthChar) {
        return (kthChar == 'A') ? 'B' : 'A';
    }

    private void writeOutputToFile(String output, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(output);
            System.out.println("Results have been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}