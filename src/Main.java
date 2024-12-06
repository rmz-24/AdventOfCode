import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static ArrayList<ArrayList<Integer>> report = new ArrayList<>();

    public static void readFile() {
        try {
            // Reading the file
            File file = new File("input/input_day2.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(" ");

                    // Create a new row for the current line
                    ArrayList<Integer> row = new ArrayList<>();
                    for (String part : parts) {
                        row.add(Integer.parseInt(part));  // Parse and add each number
                    }
                    report.add(row);  // Add the row to the report
                }
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public static boolean condOne(ArrayList<Integer> row) {
        int e1 = row.get(0);
        int e2 = row.get(1);
        if(e1 < e2){
            for(int i=2; i<row.size(); i++){
                if(row.get(i-1) > row.get(i)){
                    return false;
                }
            }
        } else if (e1 > e2) {
            for(int i=2; i<row.size(); i++){
                if(row.get(i-1) < row.get(i)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkAL(ArrayList<Integer> row) {
        for(int i=0; i < row.size() - 1; i++){
            if( Math.abs(row.get(i)-row.get(i+1)) < 1 || Math.abs(row.get(i)-row.get(i+1)) > 3 ){
                return false;
            }
        }
        return true;
    }

    public static boolean pbDp(ArrayList<Integer> row){
        for(int i=0; i < row.size(); i++){
            int e = row.get(i);
            row.remove(i);
            if(condOne(row) && checkAL(row)){
                return true;
            } else{
                row.add(i, e);
            }
        }
        return false;
    }

    public static int safeCount(ArrayList<ArrayList<Integer>> r){
        int count=0;
        for (ArrayList<Integer> integers : r) {
            if (condOne(integers) && checkAL(integers) || pbDp(integers)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        readFile();
        System.out.println(safeCount(report));
    }
}