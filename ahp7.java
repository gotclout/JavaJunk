import java.io.*;

class Proj7 {

  public static class Account {
    private int accountNum;
    private String name;
    private double balance;

    public Account(int a, String n, double b) {
      accountNum = a;
      name = n;
      balance = b;
    }

    public int getAccount() {
      return accountNum;
    }

    public String getName() {
      return name;
    }

    public double getBalance() {
      return balance;
    }

    public String toString() {
      String s = accountNum + ": " + name + " " + balance;
      return s;
    }
  }

  public static Account[] list;

  //count is how many accounts we've added so far
  public static int count;
  public static BufferedReader r;

  public static void main(String[] args) throws IOException {
    r = new BufferedReader(new InputStreamReader(System.in));
    list = new Account[10];
    count = 0;

    String command = "";
    while(!command.equals("q")){
      System.out.print("Enter (a)dd, (s)earch by number, (l)ookup number, (r)emove, (p)rint, (q)uit: ");
      command = r.readLine();
      if(command.equals("a")){
        add();
      }
      else if(command.equals("s")){
        search();
      }
      else if(command.equals("l")){
        lookup();
      }
      else if(command.equals("r")){
        remove();
      }
      else if(command.equals("p")){
        print();
      }
    }
    //repeatedly ask user to add, search, lookup, remove, print, or quit
    //call the appropriate method based on the user option
  }

  public static void add() throws IOException {
    String name = "", balance = "", account = "";
    if(count < 10){
      try{
        System.out.print("\nEnter account numer: ");
        account = r.readLine();
        System.out.print("\nEnter name: ");
        name = r.readLine();
        System.out.print("\nEnter balance: ");
        balance = r.readLine();
        Account a = new Account(Integer.parseInt(account), name, Double.parseDouble(balance));
        list[count] = a;
        count++;
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
    //if there is space for another account
    //get the account number, name, and balance from the user
    //create a new Account object and store it in the list array
  }

  public static void search() throws IOException {
    String account = "";
    int i;
    if(count > 0){
      try{
        System.out.print("\nEnter account number: ");
        account = r.readLine();
        for(i = 0; i < 10; i++){
          if(list[i].getAccount() == Integer.parseInt((account))){
            System.out.print("\n" + list[i].getName() + " " + list[i].getBalance() + "\n");
            break;
          }
        }
        if(i >= 10)
          System.out.print("There is no account number " + account + ".\n");
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
    //get the account number from the user
    //find that account in the array, and print its information
    //if you can't find it, print an error
  }

  public static void lookup() throws IOException {
    String name = "";
    int i;
    if(count > 0){
      try{
        System.out.print("\nEnter name: ");
        name = r.readLine();
        for(i = 0; i < 10; i++){
          if(list[i].getName().equals(name)){
            System.out.println("\n" + list[i].getAccount());
            break;
          }
        }
        if(i >= 10)
          System.out.println("There is no account for " + name);
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
    //get the name from the user
    //find that account in the array, and print its account number
    //if you can't find it, print an error
  }

  public static void remove() throws IOException {
    String account = "";
    int i;
    if(count > 0){
      try{
        System.out.print("\nEnter account numer: ");
        account = r.readLine();
        for(i = 0; i < 10; i++){
          if(list[i].getAccount() == Integer.parseInt(account)){
            for(int j = i; j < count-1; j++){
              list[j] = list[j+1];
            }
            count--;
            break;
          }
        }
        if(i >= 10)
          System.out.println("\nThere is no account number " + account);
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
    //get the account number from the user
    //find that account in the array, and "remove" it by shifting all later accounts up one spot to fill the whole
    //if you can't find it, print an error
  }

  //this method is complete
  public static void print() {
    String full = "";
    for (int i = 0; i < count; i++) {
      full += list[i].toString() + "\n";
    }

    System.out.println(full);
  }
}

