import java.util.Random;

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
  public MultiplicationMethod (int p, int s) {
    super(1 << p);	// 1 << p entspricht 2 hoch p.
    bits = p;
    seed = s;
  }
  
  /*public static void main(String[] args) {
    MultiplicationMethod mm = new MultiplicationMethod(32, 29);
    System.out.println(new Integer(567).hashCode());
    System.out.println(mm.compute(567));
  }*/
  
  @Override
  public int compute(Object key) {
    Random rnd = new Random();
    final int hash = Math.abs(key.hashCode());
    final int A = rnd.nextInt((int) Math.pow(2, w) +1) - 1;
    final int AS = A * (int) Math.pow(2, w);
    
    return (hash * AS) >>> (w - bits);
  }
}