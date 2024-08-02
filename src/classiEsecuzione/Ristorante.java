package classiEsecuzione;

import ordineDAO.Ordine;
import ordineDAO.OrdineDAO;
import ordineDAO.OrdineDAOImpl;

import javax.swing.*;
import java.util.List;

public class Ristorante {
    public static void main(String[] args) {

        OrdineDAO dao = new OrdineDAOImpl();
        int scelta = -1;

        while (scelta != 0) {

            String inputScelta = JOptionPane.showInputDialog("MENU GESTIONE ORDINI" +
                    "\n1) Inserisci nuovo ordine" +
                    "\n2) Visualizza un ordine" +
                    "\n3) Visualizza tutti gli ordini" +
                    "\n4) Modifica ordine" +
                    "\n5) Elimina ordine" +
                    "\n0) Esci");

            if (inputScelta == null || inputScelta.trim().isEmpty()) {
                scelta = -1;
            } else {
                try {
                    scelta = Integer.parseInt(inputScelta);
                } catch (NumberFormatException e) {
                    scelta = -1;
                }
            }

            switch (scelta) {

                case 1: {
                    int numeroTavolo = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero del tavolo"));
                    int numeroPersone = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero delle persone"));
                    double conto = Double.parseDouble(JOptionPane.showInputDialog("Inserisci il conto"));

                    Ordine o = new Ordine(numeroTavolo, numeroPersone, conto);
                    Ordine verificaOrdine = dao.leggiOrdine(numeroTavolo);
                    if (verificaOrdine == null) {
                        dao.inserisciOrdine(o);
                        JOptionPane.showMessageDialog(null, "Ordine inserito correttamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "L'ordine non può essere inserito");
                    }
                    break;
                }

                case 2: {
                    int numeroTavolo = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero del tavolo"));
                    StringBuilder messaggio = new StringBuilder();
                    Ordine ordine = dao.leggiOrdine(numeroTavolo);
                    messaggio.append("Numero Tavolo: ")
                            .append(ordine.getNumeroTavolo())
                            .append(" - ")
                            .append("Numero persone: ")
                            .append(ordine.getNumeroPersone())
                            .append(" - ").append("Conto: ")
                            .append(ordine.getConto())
                            .append("\n");
                    JOptionPane.showMessageDialog(null, messaggio.toString());
                    break;
                }

                case 3: {
                    StringBuilder messaggio = new StringBuilder();
                    List<Ordine> ordini = dao.leggiOrdini();
                    for (Ordine o : ordini) {
                        messaggio.append("Numero Tavolo: ")
                                .append(o.getNumeroTavolo())
                                .append(" - ")
                                .append("Numero persone: ")
                                .append(o.getNumeroPersone())
                                .append(" - ").append("Conto: ")
                                .append(o.getConto())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, messaggio.toString());
                    break;
                }

                case 4: {
                    int numeroTavolo = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero del tavolo da modificare: "));
                    Ordine ordineEsistente = dao.leggiOrdine(numeroTavolo);

                    if (ordineEsistente != null) {
                        int nuovoNumeroPersone = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il nuovo numero di persone: ", ordineEsistente.getNumeroPersone()));
                        double nuovoConto = Double.parseDouble(JOptionPane.showInputDialog("Inserisci il nuovo conto: ", ordineEsistente.getConto()));

                        ordineEsistente.setNumeroPersone(nuovoNumeroPersone);
                        ordineEsistente.setConto(nuovoConto);

                        dao.modificaOrdine(numeroTavolo, ordineEsistente);
                        JOptionPane.showMessageDialog(null, "Ordine aggiornato correttamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ordine non trovato");
                    }
                    break;
                }

                case 5: {
                    int numeroTavolo = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il numero del tavolo da eliminare: "));
                    dao.eliminaOrdine(numeroTavolo);
                    JOptionPane.showMessageDialog(null, "Eliminazione effettuata");
                    break;
                }


                case 0: {
                    JOptionPane.showMessageDialog(null, "Arrivederci");
                    break;
                }
                default: {
                    JOptionPane.showMessageDialog(null, "Valore digitato non corretto");
                }
            }
        }
    }
}