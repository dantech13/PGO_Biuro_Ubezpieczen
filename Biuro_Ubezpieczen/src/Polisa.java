public class Polisa {

    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;

    private static int liczbaUtworzonychPolis = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 50.0;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa,
                  int poziomRyzyka, double wartoscPojazdu,
                  boolean czyMaAlarm, boolean czyBezszkodowyKlient) {

        ++liczbaUtworzonychPolis;

        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
    }

    public static int pobierzLiczbeUtworzonychPolis() {
        return liczbaUtworzonychPolis;
    }

    public String getNumerPolisy() {
        return numerPolisy;
    }

    public String getKlient() {
        return klient;
    }
}
