// Sondierungssequenz gemäß linearer Sondierung.
class LinearProbing extends AbstractHashSequence {
  //Wert den next() als letztes zurückgegeben hatte
  private int prev;
  
  // Lineare Sondierung mit Streuwertfunktion f.
  public LinearProbing(HashFunction f) {
    super(f);
  }
  
  @Override
  public int first(Object key) {
    return prev = func.compute(key);
  }
  
  @Override
  public int next() {
    return prev = (prev + 1) % size();
  }
}