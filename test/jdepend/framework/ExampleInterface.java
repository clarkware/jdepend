package jdepend.framework;

import java.math.BigDecimal;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public interface ExampleInterface {

    public void a();

    public java.util.Vector b(String[] s, java.text.NumberFormat nf);

    public void c(BigDecimal bd, byte[] b) throws java.rmi.RemoteException;

    public java.io.File[] d() throws java.io.IOException;

}