// Sondierungssequenz gemäß quadratischer Sondierung
// (Implementierung nur mit Ganzzahlarithmetik).
class QuadraticProbing extends AbstractHashSequence {
  // Quadratische Sondierung mit Streuwertfunktion f.
  public QuadraticProbing (HashFunction f) {
    super(f);
  }
}