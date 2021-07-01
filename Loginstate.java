

public class Loginstate extends WareState{
  private loginGUI gui;
  private static final int CLERK_LOGIN = 0;
  private static final int CLIENT_LOGIN = 1;
  private static final int MANAGER_LOGIN = 2;
  private static final int EXIT = 3;  
  private static Loginstate instance;
  private Loginstate() {
      super();
      gui = new loginGUI();
  }

  public static Loginstate instance() {
    if (instance == null) {
      instance = new Loginstate();
    }
    return instance;
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getStringAnswer("Input 0 to login as Clerk\n"+ 
                        			"Input 1 to login as client\n" +
                        			"Input 2 to login as manager\n"+
						"Input 3 to exit the system\n" ));
        if (value <= EXIT && value >= CLERK_LOGIN) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        gui.output("Enter a number");
      }
    } while (true);
  }

  public String getStringAnswer(String prompt) {
    String line = "no";
try {
        line = gui.input(prompt);
       } catch (Exception e) 
 {System.exit(0);}
return line;
}
 
  
    public void clerk(){
    String clerkPIN = getStringAnswer("Please input the clerk PIN(PIN:clerk): ");
    if (clerkPIN.equals("clerk")){
	(WareContext.instance()).setLogin(WareContext.IsClerk);
    (WareContext.instance()).changeState(0);
	}
	else{
	  gui.output("Invalid clerk PIN.");
	}
  }

  public void client(){
    String clientID = getStringAnswer("Please input the client id: ");
    Client cl = Warehouse.instance().verifyclient(clientID);
    if (cl != null){
      (WareContext.instance()).setLogin(WareContext.IsClient);
      (WareContext.instance()).setClient(clientID);      
      (WareContext.instance()).changeState(1);
    }
    else 
      gui.output("Invalid client id.");
  } 
  public void manager(){
    String managerPIN = getStringAnswer("Please input the manager PIN(PIN:manager): ");
    if (managerPIN.equals("manager")){
      (WareContext.instance()).setLogin(WareContext.IsManager);
      (WareContext.instance()).changeState(2);
    }
    else 
      gui.output("Invalid manager id.");
  }
  public void exit(){(WareContext.instance()).changeState(3);}
  public void process() {
    gui.displayB();
  }

  public void run() {
    process();
  }
}

