/**
 * Represents a Right Wing player, extending the Player class.
 */
public class RightWing extends Player {
    
    public RightWing (String name, String team, int gamesPlayed, String avgTOI, int evPoints, double capHit) {
        super(name, team, "Right Wing", gamesPlayed, avgTOI, evPoints, capHit);
    }
}