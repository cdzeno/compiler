/* Un oggetto di questa classe e' una symbol table */

import java.util.Vector;
import java.util.Iterator;

public class SymbolTable implements Iterable<Descrittore> {

  private Vector<Descrittore> tabella; //implementazione basata su Vector

  /* Costruisce tabella vuota */
  public SymbolTable() {
    tabella = new Vector<Descrittore>();
  }

  /* Cerca il descrittore di una stringa nella tabella,
     se non c'e' restituisce null */
  public Descrittore trova(String s) {
    int posizione = tabella.indexOf(new Descrittore(s));
    if (posizione == -1)
      return null;
    else
      return tabella.elementAt(posizione);
  }

  /* Aggiunge un descrittore alla tabella */
  public void aggiungi(Descrittore d) {
    tabella.add(d);
  }

  /* Cerca il descrittore di una stringa nella tabella,
     se non c'e' lo aggiunge */
  public Descrittore trovaEAggiungi(String s) {
    Descrittore d = this.trova(s);
    if (d == null) {
      d = new Descrittore(s);
      this.aggiungi(d);
    }
    return d;
  }
  
  public Iterator<Descrittore> iterator() {
    return tabella.iterator();
  }

}
