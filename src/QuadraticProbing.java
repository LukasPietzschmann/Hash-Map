// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).
class QuadraticProbing extends AbstractHashSequence {
  //"Index" in der Hash Sequence
  private int j = 0;
  
  // Quadratische Sondierung mit Streuwertfunktion f.
  public QuadraticProbing(HashFunction f) {
    super(f);
  }
  
  @Override
  public int first(Object key) {
    return prev = func.compute(key);
  }
  
  @Override
  public int next() {
    j++;
    return (prev + j) % size();
  }
}