package jdepend.framework;

import static java.lang.String.format;

public class ProfilingHarness {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			JDepend jDepend = new JDepend();
			for (String arg : args) {
				jDepend.addDirectory(arg);
			}
			System.out.println(jDepend.countClasses());
		}
		long end = System.currentTimeMillis();
		System.out.println(format("done: %sms", (end - start)));
	}
	
}
