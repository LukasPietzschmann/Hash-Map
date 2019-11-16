import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PointTest {
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
      String arr[] = line.split(" ");
      
      if (arr[0].equals("dump")) tab.dump();
      else if (arr[0].equals("+")) {
        Point p = new Point(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        Integer count = (Integer) tab.get(p);
        if(count == null) tab.put(p, 1);
        else tab.put(p, count+1);
      }else if(arr[0].equals("-")){
        Point p = new Point(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        tab.remove(p);
      }else System.out.println("Du depp hasch was falsches eingegeben");
    }
  }
}

class Point {
  private int hash = -1;
  
  private int x;
  private int y;
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return x;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Point)) return false;
    Point p = (Point) obj;
    return (p.x == this.x && p.y == this.y);
  }
  
  @Override
  public int hashCode() {
    if (hash == -1) hash = x + y;
    return hash;
  }
  
  @Override
  public String toString() {
    return String.format("(%d / %d)", x, y);
  }
}
