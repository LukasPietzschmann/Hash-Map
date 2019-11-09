// Sondierungssequenz gemäß linearer Sondierung.
class LinearProbing extends AbstractHashSequence {
  // Lineare Sondierung mit Streuwertfunktion f.

  private int prev;


  public LinearProbing (HashFunction f) {
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

