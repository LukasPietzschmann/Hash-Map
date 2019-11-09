// Implementierung von Streuwerttabellen mit Verkettung.
class HashTableChaining implements HashTable {
  private ChainedList[] arr;
  private HashFunction func;
  
  // Streuwerttabelle mit Streuwertfunktion f.
  public HashTableChaining (HashFunction f) {
    arr = new ChainedList[f.size()];
    func = f;
  }
  
  @Override
  public boolean put(Object key, Object val) {
    if(key == null || val == null) return false;
    
    int i = func.compute(key);
    if(arr[i] == null) {
      arr[i] = new ChainedList(key, val);
      return true;
    }
    
    ChainedList.ListElement searchRes = arr[i].search(key);
    
    if(searchRes == null){
      arr[i].add(key, val);
      return true;
    }
    
    searchRes.setVal(val);
    return true;
  }
  
  @Override
  public Object get(Object key) {
    if(key == null) return null;
    
    int i = func.compute(key);
    
    return arr[i].search(key);
  }
  
  @Override
  public boolean remove(Object key) {
    if(key == null) return false;
    
    int i = func.compute(key);
    
    if (arr[i] == null) return false;
    
    return arr[i].remove(key);
  }
  
  @Override
  public void dump() {
    for (ChainedList list : arr) {
      if(list != null){
      
      }
    }
  }
}

class ChainedList{
  private ListElement first;
  
  public ChainedList(Object firstKey, Object firstVal) {
    this.first = new ListElement(null, firstKey, firstVal);
  }
  
  public void add(Object key, Object val){
    first = new ListElement(first, key, val);
  }
  
  public boolean remove(Object key){
    if(first.key.equals(key)){
      first = first.next;
      return true;
    }
  
    ListElement itElem = first;
    while (itElem.next != null && !itElem.next.key.equals(key)){
      itElem = itElem.next;
    }
    
    if(itElem == null) return false;
    
    itElem.next = itElem.next.next;
    return true;
  }
  
  public ListElement search(Object key){
    return first.search(key);
  }
  
  public String dump(int index){
    return first.dump(index);
  }
  
  class ListElement{
    private ListElement next;
    private Object key;
    private Object val;
    
  
    public ListElement(ListElement next, Object key, Object val) {
      this.next = next;
      this.key = key;
      this.val = val;
    }
  
    public void setVal(Object val) {
      this.val = val;
    }
  
    public Object getKey(){
      return key;
    }
  
    public Object getVal() {
      return val;
    }
  
    public ListElement getNext(){
      return next;
    }
    
    public ListElement search(Object key){
      if(this.key.equals(key)) {
        return this;
      }
      if (next == null) return null;
      return next.search(key);
    }
    
    public String dump(int index){
      if(next == null) return "";
      String out = String.format("%d %d %d\n", index, key, val);
      return out += next.dump(index);
    }
  }
}