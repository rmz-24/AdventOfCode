import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String[] readFileToArray(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Add each line to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to a String array
        return lines.toArray(new String[0]);
    }


    public static int calculateSum(String[] lines, String regex) {
        Pattern pattern = Pattern.compile(regex);
        int sum = 0;

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));

                int product = x * y;
                sum += product;

                System.out.println("Match: " + matcher.group() + " => Product: " + product);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        String filePath = "input/input_day3.txt";
        String[] fileContent = readFileToArray(filePath);

        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

        int totalSum = calculateSum(fileContent, regex);

        System.out.println("Total Sum: " + totalSum);
    }
}
