// Implementierung von Streuwerttabellen mit offener Adressierung.
class HashTableOpenAddressing implements HashTable {
  //private static final int contains = -3;
  //private static final int full = -2;
  //private static final int doesntContain = -1;
  //private static final int nothingRemembered = -1;

  private int contains = 0;
  private int full = 0;
  private int doesntContain = 0;
  private int nothingRemembered = -1;

  //contains = -3
  //full = -2
  //doesntcontain -1
  //nothingRemebered = -1

  private HashSequence sequence;
  private Slot[] array;

  // Streuwerttabelle mit Sondierungsfunktion s.
  public HashTableOpenAddressing(HashSequence sequence) {
    this.sequence = sequence;
    this.array = new Entry[sequence.size()];
  }
/*
  //Hilfsoperation
  public int[] helperFunc(Object key) {
    int j = 0;
    int remembered = nothingRemembered;
    //int[] tuple = new int[2];
    //1
    int index = sequence.first(key);
    do {
      //1.2
      if (array[index] == null) {
        //1.2.a
        if (remembered == nothingRemembered) {
          tuple[0] = doesntContain;
          tuple[1] = index;
        } else //1.2.b
        {
          tuple[0] = doesntContain;
          tuple[1] = remembered;
        }

        return tuple;
      }

      //3
      if (array[index] instanceof DelMarker && remembered == nothingRemembered) {
        remembered = index;
      }
      //4
      if ((array[index] != null) && !(array[index] instanceof DelMarker)) {
        tuple[0] = contains;
        tuple[1] = index;

        return tuple;
      }

      j++;
      index = sequence.next();
    } while (j <= sequence.size() - 1);

    //2
    if (remembered >= 0) {
      tuple[0] = doesntContain;
      tuple[1] = remembered;

      return tuple;
    }

    //4
    tuple[0] = full;
    tuple[1] = -2;

    return tuple;
  }
*/


public int helperFunc(Object key){
  int j = 0;
  int remembered = nothingRemembered;
  int index = sequence.first(key);

  do{
    /*
    * Hilfsoperation 1.2 Wenn Tabelle an dee Stelle index leer ist dann liefere
    * "nicht vorhanden" und entweder den gemerkten Wert(falls es einen gibt)
    * oder (andernfalls) den Index i zurück
    */
    if (array[index] == null){
      if (remembered == nothingRemembered) {
        doesntContain = -1;
        return index;
      } else {
        doesntContain = -1;
        return remembered;
      }
    }

    if (array[index] instanceof DelMarker && remembered == nothingRemembered) {
      remembered = index;
    }

    if ((array[index] != null) && !(array[index] instanceof DelMarker)) {
      contains = -3;
      return index;
    }
    ++j;
    index = sequence.next();
  }while(j <=sequence.size()-1);

  //2
  if (remembered >= 0) {
    doesntContain = -1;
    return remembered;
  }

  //4
  full = -2;
  return -4;
}

  @Override
  public boolean put(Object key, Object val) {
    //int[] tuple = helperFunc(key);

    //Wird abgefragt ob key oder val null sind wenn ja wird abgebrochen
    if(key == null || val == null){
      return false;
    }
    //Wenn die Tabelle nicht voll ist dann wird aus key und value ein neues Objekt angelegt. Mit der Helperfunction
    // wird geschaut wo Entry e gespeicher werden soll und dann speicher wir das an der Position.
    if (full >= 0) {
      Entry e = new Entry(key, val);
      int i = helperFunc(e.key);
      array[i] = e;
      return true;
    }
    return false;
  }

  @Override
  //FIXME liefert nicht das richtige zurück (Testcase: "eins" "eins" -> Error)
  public Object get(Object key) {
    int index = helperFunc(key);

    if (index >= 0 && contains == -3) {
      Entry e = (Entry) array[index];
      return e.val;
    }
    return null;
  }

  @Override
  public boolean remove(Object key) {
    return false;
  }

  @Override
  public void dump() {
    System.out.println(array);
  }


  abstract class Slot {
  }

  class DelMarker extends Slot{
  }

  class Entry extends Slot{
    private Object key;
    private Object val;

    public Entry(Object key, Object val) {
      this.key = key;
      this.val = val;
    }
  }


}