package lt.compiler;

import java.util.Vector;
import java.util.Iterator;

public class SymbolTable implements Iterable<Descriptor> {

    private Vector<Descriptor> table;

    public SymbolTable() {
        table = new Vector<Descriptor>();
    }

    // Look for the descriptor of an identifier inside the table.
    // If not found, return null.
    public Descriptor find(String s) {
        int pos = table.indexOf(new Descriptor(s));
        if (pos == -1)
            return null;
        return table.elementAt(pos);
    }

    // Insert a descriptor inside the table.
    public void insert(Descriptor d) {
        table.add(d);
    }

    // Look for the descriptor of an identifier inside the table.
    // If not found, insert it into the table.
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

