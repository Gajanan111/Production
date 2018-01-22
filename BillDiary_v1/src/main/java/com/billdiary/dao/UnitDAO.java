package com.billdiary.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.billdiary.entities.UnitEntity;


@Repository
public class UnitDAO extends AbstractJpaDAO< UnitEntity >{

	public UnitDAO()
	{
		setClazz(UnitEntity.class );
	}
	
	@Transactional
	public boolean deleteUnit(long unitId) {
		boolean deleted=false;
		deleteById(unitId);
		deleted=true;
		return deleted;
	}
}
