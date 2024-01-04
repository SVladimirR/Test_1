package example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("input.txt")))) {

            String[] firstLine = reader.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int q = Integer.parseInt(firstLine[1]);


//            if (q < 0) {
//                throw new IllegalArgumentException("Number of queries should be non-negative.");
//            }

            String s = reader.readLine();

            if (s.length() != n) {
                throw new IllegalArgumentException("Length of string s should be equal to n.");
            }

            List<Query> queries = new ArrayList<>();
            for (int i = 0; i < q; i++) {
                String[] query = reader.readLine().split(" ");
                if (query.length != 3) {
                    throw new IllegalArgumentException("Each query should have three parameters.");
                }
                int l = Integer.parseInt(query[0]);
                int r = Integer.parseInt(query[1]);
                int k = Integer.parseInt(query[2]);

                if (l > r || r > n ) {
                    throw new IllegalArgumentException("Invalid query parameters.");
                }

                queries.add(new Query(l, r, k));
            }

            InputData inputData = new InputData(n, q, s, queries);

            QueryService queryService = new QueryServiceImpl();
            queryService.processInputData(inputData);

        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1_000_000;

        System.out.println("Program time: " + executionTime + " milliseconds");
    }

}