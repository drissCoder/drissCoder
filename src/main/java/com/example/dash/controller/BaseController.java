/**
 *
 */
package com.example.dash.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dash.entities.User;
import com.example.dash.entities.UserModel;
import com.example.dash.entities.domain.pagination.DataTableRequest;
import com.example.dash.entities.domain.pagination.DataTableResults;
import com.example.dash.entities.domain.pagination.PaginationCriteria;
import com.example.dash.entities.repo.GenericRepo;
import com.example.dash.entities.repo.UserRepository;
import com.example.dash.entities.util.AppUtil;
import com.google.gson.Gson;


/**
 * @author pavan.solapure
 *
 */
@Controller
public class BaseController
{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private GenericRepo genericRepo;

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(final Model model)
	{
		return "users_mysql";
	}

	@RequestMapping(value = "/usersFilter", method = RequestMethod.POST)
	@ResponseBody
	public String listUsersPaginated(@RequestBody final Map<String, Object> map1)
	{

		final DataTableRequest<User> dataTableInRQ = new DataTableRequest<User>(map1);
		final PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();

		final String baseQuery = "SELECT USER_ID as id, USER_NAME as name, SALARY as salary, (SELECT COUNT(1) FROM MYUSERS) AS total_records  FROM MYUSERS";
		final String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);

		System.out.println(paginatedQuery);

		final Query query = entityManager.createNativeQuery(paginatedQuery, UserModel.class);

		@SuppressWarnings("unchecked")
		final List<UserModel> userList = query.getResultList();

		final DataTableResults<UserModel> dataTableResult = new DataTableResults<UserModel>();
		dataTableResult.setDraw(dataTableInRQ.getDraw());
		dataTableResult.setListOfDataObjects(userList);
		if (!AppUtil.isObjectEmpty(userList))
		{
			dataTableResult.setRecordsTotal(userList.get(0).getTotalRecords().toString());
			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty())
			{
				dataTableResult.setRecordsFiltered(userList.get(0).getTotalRecords().toString());
			}
			else
			{
				dataTableResult.setRecordsFiltered(Integer.toString(userList.size()));
			}
		}
		return new Gson().toJson(dataTableResult);
	}
}
