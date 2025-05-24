/**
 * Represents a Defenseman player, extending the Player class.
 */
public class Defenseman extends Player {
    
    public Defenseman (String name, String team, int gamesPlayed, String avgTOI, int evPoints, double capHit) {
        super(name, team, "Defenseman", gamesPlayed, avgTOI, evPoints, capHit);
    }
}