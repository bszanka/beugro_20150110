package elektronika;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Teve implements Comparable<Teve> {
    private String marka;
    private String tipus;
    private String[] tulajdonsagok;
    private int ar;

    public Teve(String marka, String tipus, Collection<String> tulajdonsagok, int ar) {
        this.marka = marka;
        this.tipus = tipus;
        this.tulajdonsagok = tulajdonsagok.toArray(new String[0]);
        this.ar = ar;
    }

    public String getMarka() {
        return marka;
    }

    public String getTipus() {
        return tipus;
    }

    public String[] getTulajdonsagok() {
        return tulajdonsagok;
    }

    public int getAr() {
        return ar;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teve)) return false;
        Teve teve = (Teve) o;
        return Objects.equals(marka, teve.marka) && Objects.equals(tipus, teve.tipus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marka, tipus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" (");
        for (String s : tulajdonsagok) {
            sb.append(s);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.toString().length()-2);
        sb.deleteCharAt(sb.toString().length()-1);
        sb.append(")");
        return marka + " " + tipus + sb.toString() + ", " + ar + " Ft";
    }

    @Override
    public int compareTo(Teve o) {
        int kulonbseg = Integer.compare(ar,o.ar) * -1;
        if(kulonbseg == 0){
            kulonbseg = marka.compareTo(o.marka);
            if(kulonbseg == 0){
                return tipus.compareTo(o.tipus);
            }
            else{
                return kulonbseg;
            }
        }
        else{
            return kulonbseg;
        }
    }
}
