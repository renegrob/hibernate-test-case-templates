package org.hibernate.bugs;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.bugs.entities.PrincipalEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Deletion with JPQL of an Entity containing an @ElementCollection
 */
public class ElementCollectionDeletionJPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	@Test
	public void hhhElementCollectionDeletionTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			// Create and save entity
			entityManager.getTransaction().begin();

			Map<String, String> aliases = new HashMap<>();
			aliases.put("email", "john.doe@example.com");
			aliases.put("github", "johndoe.github.io");

			PrincipalEntity entity = new PrincipalEntity();
			final long id = 100L;
			entity.setId(id);
			entity.setName("JDOE");
			entity.setAliases(aliases);
			entityManager.persist(entity);

			entityManager.getTransaction().commit();

			// Delete entity
			entityManager.getTransaction().begin();

			entityManager.createQuery("DELETE FROM PrincipalEntity p WHERE p.id = :id")
					.setParameter("id", id)
					.executeUpdate();

			entityManager.getTransaction().commit();

		} finally {
			entityManager.close();
		}
	}
}
