package elektronika;

public interface TeveUzlet {
    // visszaadja az üzletben kapható olyan tévék listáját a természetes
    // rendezettségük sorrendjében, amelyek tulajdonságai között szerepel a
    // paraméterként megkapott tulajdonság
    public java.util.List<Teve> adottTulajdonsaguTevek(String tulajdonsag);

    // visszaadja az üzletben kapható olyan modern tévék listáját a természetes
    // rendezettségük sorrendjében, amelyek tulajdonságai között szerepel a
    // paraméterként megkapott tulajdonság; egy tévé modern, ha OLED technológiával
    // készült LED-tévé, vagy olyan smart tévé, amelyre lehet új alkalmazásokat
    // telepíteni
    public java.util.List<Teve> adottTulajdonsaguModernTevek(String tulajdonsag);

    // kiírja a megadott nevű állományba a tévék listáját (mindegyiket külön
    // sorba), három felcímkézett csoportban: először a LED-tévéket („LED-tévék”
    // címkével), aztán a smart tévéket („Smart tévék” címkével), végül a többit
    // („Egyéb tévék” címkével), a csoportokon belül a természetes rendezettségük
    // sorrendjében
    public void kiir(String fajlnev);

}
