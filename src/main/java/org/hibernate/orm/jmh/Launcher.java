/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.orm.jmh;

import org.openjdk.jmh.profile.ProfilerException;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Helper to launch the benchmark from the IDE.
 * Only handy for development time; see notes about running from IDEs:
 *  - http://openjdk.java.net/projects/code-tools/jmh/
 */
public class Launcher {

	public static void main(String[] args) throws RunnerException, ProfilerException {
		Options opt = new OptionsBuilder()
				.include( BootstrapHibernateJPA.class.getSimpleName() )
				.jvmArgs( "-XX:MetaspaceSize=80M", "-Xmx1G" )
				.addProfiler( org.openjdk.jmh.profile.ClassloaderProfiler.class )
//				.addProfiler( org.openjdk.jmh.profile.SafepointsProfiler.class )
				.measurementIterations( 1 )
				.forks( 10 )
				.build();

		new Runner( opt ).run();
	}

}
