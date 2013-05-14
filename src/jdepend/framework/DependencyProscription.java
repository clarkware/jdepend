package jdepend.framework;

import java.util.Collection;
import java.util.HashSet;

/**
 * The <code>DependencyProscription</code> defines a set of disallowed package
 * dependencies. It can be used to verify that a set of packages do <em>not</em>
 * have any unwanted dependencies.
 * 
 * @author Tom van den Berge.
 */
public class DependencyProscription extends DependencyDirective {

	@Override
	public boolean followsDirective(Collection<JavaPackage> packages) {
		for (JavaPackage pckg : packages) {
			if (hasDisallowedCouplings(pckg)) {
				return false;
			}
		}
		return true;
	}

	private boolean hasDisallowedCouplings(JavaPackage verifiedPackage) {
		JavaPackage restrictedPackage = packages.get(verifiedPackage.getName());

		if (restrictedPackage == null) {
			return false;
		}
		
		if (hasDisallowedAfferents(restrictedPackage, verifiedPackage)) {
			return true;
		}

		if (hasDisallowedEfferents(restrictedPackage, verifiedPackage)) {
			return true;
		}

		return false;
	}
	
	private boolean hasDisallowedAfferents(JavaPackage restrictedPackage, JavaPackage verifiedPackage) {
		final Collection<JavaPackage> disallowedAfferents = restrictedPackage
				.getAfferents();
		final Collection<JavaPackage> actualAfferents = new HashSet<JavaPackage>(
				verifiedPackage.getAfferents());
		actualAfferents.retainAll(disallowedAfferents);
		return !actualAfferents.isEmpty();
	}
	
	private boolean hasDisallowedEfferents(JavaPackage restrictedPackage, JavaPackage verifiedPackage) {
		final Collection<JavaPackage> disallowedEfferents = restrictedPackage
				.getEfferents();
		final Collection<JavaPackage> actualEfferents = new HashSet<JavaPackage>(
				verifiedPackage.getEfferents());
		actualEfferents.retainAll(disallowedEfferents);
		return !actualEfferents.isEmpty();
	}
}
