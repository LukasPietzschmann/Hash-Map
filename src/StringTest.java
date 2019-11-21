import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("ALL")
public class StringTest {
  public static void main(String[] args) throws IOException {
    int Np = Integer.parseInt(args[2]);
    
    HashFunction f;
    switch (args[1]) {
      case "d":
        f = new DivisionMethod(Np);
        break;
      case "m":
        int s = Integer.parseInt(args[3]);
        f = new MultiplicationMethod(Np, s);
        break;
      default:
        return;
    }
    
    HashTable tab;
    if (args[0].equals("c")) {
      tab = new HashTableChaining(f);
    }else {
      HashSequence s;
      switch (args[0]) {
        case "l":
          s = new LinearProbing(f);
          break;
        case "q":
          s = new QuadraticProbing(f);
          break;
        case "d":
          class HashFunction2 extends AbstractHashFunction {
            public HashFunction2(int N) {
              super(N);
            }
            
            public int compute(Object key) {
              int h = Math.abs(key.hashCode()) % (size - 1);
              if (h % 2 == 0) h++;
              return h;
            }
          }
          s = new DoubleHashing(f, new HashFunction2(f.size()));
          break;
        default:
          return;
      }
      tab = new HashTableOpenAddressing(s);
    }
    
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String line = bf.readLine();
      if (line == null) break;
      String[] arr = line.split(" ");
      
      if (arr[0].equals("dump")) tab.dump();
      else if (arr[0].equals("+")) {
        for (int i = 1; i < arr.length; i++) {
          Integer val = (Integer) tab.get(arr[i]);
          if (val == null) tab.put(arr[i], 1);
          else tab.put(arr[i], val + 1);
        }
      }else if (arr[0].equals("-")) {
        for (int i = 1; i < arr.length; i++) tab.remove(arr[i]);
      }else System.out.println("Du depp hasch was falsches eingegeben");
    }
  }
}
