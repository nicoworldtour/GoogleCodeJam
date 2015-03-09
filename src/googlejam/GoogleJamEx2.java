/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Nicolas
 */
public class GoogleJamEx2 {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(new Locale("en", "US"));
        InputStream in = new FileInputStream(new File("./input2.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = new FileOutputStream(new File("./output2.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        NumberFormat nf = new DecimalFormat(".#######");

        nf.setMaximumFractionDigits(7);
        nf.setMinimumFractionDigits(7);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        
        int testCase = 0;
        int game = 0;
        double farmCost = 0.0;
        double farmProduction = 0.0;
        double finalQty = 0.0;
        int CPerSec = 2;
        testCase = Integer.parseInt(reader.readLine());
        while (++game <= testCase) { 
           //read data
            String line = reader.readLine();
            String[] split = line.split(" ");
            farmCost       = Double.parseDouble(split[0]);
            farmProduction = Double.parseDouble(split[1]);
            finalQty       = Double.parseDouble(split[2]);
           
            // play
            //System.out.println("Game nb" + game +": C=" + farmCost + " F="+ farmProduction + " X="+ finalQty);
            double res = GoogleJamEx2.playGame(farmCost, farmProduction, finalQty);
            System.out.println("Case #"+game+": "+nf.format(res));
            writer.write("Case #"+game+": "+nf.format(res));
            writer.append('\n');
            
            
        }

        reader.close();
        writer.close();
    }
    
    private static double playGame(double C, double F, double X) {
        double resAnt = 0.0;
        int nbFactory = 0;
        double res = X/2; //worstCase
        do {     
            resAnt = res; 
            nbFactory++;
            res = C/2 + X/(2+(F*nbFactory));
            //if (nbFactory>1) {
                int tmp = nbFactory;
                while (tmp>1) {
                    res += C/(2+F*(tmp-- -1));
                }
            //}
            
        } while (res < resAnt);
        //C/2 F*
        
        
        return resAnt;
    }

}
