package elektronika;

import java.util.Collection;

public class LEDTeve extends Teve{
    private boolean oled;

    public LEDTeve(String marka, String tipus, Collection<String> tulajdonsagok, int ar, boolean oled) {
        super(marka, tipus, tulajdonsagok, ar);
        this.oled = oled;
    }

    public boolean isOled() {
        return oled;
    }
}
