package ordineDAO;

import java.util.List;

public interface OrdineDAO {

    public void inserisciOrdine(Ordine o);

    public void modificaOrdine(int numeroTavolo, Ordine o);

    public void eliminaOrdine(int numeroTavolo);

    public List<Ordine> leggiOrdini();

    public Ordine leggiOrdine(int numeroTavolo);

}