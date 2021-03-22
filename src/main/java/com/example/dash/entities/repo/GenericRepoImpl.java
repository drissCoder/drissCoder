/**
 *
 */
package com.example.dash.entities.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.dash.entities.UserModel;


/**
 * @author pavan.solapure
 *
 */
@Repository
public class GenericRepoImpl implements GenericRepo
{

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.opencodez.repo.GenericRepo#getUserModel()
	 */
	@Override
	public List<UserModel> getUserModel()
	{

		final String qry = "SELECT USER_ID as id, USER_NAME as name, salary as salary, 1 as total_records FROM MYUSERS";
		final Query query = entityManager.createNativeQuery(qry, UserModel.class);

		@SuppressWarnings("unchecked")
		final List<UserModel> daoDtolist = query.getResultList();

		return daoDtolist;
	}

}
