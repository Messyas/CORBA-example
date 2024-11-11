import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;

class HelloImpl extends HelloPOA {
    private ORB orb;

    public void setORB(ORB orb) {
        this.orb = orb;
    }

    @Override
    public String sayHello() {
        return "Hello, CORBA!";
    }
}

public class HelloServer {
    public static void main(String[] args) {
        try {
            // Configura o ORB com as propriedades para o serviço de nomes
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            ORB orb = ORB.init(args, props);

            // Configuração do RootPOA e ativa o POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Resto da configuração do servidor
            HelloImpl helloImpl = new HelloImpl();
            helloImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            Hello href = HelloHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("Hello");
            ncRef.rebind(path, href);

            System.out.println("HelloServer pronto e esperando...");
            orb.run();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("Servidor encerrado.");
    }
}

