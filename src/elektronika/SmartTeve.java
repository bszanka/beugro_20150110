package elektronika;

import java.util.Collection;

public class SmartTeve extends Teve{
    private boolean ujAlkalmazasok;

    public SmartTeve(String marka, String tipus, Collection<String> tulajdonsagok, int ar, boolean ujAlkalmazasok) {
        super(marka, tipus, tulajdonsagok, ar);
        this.ujAlkalmazasok = ujAlkalmazasok;
    }

    public boolean isUjAlkalmazasok() {
        return ujAlkalmazasok;
    }
}
