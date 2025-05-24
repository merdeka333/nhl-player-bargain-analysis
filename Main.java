import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Player> players = PlayerAnalyzer.readPlayers("/Users/merdekakorunovski/Desktop/NHL Player Bargain Analysis/team_info.txt");
        
        PlayerAnalyzer.sortPlayersByEarnings(players);
        
        String teamName = players.get(0).getTeam();
        PlayerAnalyzer.writeReport(teamName, players, "/Users/merdekakorunovski/Desktop/NHL Player Bargain Analysis/bargain_analysis.txt");
    }
}