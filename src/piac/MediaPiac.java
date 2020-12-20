package piac;

import elektronika.LEDTeve;
import elektronika.SmartTeve;
import elektronika.Teve;
import elektronika.TeveUzlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediaPiac implements TeveUzlet {
    private String nev;
    private String cim;
    private List<Teve> tevek;

    public MediaPiac(String nev, String cim, Teve[] teveTomb) {
        this.nev = nev;
        this.cim = cim;
        this.tevek = Arrays.asList(teveTomb);
    }

    @Override
    public List<Teve> adottTulajdonsaguTevek(String tulajdonsag) {
        List<Teve> adottTulajdonsaguak = new ArrayList<>();
       for (Teve t: tevek) {
           for (String s : t.getTulajdonsagok()) {
               if(s.contains(tulajdonsag))
                   adottTulajdonsaguak.add(t);
           }
       }
       adottTulajdonsaguak.sort(null);
       return adottTulajdonsaguak;
    }

    @Override
    public List<Teve> adottTulajdonsaguModernTevek(String tulajdonsag) {
        List<Teve> adottTulajdonsaguak = new ArrayList<>();
        for (Teve t: tevek) {
            for (String s : t.getTulajdonsagok()) {
//                System.out.println(s);
                if (s.contains(tulajdonsag) &&
                        ((t instanceof LEDTeve && ((LEDTeve) t).isOled())
                                || (t instanceof SmartTeve && ((SmartTeve) t).isUjAlkalmazasok())))
                    adottTulajdonsaguak.add(t);
            }
        }
        adottTulajdonsaguak.sort(null);
        return adottTulajdonsaguak;
    }

    @Override
    public void kiir(String fajlnev) {
        File fajl = new File(fajlnev);
        try {
            FileWriter fw = new FileWriter(fajl);
            List<Teve> egyeb = new ArrayList<>();
            List<Teve> ledTevek = new ArrayList<>();
            List<Teve> smartTevek = new ArrayList<>();
            for (Teve t: tevek) {
                if(t instanceof LEDTeve)
                    ledTevek.add(t);
                if(t instanceof SmartTeve)
                    smartTevek.add(t);
                else
                    egyeb.add(t);
            }
            ledTevek.sort(null);
            smartTevek.sort(null);
            egyeb.sort(null);
            fw.append("LED-tévék\n");
            for (Teve t : ledTevek) {
                fw.append(t + "\n");
            }
            fw.append("Smart tévék\n");
            for (Teve t : smartTevek) {
                fw.append(t + "\n");
            }
            fw.append("Egyéb tévék\n");
            for (Teve t : egyeb) {
                fw.append(t + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Teve> egyeb = new ArrayList<>();
        List<Teve> ledTevek = new ArrayList<>();
        List<Teve> smartTevek = new ArrayList<>();
        for (Teve t: tevek) {
            if(t instanceof LEDTeve)
                ledTevek.add(t);
            else if(t instanceof SmartTeve)
                smartTevek.add(t);
            else
                egyeb.add(t);
        }
        ledTevek.sort(null);
        smartTevek.sort(null);
        egyeb.sort(null);
        sb.append("LED-tévék\n");
        for (Teve t : ledTevek) {
            sb.append(t + "\n");
        }
        sb.append("Smart tévék\n");
        for (Teve t : smartTevek) {
            sb.append(t + "\n");
        }
        sb.append("Egyéb tévék\n");
        for (Teve t : egyeb) {
            sb.append(t + "\n");
        }
        return nev + " " + cim + "\n\n" + sb.toString();
    }

    public boolean kezdoBetu(String s){
        boolean valtozott = false;
        for (Teve t : tevek) {
            if(t.getMarka().startsWith(s)) {
                t.setMarka(t.getMarka().toUpperCase());
                valtozott = true;
            }
        }
        return valtozott;
    }

    public  boolean melyikTobb(){
        int led = 0;
        int smart = 0;
        for (Teve t : tevek) {
            for (String s: t.getTulajdonsagok()) {
                if(s.equalsIgnoreCase("led"))
                    led++;
                if(s.equalsIgnoreCase("smart"))
                    smart++;
            }
            if(t instanceof LEDTeve)
                led++;
            if(t instanceof SmartTeve)
                smart++;
        }
        return smart > led;
    }

}
