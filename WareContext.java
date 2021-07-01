

public class WareContext{
  private int currentState;
  private static Warehouse warehouse;
  private static WareContext context;
  private int currentUser;
  private String ClientID;
  public static final int IsClerk = 0;
  public static final int IsClient = 1;
  public static final int IsManager= 2;
  private WareState[] states;
  private int[][] nextState;
  
  private WareContext() {
    retrieve();
    warehouse = Warehouse.instance();
    states = new WareState[4];
    states[0] = Clerkstate.instance();
    states[1] = Clientstate.instance(); 
    states[2] = Managerstate.instance();
    states[3] = Loginstate.instance();
    nextState = new int[4][4];
    nextState[0][0] = 3;nextState[0][1] = 1;nextState[0][2] = 2;nextState[0][3] = 3;
    nextState[1][0] = 0;nextState[1][1] = 0;nextState[1][2] = -2;nextState[1][3] = 3;
    nextState[2][0] = 0;nextState[2][1] = -2;nextState[2][2] = 2;nextState[2][3] = 3;
	nextState[3][0] = 0;nextState[3][1] = 1;nextState[3][2] = 2;nextState[3][3] = -1;
    currentState = 3;
  }
  public static WareContext instance(){
    if (context == null) 
	  {context = new WareContext();}
    return context;
  }
  public void setLogin(int code){currentUser = code;}
  
  public void setClient(String uID){ClientID = uID;}
  
  public int getLogin(){return currentUser;}
  
  public String getClient(){return ClientID;}
  
  private void terminate(){
      if (warehouse.save()) {
         System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
       } else {
         System.out.println(" There has been an error in saving \n" );
       }
	System.out.println(" Goodbye \n "); System.exit(0);
     }
  
  public void changeState(int transition)
 {currentState = nextState[currentState][transition];
    if (currentState == -2) 
      {System.out.println("Error has occurred"); terminate();}
    if (currentState == -1) 
	  {terminate();}
    states[currentState].run();
  }
  
  public void process(){states[currentState].run();}
 
private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  public static void main (String[] args){
    WareContext.instance().process(); 
  }


}

