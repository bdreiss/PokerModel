package net.berndreiss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import static net.berndreiss.HandEvaluation.compareHands;

public class ParallelPokerEquityCalculator extends RecursiveTask<Map<String, double[]>> {

    private static final String API_URL = "http://www.cardfight.com/";

    private static int runs = 0;

    public static double[] calculateEquity(StartingHands hand1, StartingHands hand2) {
        StringBuilder content = new StringBuilder();
        System.out.println("RUN: " + runs++);
        double[] result = new double[3];
        try {
            String urlString = API_URL + hand1.getShortHand() + "_" + hand2.getShortHand() + ".html";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();
            String page = content.toString();


            result[0] = Double.parseDouble(page.split("Win")[1].split("<br>")[1].split(" %")[0]);
            result[1] = Double.parseDouble(page.split("Win")[2].split("<br>")[1].split(" %")[0]);
            result[2] = Double.parseDouble(page.split("Win")[1].split("Tie")[1].split("<br>")[1].split(" %")[0]);
        } catch (RuntimeException | IOException e) {
            try {
                StartingHands temp = hand1;
                hand1 = hand2;
                hand2 = temp;
                String urlString = API_URL + hand1.getShortHand() + "_" + hand2.getShortHand() + ".html";
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();
                String page = content.toString();


                result[1] = Double.parseDouble(page.split("Win")[1].split("<br>")[1].split(" %")[0]);
                result[0] = Double.parseDouble(page.split("Win")[2].split("<br>")[1].split(" %")[0]);
                result[2] = Double.parseDouble(page.split("Win")[1].split("Tie")[1].split("<br>")[1].split(" %")[0]);
            }catch(RuntimeException | IOException e1){System.out.println(e1.toString());}
        }

        return result;
    }

    private static int THRESHOLD;
    private static List<List<StartingHands>> startingHandCombinations;
    private final int start;
    private final int end;

    ParallelPokerEquityCalculator(int start, int end){
        this.start = start;
        this.end = end;
    }

    ParallelPokerEquityCalculator(List<List<StartingHands>> startingHandCombinations, int THRESHOLD){
        this.startingHandCombinations = startingHandCombinations;
        this.THRESHOLD = THRESHOLD;
        start = 0;
        end = startingHandCombinations.size();
    }


    @Override
    protected Map<String, double[]> compute() {
        int length = end - start;
        if (length <= THRESHOLD)
            return computeSequentially();

        ParallelPokerEquityCalculator leftTask = new ParallelPokerEquityCalculator(start, start+length/2);
        leftTask.fork();
        ParallelPokerEquityCalculator rightTask = new ParallelPokerEquityCalculator(start+length/2, end);
        Map<String, double[]> rightResult = rightTask.compute();
        Map<String, double[]> leftResult = leftTask.join();
        rightResult.putAll(leftResult);
        return rightResult;

    }

    private Map<String, double[]> computeSequentially(){
       Map<String, double[]> results = new HashMap<>();
        for (int i = start; i < end; i++) {
            List<StartingHands> combination = startingHandCombinations.get(i);

            double[] result = null;
            try {
                result = calculateEquity(combination.get(0), combination.get(1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("GET " + combination.get(0) + ", " + combination.get(1));
            System.out.println(result[0]);
            results.put(combination.get(0).getShortHand()+combination.get(1).getShortHand(), result);
        }
        return results;
    }



}
