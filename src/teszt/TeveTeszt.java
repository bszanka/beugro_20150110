package teszt;

import elektronika.LEDTeve;
import elektronika.SmartTeve;
import elektronika.Teve;
import piac.MediaPiac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class TeveTeszt {
    public static double atlagTulajdonsagok(Collection<Teve> tevekParam){
        int db = 0, sum = 0;
        for (Teve t: tevekParam) {
            for (String s : t.getTulajdonsagok()) {
                sum++;
            }
            db++;
        }
        double atlag = (double) sum/db;
        return atlag;
    }

    public static int modernPiacok(Collection<MediaPiac> piacok, String s){
        int c = 1;
        for (MediaPiac mp : piacok) {
            if(mp.adottTulajdonsaguModernTevek(s).size() > 3) {
                File fajl = new File("lista" + c + ".txt");
                try {
                    FileWriter fw = new FileWriter(fajl);
                    fw.append(mp.toString());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                c++;
            }
        }
        return c-1;
    }
    public static void main(String[] args) {
        if (args[0].length() == 0)
            return;
        try {
            List<Teve> tevek = new ArrayList<>();
            Scanner fajl = new Scanner(new File(args[0]));
            while (fajl.hasNextLine()) {
                Scanner sor = new Scanner(fajl.nextLine());
                sor.useDelimiter(";");
                boolean led = false;
                boolean smart = false;
                String fajta = sor.next();
                if (fajta.equals("L"))
                    led = true;
                else if (fajta.equals("S"))
                    smart = true;
                String marka = sor.next();
                String tipus = sor.next();
                int ar = Integer.parseInt(sor.next());
                boolean modern = false;
                String smartE = null;
                if (led || smart) {
                    smartE = sor.next();
                    if (smartE != null && (smartE.equals("+") || smartE.equals("smart")))
                        modern = true;
                }
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
            MediaPiac mp = new MediaPiac(args[1].length() == 0 ? "Debreceni Médiapiac" : args[1],
                    args[2].length() == 0 ? "Debrecen, Piac u." : args[2],tevek.toArray(new Teve[0]));

            Scanner stdin = new Scanner(System.in);
            String input = stdin.next();
            List<Teve> res = mp.adottTulajdonsaguTevek(input);
            for (Teve t: res) {
                System.out.println(t.toString());
            }

            double sum = 0;
            for (Teve t: tevek) {
                sum = 0;
                for (String s : t.getTulajdonsagok()) {
                    sum++;
                }
                if(sum < atlagTulajdonsagok(tevek))
                    System.out.println(t.toString());
            }

            if(mp.kezdoBetu(stdin.next())){
                System.out.println("Változások történtek az alábbi piacon: ");
                System.out.println(mp.toString());
            }
            else{
                System.out.println("Nem történt változás.");
            }
            System.out.println(mp.melyikTobb() ? "Több Smart tévé van, mint LED-es."
                        : "Több LED-es tévé van, mint Smart.");

            List<MediaPiac> piacok = new ArrayList<>();
            piacok.add(mp);
            System.out.println("Létrehozott fájlok száma: " + modernPiacok(piacok, input));

        } catch (FileNotFoundException e) {
            System.err.println("A megadott fájl nem található!");
            return;
        }
    }
}
