package com.billdiary.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;

//public class GenericDAOImpl extends JpaDaoSupport implements GenericDAO{
public class GenericDAOImpl  implements GenericDAO {
	
	
	
	/**
	 * Holds Logger for logging purpose.
	 */
	//private static final Logger LOGGER = Logger.getLogger(GenericDAOImpl.class);

	/**
	 * Constant for unchecked.
	 */
	private static final String UNCHECKED = "unchecked";

	/**
	 * Constant for rawtypes.
	 */
	private static final String RAW_TYPES = "rawtypes";
	
	/**
	 * Constant for batchSize.
	 */
	private static final Integer batchSize = 10;
	
	@SuppressWarnings({ UNCHECKED, RAW_TYPES })
	@Override
	public <T extends Object> List<T> findEntitiesByNamedQuery(final String queryName,
			final Map<Integer, Object> paramMap) {
		/*
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityManager) {
				LOGGER.debug("Entering findEntitiesByNamedQuery Method");
				final Query query = entityManager.createNamedQuery(queryName);
				if (paramMap != null) {
					for (Map.Entry<Integer, Object> entry : paramMap.entrySet()) {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				LOGGER.debug("Exiting findEntitiesByNamedQuery Method");
				return query.getResultList();
			}
		});*/
		return null;
	}

	@SuppressWarnings({ UNCHECKED, RAW_TYPES })
	@Override
	public <T extends Object> List<T> callStoredProc(final String queryName,
			final Map<String, Object> paramMap) {
		/*
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityManager) {
				LOGGER.debug("Entering callStoredProc Method");
				Query query = entityManager.createNamedQuery(queryName);
				if (paramMap != null) {
					for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				// get result
				final List<T> result = query.getResultList();
				LOGGER.debug("Exiting callStoredProc Method");
				return result;
			}
			
		});*/return null;
	}
	
	@SuppressWarnings({ UNCHECKED, RAW_TYPES })
	@Override
	public Integer executeNativeSQLQuery(final String nativeSQLQuery) {
		/*
		return (Integer) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em) {
				final Query q = em.createNativeQuery(nativeSQLQuery);
				return q.executeUpdate();
			}
		});*/return null;
	}

	@SuppressWarnings({ UNCHECKED, RAW_TYPES })
	@Override
	public <T extends Serializable> Collection<T> bulkInsert(final Collection<T> entities) {
		/*
		return (Collection<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em){
				LOGGER.debug("Entering bulkInsert Method");
				final List<T> savedEntities = new ArrayList<T>(entities.size());
				int i = 0;
				for(T entity : entities){
					em.persist(entity);
					savedEntities.add(entity);
					i++;
					if(i % batchSize == 0){
						em.flush();
						em.clear();
					}
				}
				em.flush();
				em.clear();
				LOGGER.debug("Exiting bulkInsert Method");
				return savedEntities;
			}
		});*/return null;
	}

	@Override
	public Integer deleteEntititesByNamedQuery(final String namedQuery, final Map<Integer, Object> paramMap) {
		/*
		return (Integer) getJpaTemplate().execute(new JpaCallback<Object>() {
			public Object doInJpa(final EntityManager entityManager) {
				int count = 0;
				final Query query = entityManager.createNamedQuery(namedQuery);
				for (Map.Entry<Integer, Object> entry : paramMap.entrySet()) {
					query.setParameter(entry.getKey(), entry.getValue());
				}
				count = query.executeUpdate();
				entityManager.flush();
				return count;
			}
		});*/return null;
	}

	@SuppressWarnings({ UNCHECKED, RAW_TYPES })
	@Override
	public <T extends Object> List<T> callStoredProcForProdUpdate(final String queryName,
			final Map<String, String> paramMap) {
		/*
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityManager) {
				LOGGER.debug("Entering callStoredProc Method");
				Query query = entityManager.createNamedQuery(queryName);
				if (paramMap != null) {
					for (Map.Entry<String, String> entry : paramMap.entrySet()) {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				// get result
				final List<T> result = query.getResultList();
				LOGGER.debug("Exiting callStoredProc Method");
				return result;
			}
		});*/return null;
	}

}
