package com.billdiary.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GenericDAO {
	
	/**
	 * Store the entity into the database.
	 * 
	 * @param toStore
	 *            return
	 * @param <T>
	 *            generic Entity
	 * @return attached entity that is stored in the database.
	 *//*
	<T extends Serializable> T store(final T toStore);

	*//**
	 * Fetch the database entity using Named Query.
	 * 
	 * @param <T>
	 *            Generic Entity
	 * @param queryName
	 *            namedQuery for the entity
	 * @param paramMap
	 *            parameters to be passed to Named Query
	 * 
	 * @return List of fetched entity
	 *//*
	<T extends Object> List<T> findEntitiesByQueryParams(final String queryName, final Map<String, Object> paramMap);

	*//**
	 * Retrieve entities based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param <T>
	 *            Generic Entity
	 * @param jpaQuery
	 *            The query criteria to be searched
	 * @param paramMap
	 *            Map of parameters substituted in the query
	 * @return List of fetched entity
	 *//*
	<T extends Object> List<T> findEntities(final String jpaQuery, final Map<Integer, Object> paramMap);

	<T extends Object> List<T> executeNativeSQL(final String nativeSQLQuery);

	<T extends Serializable> T merge(final T toStore);

	*//**
	 * Remove an entity object from the persistent unit.
	 * 
	 * @param <T>
	 *            entity to be removed.
	 * @param entity
	 *            object to be removed.
	 * 
	 *//*
	<T extends Serializable> void remove(T entity);

	*//**
	 * Retrieve entities based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param jpaQuery
	 *            The query criteria to be searched
	 * @param paramMap
	 *            Map of parameters substituted in the query
	 * 
	 * @return an Object if it exists against given criteria. Returns null if
	 *         not found
	 *//*
	Integer executeUpdateNativeSqlQuery(final String jpaQuery, final Map<Integer, Object> paramMap);

	Integer executeUpdateByNamedQuery(final String namedQuery, final Map<Integer, Object> paramMap);

	*//**
	 * Deletes entities on the basis of named query.
	 * 
	 * @param namedQuery
	 *            the query to execute.
	 * @param paramMap
	 *            named parameters for the query.
	 * @return the count of rows deleted.
	 *//*
	Integer deleteEntititesByNamedQuery(final String namedQuery, final Map<Integer, Object> paramMap);*/
	
	/**
	 * Fetch the database entity using Named Query.
	 * 
	 * @param <T>
	 *            Generic Entity
	 * @param queryName
	 *            namedQuery for the entity
	 * @param paramMap
	 *            parameters to be passed to Named Query
	 * 
	 * @return List of fetched entity
	 */
	<T extends Object> List<T> findEntitiesByNamedQuery(final String queryName, final Map<Integer, Object> paramMap);
	
	/**
	 * Calls stored procedure based on query name
	 * 
	 * @param <T> Generic Entity
	 * @param queryName
	 * 			the SP to execute.
	 * @param paramMap
	 * 			named parameters for the SP.
	 * @return List of fetched entity
	 */
	<T extends Object> List<T> callStoredProc(final String queryName,
			final Map<String, Object> paramMap);
	
	/**
	 * @param <T>
	 * @param entities
	 * @return
	 */
	public <T extends Serializable> Collection<T> bulkInsert(final Collection<T> entities);
	
	public Integer executeNativeSQLQuery(final String nativeSQLQuery);
	
	/**
	 * Deletes entities on the basis of named query.
	 * 
	 * @param namedQuery
	 *            the query to execute.
	 * @param paramMap
	 *            named parameters for the query.
	 * @return the count of rows deleted.
	 */
	Integer deleteEntititesByNamedQuery(final String namedQuery, final Map<Integer, Object> paramMap);
	
	
	
	/**
	 * Calls stored procedure based on query name
	 * 
	 * @param <T> Generic Entity
	 * @param queryName
	 * 			the SP to execute.
	 * @param paramMap
	 * 			named parameters for the SP.
	 * @return List of fetched entity
	 */
	<T extends Object> List<T> callStoredProcForProdUpdate(final String queryName,
			final Map<String, String> paramMap);

}
