import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;


public class ClientList implements Serializable {
private static final long serialVersionUID = 1L;
private LinkedList<Client> clients = new LinkedList<Client>();
private static ClientList clist;
private ClientList() {}
public static ClientList instance() {
if (clist == null) 
	{return (clist = new ClientList());} 
else 
	{return clist;}
}
public boolean insertClient(Client c){
clients.add(c);
return true;
}
public Iterator<Client> getClients(){return clients.iterator();}
public Client findClient(String clientId) {
	for (Iterator<Client> all = clients.iterator(); all.hasNext(); ) {
      Client cl = (Client) all.next();
      if (cl.getid().equals(clientId)) {
        return cl;
      }
    } return null;
} 
private void writeObject(ObjectOutputStream var1) {
        try {
            var1.defaultWriteObject();
            var1.writeObject(clist);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private void readObject(ObjectInputStream var1) {
        try {
            if(clist != null) {
                return;
            }

            var1.defaultReadObject();
            if(clist == null) {
                clist = (ClientList)var1.readObject();
            } else {
                var1.readObject();
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

    }

public String toString() {return clients.toString();}
 }