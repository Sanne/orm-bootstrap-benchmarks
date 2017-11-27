/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.orm.jmh;

import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManagerFactory;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

/**
 * This benchmark state helps to ensure we shut down the Hibernate instance
 * after measuring its initialization.
 * We add some safety logic to shield against illegal benchmark configurations
 * which technically would introduce noise in a micro benchmark but the
 * should be negligible compared to the amount of code being measured
 * (booting Hibernate is not really needing a micro-benchmark.. yet).
 */
@State(Scope.Benchmark)
public class EntityManagerHolder {

	private final AtomicReference<EntityManagerFactory> emfReference = new AtomicReference<>();

	public void set(final EntityManagerFactory em) {
		if ( emfReference.compareAndSet( null, em ) == false ) {
			em.close();
			throw new RuntimeException( "Wrong benchmark settings detected: "
					+ "Previously set EntityManagerFactory reference was not cleaned up!" );
		}
	}

	@TearDown(Level.Invocation)
	public void cleanup() {
		EntityManagerFactory factory = emfReference.getAndSet( null );
		if ( factory != null ) {
			factory.close();
		}
		else {
			System.err.println( "Cleanup invoked with no EntityManagerFactory created? Something is wrong." );
		}
	}

}
