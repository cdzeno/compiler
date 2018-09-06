package lt.compiler;

import java.util.Vector;
import java.util.Iterator;

public class SymbolTable implements Iterable<Descriptor> {

	private Vector<Descriptor> table;

	public SymbolTable() {
		table = new Vector<Descriptor>();
	}

	/* Cerca il descrittore di una stringa nella tabella,
		 se non c'e' restituisce null */
	public Descriptor find(String s) {
		int pos = table.indexOf(new Descriptor(s));
		if (pos == -1)
			return null;
		return table.elementAt(pos);
	}

	/* Aggiunge un descrittore alla tabella */
	public void insert(Descriptor d) {
		table.add(d);
	}

	/* Cerca il descrittore di una stringa nella tabella,
		 se non c'e' lo aggiunge */
	public Descriptor findInsert(String s) {
		Descriptor d = find(s);
		if (d == null) {
			d = new Descriptor(s);
			insert(d);
		}
		return d;
	}
	
	public Iterator<Descriptor> iterator() {
		return table.iterator();
	}

}
