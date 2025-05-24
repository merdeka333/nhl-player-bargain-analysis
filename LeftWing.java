/**
 * Represents a Left Wing player, extending the Player class.
 */
public class LeftWing extends Player {
    
    public LeftWing (String name, String team, int gamesPlayed, String avgTOI, int evPoints, double capHit) {
        super(name, team, "Left Wing", gamesPlayed, avgTOI, evPoints, capHit);
    }
}