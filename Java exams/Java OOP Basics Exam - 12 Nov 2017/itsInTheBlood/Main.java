package itsInTheBlood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HealthManager hm = new HealthManager();

        while (true) {

            String line = reader.readLine();
            if (line.equals("BEER IS COMING")) {
                break;
            }

            String[] tokens = line.split(" ");
            String result = null;

            switch (tokens[0]) {

                case "checkCondition":
                    result = hm.checkCondition(tokens[1]);
                    break;

                case "createOrganism":
                    result = hm.createOrganism(tokens[1]);
                    break;

                case "addCluster":
                    result = hm.addCluster(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                    break;

                case "addCell":
                    result = hm.addCell(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]),
                            Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]));
                    break;

                case "activateCluster":
                    result = hm.activateCluster(tokens[1]);
                    break;
            }

            if (result != null) {
                System.out.print(result);
            }
        }
    }
}
