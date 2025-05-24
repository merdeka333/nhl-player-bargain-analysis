/**
 * Represents a Center player, extending the Player class.
 */
public class Center extends Player {
    
    public Center (String name, String team, int gamesPlayed, String avgTOI, int evPoints, double capHit) {
        super(name, team, "Center", gamesPlayed, avgTOI, evPoints, capHit);
    }
}