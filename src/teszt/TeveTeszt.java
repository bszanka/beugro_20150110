package teszt;

import elektronika.LEDTeve;
import elektronika.SmartTeve;
import elektronika.Teve;
import piac.MediaPiac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeveTeszt {

    public static void main(String[] args) {
        if (args[0].length() == 0)
            return;
        try {
//            System.out.println(args[0]);
            List<Teve> tevek = new ArrayList<>();
            Scanner fajl = new Scanner(new File(args[0]));
            while (fajl.hasNextLine()) {
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                boolean led = false;
                boolean smart = false;
                boolean egyeb = false;
                String fajta = sor.next();
                if (fajta.equals("L"))
                    led = true;
                else if (fajta.equals("S"))
                    smart = true;
                else
                    egyeb = true;
                String marka = sor.next();
                String tipus = sor.next();
                int ar = Integer.parseInt(sor.next());
                boolean modern = false;
                if (led || smart)
                    if (sor.next().equals("+"))
                        modern = true;
                List<String> tulajdonsagok = new ArrayList<>();
                if (sor.hasNext()) {
                    sor.useDelimiter("\n");
                    Scanner prop = new Scanner(sor.next().substring(1));
                    prop.useDelimiter(",");
                    while (prop.hasNext()) {
                        tulajdonsagok.add(prop.next());
                    }
                }
                if(led)
                    tevek.add(new LEDTeve(marka, tipus, tulajdonsagok, ar, modern));
                else if(smart)
                    tevek.add(new SmartTeve(marka, tipus, tulajdonsagok, ar, modern));
                else
                    tevek.add(new Teve(marka, tipus, tulajdonsagok, ar));

            }
//            System.out.println(tevek.toString());
            MediaPiac mp = new MediaPiac(args[1].length() == 0 ? "Debreceni Médiapiac" : args[1],
                    args[2].length() == 0 ? "Debrecen, Piac u." : args[2],tevek.toArray(new Teve[0]));
//            System.out.println(mp.toString());
            Scanner stdin = new Scanner(System.in);
            List<Teve> res = mp.adottTulajdonsaguTevek(stdin.next());
            for (Teve t: res) {
                System.out.println(t.toString());
            }

        } catch (FileNotFoundException e) {
            System.err.println("A megadott fájl nem található!");
            return;
        }
    }
}
