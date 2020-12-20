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
           if(t.getTulajdonsagok().toString().contains(tulajdonsag))
               adottTulajdonsaguak.add(t);
       }
       adottTulajdonsaguak.sort(null);
       return adottTulajdonsaguak;
    }

    @Override
    public List<Teve> adottTulajdonsaguModernTevek(String tulajdonsag) {
        List<Teve> adottTulajdonsaguak = new ArrayList<>();
        for (Teve t: tevek) {
            if(t.getTulajdonsagok().toString().contains(tulajdonsag) &&
                    ((t instanceof LEDTeve && ((LEDTeve) t).isOled())
                            || (t instanceof SmartTeve && ((SmartTeve) t).isUjAlkalmazasok())))
                adottTulajdonsaguak.add(t);
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
            fw.append("LED-tévék " + ledTevek.toString() + "\nSmart tévék " +
                    smartTevek.toString() + "\nEgyéb tévék " + egyeb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
