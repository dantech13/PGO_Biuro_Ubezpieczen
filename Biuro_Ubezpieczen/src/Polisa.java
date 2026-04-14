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

    public double obliczSkladkeOdnowieniowa() {
        double aktualna = obliczSkladkeKoncowa();
        double nowa = aktualna;

        // zwiększenie za ryzyko
        if (poziomRyzyka == 4) {
            nowa *= 1.10;
        } else if (poziomRyzyka >= 5) {
            nowa *= 1.20;
        }

        // dopłata za drogi pojazd
        if (wartoscPojazdu > 60000) {
            nowa += 150;
        }

        // zniżki
        if (czyBezszkodowyKlient) {
            nowa *= 0.92;
        }

        if (czyMaAlarm) {
            nowa *= 0.95;
        }

        // ograniczenia (widełki)
        double min = aktualna * 0.9;
        double max = aktualna * 1.25;

        if (nowa < min) {
            nowa = min;
        }
        if (nowa > max) {
            nowa = max;
        }

        return Math.round(nowa * 100.0) / 100.0;
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

    public double obliczSkladkeKoncowa() {
        double skladka = skladkaBazowa + OPLATA_ADMINISTRACYJNA;

        skladka += poziomRyzyka * 120;

        if (wartoscPojazdu > 60000) {
            skladka += 200;
        }
        if (czyMaAlarm) {
            skladka -= 100;
        }
        if (czyBezszkodowyKlient) {
            skladka *= 0.9;
        }
        if (skladka<skladkaBazowa) {
            skladka = skladkaBazowa;
        }
        return Math.round(skladka * 100.0) / 100.0;
    }

    public String pobierzPodsumowanieRyzyka() {
        return "Polisa " + numerPolisy + " | klient: " + klient +
                " | poziom ryzyka: " + poziomRyzyka;
    }

    @Override
    public String toString() {
        return "Polisa{" +
                "numer='" + numerPolisy + '\'' +
                ", klient='" + klient + '\'' +
                ", skladkaKoncowa=" + obliczSkladkeKoncowa() +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Polisa other = (Polisa) obj;
        return this.numerPolisy.equals(other.numerPolisy);
    }
}
