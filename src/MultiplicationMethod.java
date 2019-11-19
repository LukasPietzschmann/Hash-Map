// Streuwertfunktion gemäß Multiplikationsmethode
// (Implementierung mit 32-Bit-Ganzzahlarithmetik).
class MultiplicationMethod extends AbstractHashFunction {
  private static final int w = 32;
  
  // Anzahl p von Bits.
  private final int bits;
  
  // Parameter s = A'.
  private final int seed;
  
  // Multiplikationsmethode für Tabellengröße N = 2 hoch p
  // mit Parameter s.
  public MultiplicationMethod(int p, int s) {
    super(1 << p);    // 1 << p entspricht 2^p.
    bits = p;
    seed = s;
  }
  
  @Override
  public int compute(Object key) {
    final int hash = (key.hashCode() % size + size) % size;
    
    return ((hash * seed) >>> (w - bits)) % size();
  }
}