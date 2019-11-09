// Implementierung von Streuwerttabellen mit offener Adressierung.
class HashTableOpenAddressing implements HashTable {
  private static final int contains = -3;
  private static final int full = -2;
  private static final int doesntContain = -1;
  private static final int nothingRemembered = -1;

  private HashSequence sequence;
  private Object[] array;

  // Streuwerttabelle mit Sondierungsfunktion s.
  public HashTableOpenAddressing (HashSequence sequence) {
    this.sequence = sequence;
    this.array = new Entry[sequence.size()];
  }

  public int[] helperFunc(Object key)
  {
    //signal
    //-1 nicht vorhanden
    //-2 liste voll
    //-3 vorhanden

    int signal = 0;
    int remembered = nothingRemembered;
    int[] tuple = new int[2];

    //1
    int index = sequence.first(key);
    do {
      //1.2
      if(array[index] == null)
      {
        //1.2.a
        if(remembered == nothingRemembered)
        {
          tuple[0] = doesntContain;
          tuple[1] = index;
        }
        else //1.2.b
        {
          tuple[0] = doesntContain;
          tuple[1] = remembered;
        }

        return tuple;
      }

      //3
      if(array[index] instanceof DelMarker && remembered == nothingRemembered)
      {
        remembered = index;
      }
      //4
      if((array[index] != null) && !(array[index] instanceof DelMarker))
      {
        tuple[0] = contains;
        tuple[1] = index;

        return tuple;
      }

      index = sequence.next();
    }while(signal >= 0);

    //2
    if(remembered >= 0)
    {
      tuple[0] = doesntContain;
      tuple[1] = remembered;

      return  tuple;
    }

    //4
    tuple[0] = full;
    tuple[1] = -2;

    return tuple;
  }

  @Override
  public boolean put(Object key, Object val) {
    int[] tuple = helperFunc(key);
    if(tuple[1] >= 0)
    {
      Entry e = new Entry(key, val);
      array[tuple[1]] = e;
      return true;
    }
    return false;
  }

  @Override
  public Object get(Object key) {
    int[] tuple = helperFunc(key);
    if(tuple[0] == -3)
    {
      return array[tuple[1]];
    }
    return null;
  }

  @Override
  public boolean remove(Object key) {
    return false;
  }

  @Override
  public void dump() {

  }
}