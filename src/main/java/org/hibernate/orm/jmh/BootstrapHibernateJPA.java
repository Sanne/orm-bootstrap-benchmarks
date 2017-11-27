package org.hibernate.orm.jmh;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BootstrapHibernateJPA {

	@Benchmark
	public void testMethod(EntityManagerHolder state) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "mainPU" );
		state.set( entityManagerFactory );
	}

}
