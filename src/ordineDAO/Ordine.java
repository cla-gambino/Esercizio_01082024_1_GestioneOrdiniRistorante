package ordineDAO;

public class Ordine {

    private int numeroTavolo;
    private int numeroPersone;
    private double conto;

    public Ordine(int numeroTavolo, int numeroPersone, double conto) {
        this.numeroTavolo = numeroTavolo;
        this.numeroPersone = numeroPersone;
        this.conto = conto;
    }

    public int getNumeroTavolo() {
        return numeroTavolo;
    }

    public void setNumeroTavolo(int numeroTavolo) {
        this.numeroTavolo = numeroTavolo;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public double getConto() {
        return conto;
    }

    public void setConto(double conto) {
        this.conto = conto;
    }
}
