import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class that handles analysis of NHL Player data.
 * Includes methods for reading players from file, sorting, and generating reports.
 */
public class PlayerAnalyzer {
    
    /**
     * Reads player data from a text file and returns a list of Player objects.
     * 
     * @param filePath      Path to input text file
     * @return players      ArrayList of Player objects        
     */
    public static ArrayList<Player> readPlayers(String filePath) throws IOException {
        ArrayList<Player> players = new ArrayList<>(); // Creates an empty ArrayList of Player objects
        Scanner scanner = new Scanner(new File(filePath));

        // Each line in the input file contains data, separated by commas, for a specific player
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(","); // Splits each line into an array of substrings
            String name = parts[0]; // Zeroth index of line contains player's name
            String team = parts[1]; // First index of line contains player's team
            String position = parts[2]; // Second index of line contains player's position, etc.
            int gamesPlayed = Integer.parseInt(parts[3]); 
            String avgTOI = parts[4];
            int evPoints = Integer.parseInt(parts[5]);
            double capHit = Double.parseDouble(parts[6]);
            
            // Declares a reference variable of type Player (superclass)
            Player p;
            if (position.equals("C")) {
                // If position is "C", creates a Center object (subclass of Player)
                // and assigns it to the player reference variable 'p'
                p = new Center(name, team, gamesPlayed, avgTOI, evPoints, capHit);
            }
            else if (position.equals("L")) {
                // If position is "L", creates a LeftWing object (subclass of Player)
                p = new LeftWing(name, team, gamesPlayed, avgTOI, evPoints, capHit);
            }
            else if (position.equals("R")) {
                // If position is "R", creates a RightWing object (subclass of Player)
                p = new RightWing(name, team, gamesPlayed, avgTOI, evPoints, capHit);
            }
            else {
                // If position is "D", creates a Defenseman object (subclass of Player)
                p = new Defenseman(name, team, gamesPlayed, avgTOI, evPoints, capHit);
            }
            players.add(p); // Adds the subclass object to the players list
        }
        
        scanner.close();
        return players;
    }
    
    /**
     * Sorts the list of players in ascending order by earnings per EVP/60 using insertion sort
     * 
     * @param players       List of Player objects
     */
    public static void sortPlayersByEarnings(ArrayList<Player> players) {
        for (int i = 1; i < players.size(); i++) {
            Player key = players.get(i);
            int j = i - 1;
            while (j >= 0 && players.get(j).getEarningsPerEVPPer60() > key.getEarningsPerEVPPer60()) {
                players.set(j + 1, players.get(j));
                j--;
            }
            players.set(j + 1, key);
        }
    }
    
    /**
     * Recursively finds the best value (lowest earnings per EVP/60) player.
     * 
     * @param players       List of Player objects
     * @param index         Current index in recursion
     * @param best          Player with lowest earnings so far
     * @return best         Player with best bargain value
     */
    public static Player findTopBargain(ArrayList<Player> players, int index, Player best) {
        if (index == players.size()) {
            return best;
        }
        if (players.get(index).getEarningsPerEVPPer60() < best.getEarningsPerEVPPer60()) {
            best = players.get(index);
        }
        
        return findTopBargain(players, index + 1, best);
    }
    
    /**
     * Writes a formatted report of players' performance and earnings metrics to an output file.
     * 
     * @param team          Team name
     * @param players       Sorted list of Player objects
     * @param outputFile    File path for writing the report
     */
    public static void writeReport(String team, ArrayList<Player> players, String outputFile) throws IOException {
        PrintWriter output = new PrintWriter(outputFile);
        
        output.println("2024-2025 Player Bargain Report -- " + team);
        output.println();
        
        for (Player p : players) {
            output.println("Name: " + p.getName());
            output.println("Position: " + p.getPosition());
            output.println("Time on Ice (min): " + String.format("%.2f", p.getTotalTOI())); // Rounds to 2 decimal places
            output.println("Even-Strength Points: " + p.getEVPoints());
            output.println("Even-Strength Points per 60 mins: " + String.format("%.2f", p.getEVPPer60()));
            output.println("Earnings per Even-Strength Point per 60 mins: $" + String.format("%.2f", p.getEarningsPerEVPPer60()));
            output.println();
        }
        
        Player topBargain = findTopBargain(players, 0, players.get(0)); // Top bargain player is located at zeroth index of the sorted array
        Player mostOverpaid = players.get(players.size() - 1); // Most overpaid player is located at last index of the sorted array
        
        output.println("Top Bargain: " + topBargain.getName());
        output.println("Most Overpaid: " + mostOverpaid.getName());
        output.close();
    }
}