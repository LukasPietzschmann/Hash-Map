// Sondierungssequenz gemäß linearer Sondierung.
class LinearProbing extends AbstractHashSequence {
  // Lineare Sondierung mit Streuwertfunktion f.
  public LinearProbing (HashFunction f) {
    super(f);
  }
}