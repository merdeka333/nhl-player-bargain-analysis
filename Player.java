/**
 * Represents a generic NHL player with relevant stats and calculated metrics during a single season.
 * Used as a base class for specific player positions (Center, Left Wing, Right Wing, Defenseman)
 */
public class Player {
    
    private String name;
    private String team;
    private String position;
    private int gamesPlayed; // Games played
    private String avgTOI; // Average time on ice per game in "mm:ss"
    private int evPoints; // Even-strength points (5 on 5)
    private double capHit;
    
    // Calculated fields
    private double totalTOI; // Total time on ice in minutes
    private double evpPer60; // Even-strength points per 60 minutes
    private double earningsPerEVPPer60; // Player's earnings per EVP/60 in dollars
    
    /**
     * Constructs a Player with provided attributes and calculates performance metrics.
     * 
     * @param name              Player's name
     * @param team              Player's team
     * @param position          Player's position
     * @param gamesPlayed       Number of games played
     * @param avgTOI            Average time on ice per game in "mm:ss"
     * @param evPoints          Even-strength points (5-on-5)
     * @param capHit            Player's cap hit (average annual value)
     */
    public Player (String name, String team, String position, int gamesPlayed, String avgTOI, int evPoints, double capHit) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.gamesPlayed = gamesPlayed;
        this.avgTOI = avgTOI;
        this.evPoints = evPoints;
        this.capHit = capHit;
        this.totalTOI = calculateTotalTOI();
        this.evpPer60 = calculateEVPPer60();
        this.earningsPerEVPPer60 = calculateEarningsPerEVPPer60();
    }
    
    /**
     * Converts a TOI string from "mm:ss" to decimal minutes.
     * 
     * @param toiString     Time string in mm:ss format
     * @return              Time in decimal minutes
     */
    public static double convertTOIStringToMinutes(String toiString) {
        String[] parts = toiString.split(":"); // split() method splits toiString into an array of substrings -- sourced from https://www.w3schools.com/java/ref_string_split.asp
        int minutes = Integer.parseInt(parts[0]); //parseInt() method extracts the primitive Integer values from the parts String -- sourced from https://www.tutorialspoint.com/java/number_parseint.htm
        int seconds = Integer.parseInt(parts[1]);
        return minutes + (seconds / 60.0);
    } 
    
    /** 
     * Calculates the total time on ice in minutes for the season.
     */ 
    public double calculateTotalTOI() {
        return convertTOIStringToMinutes(avgTOI) * gamesPlayed; // Multiplies avgTOI per game by gamesPlayed to get total ice time
    }
    
    /** 
     * Calculates even-strength points per 60 minutes.
     */
    public double calculateEVPPer60() {
        return (evPoints / totalTOI) * 60;
    }
    
    /**
     * Calculates earnings per even-strength point per 60 minutes -- shows how much money the player earns per 60-minute point contribution.
     */
    public double calculateEarningsPerEVPPer60() {
        return ((capHit / totalTOI) * 60) / evpPer60;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getTeam() {
        return team;
    }
    
    public String getPosition() {
        return position;
    }
    
    public int getEVPoints() {
        return evPoints;
    }
    
    public double getTotalTOI() {
        return totalTOI;
    }
    
    public double getEVPPer60() {
        return evpPer60;
    }
    
    public double getEarningsPerEVPPer60() {
        return earningsPerEVPPer60;
    }
}