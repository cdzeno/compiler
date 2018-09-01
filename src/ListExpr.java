import java.util.Iterator;
import java.util.Vector;

class ListExpr {

    private Vector<Expr> listIstruction;

    public ListExpr(){
        this.listIstruction = new Vector<Expr>();
    }

    public ListExpr(Expr expr){
        this.listIstruction = new Vector<Expr>();
        this.listIstruction.add(expr);
    }

    public void append(Expr expr){
        this.listIstruction.add(expr);
    }

    public Iterator<Expr> iterator(){
        return listIstruction.iterator();
    }
}