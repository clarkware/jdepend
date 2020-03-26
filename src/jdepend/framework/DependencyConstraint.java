package jdepend.framework;

import java.util.*;

import jdepend.framework.JavaPackage;

/**
 * This class has been replaced by DependencyPrescription. 
 * <p>
 * @deprecated use DependencyPrescription instead.
 */
public class DependencyConstraint {

	private DependencyPrescription prescription = new DependencyPrescription();
	
    public JavaPackage addPackage(String packageName) {
        return prescription.addPackage(packageName);
    }

    public void addPackage(JavaPackage jPackage) {
    	prescription.addPackage(jPackage);
    }

    public Collection<JavaPackage> getPackages() {
        return prescription.getPackages();
    }

    public boolean match(Collection<JavaPackage> expectedPackages) {
    	return prescription.followsDirective(expectedPackages);
    }
}