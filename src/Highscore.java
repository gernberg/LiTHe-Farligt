
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gustav
 */
public class Highscore {
    private static String highscore_file = "highscore.txt";
    public static long getHighscore() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(highscore_file));
            return Long.valueOf(br.readLine());
        }catch(Exception e){
            // TODO: Ingen felhantering
            System.out.println(e);
        }
        return -1;
    }

    public static boolean writeNewHighscore(long score) {
        // Skriv bara in ny highscore om den är större än den förra.
        if (score > getHighscore()) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(highscore_file));
                bw.write(String.valueOf(score));
                bw.close();
                return true;
            } catch (IOException e) {
                // TODO: ingen felhantering
            }
        }
        return false;
    }
}
