/* Ogni istanza di questa classe descrive un identificatore.
   "Predisposta" per gestire con semplici modifiche, identificatori di variabile
   che richiedano piu' indirizzi (es. array di lunghezza fissata in compilazione). */

public class Descrittore {

  private String identificatore;
  private int indirizzo;
  private int lunghezza = 1; //in questo esempio non viene modificata

  public Descrittore(String id, int val) {
    identificatore = id;
    indirizzo = val;
  }

  public Descrittore(String id) {
    this(id, 0);
  }

  public String getIdentificatore() {
    return identificatore;
  }

  public int getIndirizzo() {
    return indirizzo;
  }

  public int getLunghezza() {
    return lunghezza;
  }

  //Assegna alla variabile un numero di indirizzi consecutivi
  //pari a lunghezza, in cui il primo e' quello specificato
  //tramite l'argomento. Restituisce il primo indirizzo
  //successivo a tale blocco
  public int assegnaIndirizzo(int ind) {
    indirizzo = ind;
    return indirizzo + lunghezza;
  }

  public boolean equals(Descrittore d) {
    return this.identificatore.equals(d.identificatore);
  }

  public boolean equals(Object o) {
    if (o instanceof Descrittore)
      return equals((Descrittore) o);
    else
      return false;
  }

  public String toString() {
    return identificatore + " " + indirizzo + " " + lunghezza;
  }
}
