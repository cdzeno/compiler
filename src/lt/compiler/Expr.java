/* La classe astratta Expr definisce parse tree per le espressioni.
   Le sottoclassi concrete si riferiscono ai tipi particolari di
   espressioni.
   Il metodo generaCodice permette di generare il codice per calcolare
   l'espressione.
*/

import lt.macchina.Codice;
import static lt.macchina.Macchina.*;

abstract class Expr {

  public abstract void generaCodice(Codice c);

}

class Assign extends Expr {

  private Descrittore v;
  private Expr e;

  public Assign (Descrittore desc, Expr expr){
    this.v = desc;
    this.e = expr;
  }
  
  void generaCodice(Codice c){

  }
}

class Write extends Expr {

  private String output;
  private Expr expr;

  public Write (String s, Expr e){
    this.output = s;
    this.expr = e;
  }

  public Write (String s){
    this.output = s;
  }

  public Write (Expr e){
    this.expr = e;
  }
  
  void generaCodice(Codice c){

  }
}

class Cycle extends Expr {

  private Expr cond;
  private ListExpr seq;

  public Cycle (Expr expr, ListExpr seq){
    this.cond = expr;
    this.seq = seq;  
  }
  
  void generaCodice(Codice c){

  }
}

class NumExpr extends Expr {
  private Integer num;

  public NumExpr(Integer num) {
    this.num = num;
  }

  public void generaCodice(Codice c) {
    c.genera(PUSHIMM, num.intValue());
  }
  
  public String toString() {
    return num.toString();
  }
}

class IdExpr extends Expr {
  private Descrittore descrittore;

  public IdExpr(Descrittore d) {
    descrittore = d;
  }

  public void generaCodice(Codice c) {
    c.genera(PUSH, descrittore.getIndirizzo());
  }

  public String toString() {
    return descrittore.getIdentificatore();
  }
}


class SumExpr extends Expr {
  private Expr sx, dx;

  public PiuExpr(Expr sx, Expr dx) {
    this.sx = sx;
    this.dx = dx;
  }

  public void generaCodice(Codice c) {
    sx.generaCodice(c);
    dx.generaCodice(c);
    c.genera(ADD);
  }

  public String toString() {
    return sx.toString() + " " + dx.toString() + " +";
  }
}


class SubExpr extends Expr {
  private Expr sx, dx;

  public MenoExpr(Expr sx, Expr dx) {
    this.sx = sx;
    this.dx = dx;
  }

  public void generaCodice(Codice c) {
    sx.generaCodice(c);
    dx.generaCodice(c);
    c.genera(SUB);
  }

  public String toString() {
    return sx.toString() + " " + dx.toString() + " -";
  }
}


class MulExpr extends Expr {
  private Expr sx, dx;

  public PerExpr(Expr sx, Expr dx) {
    this.sx = sx;
    this.dx = dx;
  }

  public void generaCodice(Codice c) {
    sx.generaCodice(c);
    dx.generaCodice(c);
    c.genera(MUL);
  }

  public String toString() {
    return sx.toString() + " " + dx.toString() + " *";
  }
}


class DivExpr extends Expr {
  private Expr sx, dx;

  public DivisoExpr(Expr sx, Expr dx) {
    this.sx = sx;
    this.dx = dx;
  }

  public void generaCodice(Codice c) {
    sx.generaCodice(c);
    dx.generaCodice(c);
    c.genera(DIV);
  }

  public String toString() {
    return sx.toString() + " " + dx.toString() + " /";
  }
}

class ModExpr extends Expr {
  private Expr sx, dx;

  public DivisoExpr(Expr sx, Expr dx) {
    this.sx = sx;
    this.dx = dx;
  }

  public void generaCodice(Codice c) {
    sx.generaCodice(c);
    dx.generaCodice(c);
    //c.genera(DIV);
  }

  public String toString() {
    return sx.toString() + " " + dx.toString() + " /";
  }
}


class MinusExpr extends Expr {
  private Expr e;

  public MinusExpr(Expr e) {
    this.e = e;
  }

  public void generaCodice(Codice c) {
    c.genera(PUSHIMM, 0);
    e.generaCodice(c);
    c.genera(SUB);
  }

  public String toString() {
    return e.toString() + "-";
  }
}


class PlusExpr extends Expr {
  private Expr e;

  public PlusExpr(Expr e) {
    this.e = e;
  }

  public void generaCodice(Codice c) {
    e.generaCodice(c);
  }

  public String toString() {
    return e.toString() + "+";
  }
}

class TernaryExpr extends Expr {

  private Expr condition;
  private Expr first;
  private Expr second;

  public TernaryExpr(Expr cond, Expr f, Expr s){
    this.condition = cond;
    this.first = f;
    this.second = s;
  }

  public void generaCodice(Codice c){

  }
}

class InputExpr extends Expr {
  private String message;

  public InputExpr(){

  }

  public InputExpr(String s){
    this.message = s;
  }

  public void generaCodice(Codice c){
    
  }
}
