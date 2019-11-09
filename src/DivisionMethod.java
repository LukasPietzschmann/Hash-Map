// Streuwertfunktion gemäß Divisionsrestmethode.
class DivisionMethod extends AbstractHashFunction {
  // Divisionsrestmethode für Tabellengröße N.
  public DivisionMethod (int N) {
    super(N);
  }

  //compute berechnet den HashCode für ein Object kex das der Funktion übergeben wird
  //Und gibt den positiven Hashwert der auf 0 bis N-1 abgebildet wird zurück.
  //(Bei h % size lönnten auch negative Zahlen entstehen)
  @Override
  public int compute(Object key) {
    int h = key.hashCode();
    return (h % size + size) % size;
  }
}