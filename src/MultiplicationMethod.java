// Streuwertfunktion gemäß Multiplikationsmethode
// (Implementierung mit 32-Bit-Ganzzahlarithmetik).
class MultiplicationMethod extends AbstractHashFunction {
  private static final int w = 32;
  
  // Anzahl p von Bits.
  private int bits;
  
  // Parameter s = A'.
  private int seed;
  
  // Multiplikationsmethode für Tabellengröße N = 2 hoch p
  // mit Parameter s.
  public MultiplicationMethod(int p, int s) {
    super(1 << p);    // 1 << p entspricht 2^p.
    bits = p;
    seed = s;
  }
  
  @Override
  public int compute(Object key) {
    final int hash = Math.abs(key.hashCode());
    
    return ((hash * seed) >>> (w - bits)) % size();
  }
}