// Sondierungssequenz gemäß doppelter Streuung.
class DoubleHashing extends AbstractHashSequence {
  // Zweite Streuwertfunktion.
  private HashFunction func2;
  private Object key;
  private int it = 0;
  
  // Doppelte Streuung mit Streuwertfunktionen f1 und f2.
  public DoubleHashing(HashFunction f1, HashFunction f2) {
    super(f1);
    func2 = f2;
  }
  
  @Override
  public int first(Object key) {
    this.key = key;
    return (func.compute(key) % size());
  }
  
  @Override
  public int next() {
    return (func.compute(key) + ++it * func2.compute(key)) % size();
  }
}