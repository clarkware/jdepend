package jdepend.framework;

import java.util.Collection;
import java.util.Iterator;

/**
 * The <code>DependencyPrescription</code> class is a constraint that tests
 * whether two package-dependency graphs are equivalent.
 * <p>
 * This class is useful for writing package dependency assertions (e.g. JUnit).
 * For example, the following JUnit test will ensure that the 'ejb' and 'web'
 * packages only depend upon the 'util' package, and no others:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * public void testDependencyConstraint() {
 * 
 *     JDepend jdepend = new JDepend();
 *     jdepend.addDirectory(&quot;/path/to/classes&quot;);
 *     Collection analyzedPackages = jdepend.analyze();
 * 
 *     DependencyPrescription constraint = new DependencyPrescription();
 * 
 *     JavaPackage ejb = constraint.addPackage(&quot;com.xyz.ejb&quot;);
 *     JavaPackage web = constraint.addPackage(&quot;com.xyz.web&quot;);
 *     JavaPackage util = constraint.addPackage(&quot;com.xyz.util&quot;);
 * 
 *     ejb.dependsUpon(util);
 *     web.dependsUpon(util);
 * 
 *     assertEquals(&quot;Dependency mismatch&quot;, true, constraint
 *             .followsDirective(analyzedPackages));
 * }
 * </pre>
 * 
 * </blockquote>
 * </p>
 * 
 * @author <b>Mike Clark</b> 
 * @author Clarkware Consulting, Inc.
 */
public class DependencyPrescription extends DependencyDirective {

	/**
	 * Indicates whether the specified packages match the packages in this
	 * prescription.
	 * 
	 * @return <code>true</code> if the packages match this constraint
	 */
	@Override
	public boolean followsDirective(Collection<JavaPackage> packages) {
		if (packages.size() == packages.size()) {

			for (Iterator<JavaPackage> i = packages.iterator(); i.hasNext();) {
				Object next = i.next();
				JavaPackage nextPackage = (JavaPackage) next;
				if (!matchPackage(nextPackage)) {
					return false;
				}

				return true;
			}
		}

		return false;
	}

	private boolean matchPackage(JavaPackage expectedPackage) {

		JavaPackage actualPackage = (JavaPackage) packages.get(expectedPackage
				.getName());

		if (actualPackage != null) {
			if (equalsDependencies(actualPackage, expectedPackage)) {
				return true;
			}
		}

		return false;
	}

	private boolean equalsDependencies(JavaPackage a, JavaPackage b) {
		return equalsAfferents(a, b) && equalsEfferents(a, b);
	}

	private boolean equalsAfferents(JavaPackage a, JavaPackage b) {

		if (a.equals(b)) {

			Collection<JavaPackage> otherAfferents = b.getAfferents();

			if (a.getAfferents().size() == otherAfferents.size()) {
				for (Iterator<JavaPackage> i = a.getAfferents().iterator(); i.hasNext();) {
					JavaPackage afferent = (JavaPackage) i.next();
					if (!otherAfferents.contains(afferent)) {
						return false;
					}
				}

				return true;
			}
		}

		return false;
	}

	private boolean equalsEfferents(JavaPackage a, JavaPackage b) {

		if (a.equals(b)) {

			Collection<JavaPackage> otherEfferents = b.getEfferents();

			if (a.getEfferents().size() == otherEfferents.size()) {
				for (Iterator<JavaPackage> i = a.getEfferents().iterator(); i.hasNext();) {
					JavaPackage efferent = (JavaPackage) i.next();
					if (!otherEfferents.contains(efferent)) {
						return false;
					}
				}

				return true;
			}
		}

		return false;
	}
}
