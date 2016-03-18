import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;

class iqs{

  private Map<Integer, List<String>> mungMap = new HashMap<Integer, List <String>>();

  private String mungMeta = "";

  private void tokenize(String [] input){

    int i = 0, j = 0;

    String pp = input[i++];
    int srcSize = Integer.parseInt(input[i++]),
        tgtSize = Integer.parseInt(input[i++]);

    List<String> srcList = new ArrayList<String>();
    List<String> tgtList = new ArrayList<String>();

      for( i = i; i < 3 + srcSize; ++i){
        srcList.add(input[i]);
      }
    for(j = i + 1; j < i + 1 + tgtSize; ++j){
      tgtList.add(input[j]);
    }

    mungMeta = input[++j];

    mungMap.put(0, srcList);
    mungMap.put(1, tgtList);
  }

  private void mung(){

    for(int k : mungMap.keySet()){
      System.out.print(mungMap.get(k).size() + " ");
      for(String s : mungMap.get(k))
        System.out.print(s + " ");
    }

    System.out.print(mungMeta + "\n");
  }

  private Stack<String> strStack = new Stack<String>();

  private List<String> substrList = new ArrayList<String>();

  private void readFile(Path path){
    Charset charset = Charset.forName("US-ASCII");

    try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);
        String prev = "";
        while (st.hasMoreTokens()) {
          String token = st.nextToken();
          strStack.push(token + " ");
          if (substrList.isEmpty()){
            substrList.add(token + " " );
          }
          else{
            substrList.add(token + " " );
            substrList.add(prev + token + " " );
            int sz = substrList.size() - 1;  
            for(int i = sz; i >= 0; --i){
              substrList.add(substrList.get(i) + token + " ");
            }
          } 
          prev = token + " ";
        }
      }
    } catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
  }

  private void countGrams(){
    String cur = "", pre = "";
    Map<String, Integer> ngMap = new HashMap<String, Integer>();
    Set<String> unique = new HashSet<String>(substrList);

    for(String s : substrList){
      System.out.println(s);
    }

    int m = 0;

    while(!strStack.isEmpty()){
      String v = strStack.pop();

      cur += v;
      if(unique.contains(v)){
        int freq = Collections.frequency(substrList, v);
        ngMap.put(v, freq);
        if (freq > m) m = freq;
      }
      if(unique.contains(cur)){
        int freq = Collections.frequency(substrList, cur);
        ngMap.put(cur, freq);
        if (freq > m) m = freq;
      }
      if(unique.contains(pre + v)){
        int freq = Collections.frequency(substrList, pre + v);
        ngMap.put(pre + v, freq);
        if (freq > m) m = freq;
      }
      if(unique.contains(v + pre)){
        int freq = Collections.frequency(substrList, v + pre);
        ngMap.put(v + pre, freq);
        if (freq > m) m = freq;
      }
      pre = v;
    }

    Map<Integer, List<String>> omap = new HashMap<Integer, List<String>>();
    for(int i = 2; i < m; ++i)
    {
      List <String> l = new ArrayList<String>();
      omap.put(i, l);
    }
    for(String k : ngMap.keySet()){
      if(ngMap.get(k) > 1)
      {
        for(String s : k.split(" ")){
          String [] sa = k.split(" ");
          int len = sa.length;
          if(len > 1)
            omap.get(len).add(s);
        }
      }
    }
    for (int k : omap.keySet()){
      System.out.print(k + " ");
      for(String v : omap.get(k)){
        System.out.print(v + " ");
      }
      System.out.print("\n");
    }
  }

  public static void main(String [] args){
    iqs munger = new iqs();

    munger.tokenize(args);
    munger.mung();

    Path p = Paths.get("/home/rfoster/temp.txt");
    munger.readFile(p);
    munger.countGrams();
  }
}
