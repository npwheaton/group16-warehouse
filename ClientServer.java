import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ClientServer implements Serializable{
private int idcount;
private static ClientServer server;
private ClientServer(){idcount=1;}
public static ClientServer instance(){
if (server==null)
	{return (server = new ClientServer());}
else
	return server;
}
public int getid(){return idcount++;}
public static void retrieve(ObjectInputStream var0) {
        try {
            server = (ClientServer)var0.readObject();
        } catch (IOException var2) {
            var2.printStackTrace();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private void writeObject(ObjectOutputStream var1) throws IOException {
        try {
            var1.defaultWriteObject();
            var1.writeObject(server);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            var1.defaultReadObject();
            if(server == null) {
                server = (ClientServer)var1.readObject();
            } else {
                var1.readObject();
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

public String toString(){
return " " +  idcount;}

}