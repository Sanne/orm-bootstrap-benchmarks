package org.hibernate.orm.jmh;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Launch with: -XX:MetaspaceSize=80M -Xmx1G
public class StandardLaunch {

	public static void main(String[] args) throws IOException {
		EntityManagerFactory entityManagerFactory = null;
		try {
			final long start = System.currentTimeMillis();
			entityManagerFactory = Persistence.createEntityManagerFactory( "mainPU" );
			final long end = System.currentTimeMillis();
			System.out.println( "Took number of wall clock milliseconds to start: " + (end-start) );
			final int read = System.in.read();
		}
		finally {
			if ( entityManagerFactory != null ) {
				entityManagerFactory.close();
			}
		}
	}

}
