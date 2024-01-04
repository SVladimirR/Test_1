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

            char kthChar = getKthChar(inputData.s, query.l, query.k);
            CustomPair<char[], Integer> fs = getSubstring(query, inputData.s, kthChar, query.k);
            int charPosition = getCharPosition(fs, getOppositeChar(kthChar));

            output.append(charPosition).append("\n");
        });

        writeOutputToFile(output.toString(), "output.txt");
    }


    private CustomPair<char[], Integer> getSubstring(Query query, String s, char kthChar, int k) {
        char[] result = new char[query.r - query.l + 1];
        int countKthChar = 0;

        for (int i = 0; i < result.length; i++) {
            result[i] = s.charAt(query.l + i - 1);
            if (i < k && result[i] == kthChar) {
                countKthChar++;
            }
        }
        return new CustomPair<>(result, countKthChar);
    }


    private char getKthChar(String s, int originalPosition, int k) {
        return s.charAt(originalPosition - 1 + k - 1);
    }

    private int getCharPosition(CustomPair<char[], Integer> pair, char kthChar) {
        char[] substring = pair.getFirst();
        int k = pair.getSecond();
        int count = 0;
        int position = -1;

        for (int i = 0; i < substring.length; i++) {
            char currentChar = substring[i];
            if (currentChar == kthChar && ++count == k) {
                position = i + 1;
                break;
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
