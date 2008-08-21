package jdepend.framework;

import java.math.BigDecimal;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public abstract class ExampleAbstractClass 
    implements ExampleInterface, java.io.Serializable {

    private java.lang.reflect.Method method;

    public ExampleAbstractClass() {
    }

    public void a() {
        java.net.URL url;
    }

    public java.util.Vector b(String[] s, java.text.NumberFormat nf) {
        return null;
    }

    public void c(BigDecimal bd, byte[] b) throws java.rmi.RemoteException {

    }

    public abstract java.io.File[] d() throws java.io.IOException;
}