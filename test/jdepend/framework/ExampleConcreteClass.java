package jdepend.framework;

import java.math.BigDecimal;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class ExampleConcreteClass extends ExampleAbstractClass {

    private java.sql.Statement[] statements;

    public ExampleConcreteClass() {
    }

    public void a() {
        try {
            java.net.URL url = new java.net.URL("http://www.clarkware.com");
        } catch (Exception e) {
        }
    }

    public java.util.Vector b(String[] s, java.text.NumberFormat nf) {
        return null;
    }

    public void c(BigDecimal bd, byte[] bytes) throws java.rmi.RemoteException {
        int[] a = { 1, 2, 3};
        int[][] b = { { 1, 2}, { 3, 4}, { 5, 6}};
    }

    public java.io.File[] d() throws java.io.IOException {
        java.util.jar.JarFile[] files = new java.util.jar.JarFile[1];
        return new java.io.File[10];
    }

    public java.lang.String[] e() {
        java.lang.String[] strings = new java.lang.String[1];
        return strings;
    }

    public class ExampleInnerClass {
    }
}

class ExamplePackageClass {
}