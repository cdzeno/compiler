import java_cup.runtime.*;
import java.io.*;
import lt.macchina.*;
import static lt.macchina.Macchina.*;

class Compilatore {
  
  public static void main(String[] args) throws java.io.IOException {
    // creazione del canale di input
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //lettura della stringa da esaminare
    System.out.print("Espressione? ");
    String s = in.readLine();

    //creazione della symbol factory
    ComplexSymbolFactory sf = new ComplexSymbolFactory();

    //creazione dell'analizzatore lessicale
    Scanner scanner = new Scanner(new StringReader(s), sf);

    //creazione del parser
    Parser p = new Parser(scanner, sf);

    try {
      Symbol ris = p.parse();
      ExprConTab risultato = (ExprConTab) ris.value;
      Expr albero = risultato.getExpr();

      //valutazione dell'albero: notazione postfissa
      System.out.println("Notazione postfissa : " + albero.toString());

      SymbolTable tabella = risultato.getSymbolTable();

      Codice c = new Codice("eseguibile");

      /* PRIMA PARTE: assegna gli indirizzi alle variabili 
      */
      int proxIndirizzo = 0;
      for (Descrittore d : tabella) 
        proxIndirizzo = d.assegnaIndirizzo(proxIndirizzo);

      /* SECONDA PARTE: genera il codice per riservare lo spazio
                        per le variabili
         Per la costruzione della calcolatrice le variabili vengono
         inizializzate leggendo i valori da input.
         Nella parte commentata (che si adatta anche ad array di lunghezza
         nota in compilazione) sono invece inizializzate a zero
      */
      for (Descrittore d : tabella) {
        //for (int j = 0; i < d.getLunghezza(); j++)
        //  c.genera(PUSHIMM, 0);
        String id = d.getIdentificatore();

        //genera le istruzioni per stampare il nome
        //della variabile e leggerne il valore
        for (int k = 0; k < id.length(); k++) {
	  c.genera(PUSHIMM, id.charAt(k));
	  c.genera(OUTPUTCH);
        }
        c.genera(PUSHIMM, '?');
        c.genera(OUTPUTCH);      
        c.genera(PUSHIMM, ' ');
        c.genera(OUTPUTCH);
        c.genera(INPUT);
      }

      /* TERZA PARTE: genera il codice per valutare l'espressione
      */
      albero.generaCodice(c);
    
      /* QUARTA PARTE: genera il codice per stampare il risultato
                       e termina l'esecuzione
      */                 
      c.genera(OUTPUT);
      c.genera(PUSHIMM, '\n'); //ritorno a capo
      c.genera(OUTPUTCH);
      c.genera(HALT);

      c.fineCodice();

    } catch (Exception e)  {
      System.out.println(e);
    }

  }
}

