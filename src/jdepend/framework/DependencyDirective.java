package jdepend.framework;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A <code>DependencyDirective</code> represents a set of dependency
 * instructions to which analysed code can be compared.
 * <p>
 * Concrete subclasses implement the comparing functionality.
 * 
 * @author Tom van den Berge
 */
public abstract class DependencyDirective {

    protected Map<String, JavaPackage> packages = new HashMap<String, JavaPackage>();

    /**
	 * Adds the specified package to this directive. The returned JavaPackage
	 * can be used to couple to other packages.
	 * 
	 * @param packageName
	 *            the name of the package.
	 * @return the added package.
	 */
    public JavaPackage addPackage(String packageName) {
        JavaPackage jPackage = (JavaPackage) packages.get(packageName);
        if (jPackage == null) {
            jPackage = new JavaPackage(packageName);
            addPackage(jPackage);
        }
        return jPackage;
    }

    /**
     * Adds the specified package to this directive.
     * 
     * @param jPackage the package to add.
     */
    public void addPackage(JavaPackage jPackage) {
        if (!packages.containsValue(jPackage)) {
            packages.put(jPackage.getName(), jPackage);
        }
    }

	/**
	 * Returns all configured packages of this directive.
	 * 
	 * @return the packages.
	 */
    public Collection<JavaPackage> getPackages() {
        return packages.values();
    }

    /**
     * Returns true if the specified packages follow this directive.
     * 
     * @param packages the packages to verify against this directive.
     * @return true if the packages follow this directive, otherwise false.
     */
    public abstract boolean followsDirective(Collection<JavaPackage> packages);
}
