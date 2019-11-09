// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).
class QuadraticProbing extends AbstractHashSequence {

  private static int j;
  private int first;
  // Quadratische Sondierung mit Streuwertfunktion f.
  public QuadraticProbing (HashFunction f) {
    super(f);
    j = 0;
  }

  @Override
  public int first(Object key) {
    return prev= func.compute(key);
  }

  @Override
  public int next() {
    j = j + 1;
    return (prev + j) % size();
  }
}