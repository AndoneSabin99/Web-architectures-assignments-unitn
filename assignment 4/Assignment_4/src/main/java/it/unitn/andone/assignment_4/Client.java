package it.unitn.andone.assignment_4;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import it.unitn.andone.assignment_4.StudentBeanIF;
import it.unitn.andone.assignment_4.StudentBean;

public class Client {
    public static void main(String[] args) throws Exception {
        remoteEJB();
    }
    private static void remoteEJB() throws NamingException {
        //lookup the remote stateless bean
        final StudentBeanIF student = lookupStudent();
        String name = student.getName("231234");
        System.out.println(name);
    }
    private static StudentBeanIF lookupStudent() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        if(Boolean.getBoolean("http")) {
            //use HTTP based invocation. Each invocation will be a HTTP request
            jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");
        } else {
            //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
        }
        final Context context = new InitialContext(jndiProperties);
        //do the lookup
        return (StudentBeanIF) context.lookup("ejb:/Assignment_4-1.0-SNAPSHOT/StudentBean!it.unitn.andone.assignment_4.StudentBeanIF");
    }
}
