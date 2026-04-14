import java.util.ArrayList;

public class BiuroUbezpieczen {

    private String nazwa;
    private ArrayList<Polisa> polisy;

    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }

    public void dodajPolise(Polisa polisa) {
        polisy.add(polisa);
    }

    public double policzLacznaSkladke() {
        double suma = 0;

        for (Polisa p : polisy) {
            suma += p.obliczSkladkeKoncowa();
        }

        return Math.round(suma * 100.0) / 100.0;
    }

    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;

        for (Polisa p : polisy) {
            suma += p.obliczSkladkeOdnowieniowa();
        }

        return Math.round(suma * 100.0) / 100.0;
    }

    public int policzPolisyWysokiegoRyzyka() {
        int licznik = 0;

        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() > 1500) {
                licznik++;
            }
        }

        return licznik;
    }

    public Polisa znajdzPoNumerze(String numerPolisy) {
        for (Polisa p : polisy) {
            if (p.getNumerPolisy().equals(numerPolisy)) {
                return p;
            }
        }
        return null;
    }

    public void wypiszTanszeNiz(double prog) {
        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() < prog) {
                System.out.println(p);
            }
        }
    }

    public void wypiszRaport() {
        System.out.println("------ RAPORT: " + nazwa + " ------");

        for (Polisa p : polisy) {
            System.out.println(p);
        }

        System.out.println("-----------------------");
        System.out.println("Łączna składka: " + policzLacznaSkladke());
        System.out.println("Prognoza odnowień: " + policzLacznaPrognozeOdnowien());
        System.out.println("Polisy wysokiego ryzyka: " + policzPolisyWysokiegoRyzyka());
        System.out.println("-----------------------");
    }
}