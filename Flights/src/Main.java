import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Input Data
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Departure: ");
        String departure = scanner.next();
        System.out.print("Input Arrival: ");
        String arrival = scanner.next();
        System.out.print("Input Transfers: ");
        int transfers = scanner.nextInt();

        System.out.println();

        // Create file and read it to 'flights'
        File file = new File("text.txt");
        String[][] flights = readFile(file);
        List<String> baza = new ArrayList<>();

        checkFlights(flights, departure, arrival, transfers, baza);
    }

    static void checkFlights(String[][] flights, String departure, String arrival, int transfers, List<String> baza) {
        boolean check = false;
        for (String[] flight : flights) {
            if (departure.equals(flight[0]) && arrival.equals(flight[1])) {
                for (String str : baza) {
                    System.out.println(str);
                }
                System.out.println(flight[0] + " " + flight[1] + " " + flight[2] + " " + flight[3] + " " + flight[4]);
                check = true;
            }
        }

        if (check) {
            System.out.println();
        }

        if (transfers < 1) {
            return;
        }

        for (String[] flight : flights) {
            if (departure.equals(flight[0]) && !arrival.equals(flight[1])) {
                baza.add(flight[0] + " " + flight[1] + " " + flight[2] + " " + flight[3] + " " + flight[4]);
                checkFlights(flights, flight[1], arrival, --transfers, baza);
            }
        }
    }

    static String[][] readFile(File file) throws Exception{

        // Reading file to array of chars
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Create a matrix of flights
        String[][] flights = new String[countOfLines(file)][5];

        // Filling matrix
        int i = 0;
        while (bufferedReader.ready()) {
            String[] str = bufferedReader.readLine().split(" ");
            flights[i] = str;
            i++;
        }

        return flights;
    }

    static int countOfLines(File file) throws Exception {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int count = 0;
        while (bufferedReader.ready()) {
            if (!bufferedReader.readLine().isEmpty()) {
                count++;
            }
        }

        return count;
    }
}
