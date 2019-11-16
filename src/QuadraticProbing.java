// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).
class QuadraticProbing extends AbstractHashSequence {
  //"Index" in der Hash Sequence
  private int j;
  
  //Ideal bei, wenn N 2er Potentz ist
  // Quadratische Sondierung mit Streuwertfunktion f.
  public QuadraticProbing(HashFunction f) {
    super(f);
  }
  
  @Override
  public int first(Object key) {
    j = 0;
    return prev = func.compute(key);
  }
  
  @Override
  public int next() {
    j += 1;
    return (int) ((prev + (j + Math.pow(j, 2)) / 2) % size());
  }
}