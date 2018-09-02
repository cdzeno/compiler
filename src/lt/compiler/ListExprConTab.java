class ListExprConTab {

  private ListExpr list;
  private SymbolTable tabel;

  public ExprConTab(ListExpr listExpr, SymbolTable table) {
    this.list = listExpr;
    this.tabel = table;
  }

  public ListExpr getListExpr() {
    return this.list;
  }

  public SymbolTable getSymbolTable() {
    return this.tabel;
  }

}


