import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.Properties;

public class HelloClient {
    public static void main(String[] args) {
        try {
            // Configura o ORB com as propriedades para o serviço de nomes
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);

            // Obtém a referência do serviço de nomes
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Procura a referência do objeto Hello
            Hello helloRef = HelloHelper.narrow(ncRef.resolve_str("Hello"));
            System.out.println("Resposta do Servidor: " + helloRef.sayHello());
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            e.printStackTrace(System.out);
        }
    }
}

