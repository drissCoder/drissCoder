/**
 *
 */
package com.example.dash.entities.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.example.dash.entities.domain.pagination.PaginationCriteria;
import com.google.gson.Gson;


/**
 * The Class AppUtil.
 *
 * @author pavan.solapure
 */
public class AppUtil
{

	/**
	 * Checks if is collection empty.
	 *
	 * @param collection
	 *           the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(final Collection<?> collection)
	{
		if (collection == null || collection.isEmpty())
		{
			return true;
		}
		return false;
	}

	/**
	 * Checks if is object empty.
	 *
	 * @param object
	 *           the object
	 * @return true, if is object empty
	 */
	public static boolean isObjectEmpty(final Object object)
	{
		if (object == null)
		{
			return true;
		}
		else if (object instanceof String)
		{
			if (((String) object).trim().length() == 0)
			{
				return true;
			}
		}
		else if (object instanceof Collection)
		{
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}

	/**
	 * Gets the bean to json string.
	 *
	 * @param beanClass
	 *           the bean class
	 * @return the bean to json string
	 */
	public static String getBeanToJsonString(final Object beanClass)
	{
		return new Gson().toJson(beanClass);
	}

	/**
	 * Gets the bean to json string.
	 *
	 * @param beanClasses
	 *           the bean classes
	 * @return the bean to json string
	 */
	public static String getBeanToJsonString(final Object... beanClasses)
	{
		final StringBuilder stringBuilder = new StringBuilder();
		for (final Object beanClass : beanClasses)
		{
			stringBuilder.append(getBeanToJsonString(beanClass));
			stringBuilder.append(", ");
		}
		return stringBuilder.toString();
	}

	/**
	 * Concatenate.
	 *
	 * @param listOfItems
	 *           the list of items
	 * @param separator
	 *           the separator
	 * @return the string
	 */
	public String concatenate(final List<String> listOfItems, final String separator)
	{
		final StringBuilder sb = new StringBuilder();
		final Iterator<String> stit = listOfItems.iterator();

		while (stit.hasNext())
		{
			sb.append(stit.next());
			if (stit.hasNext())
			{
				sb.append(separator);
			}
		}

		return sb.toString();
	}

	/**
	 * Builds the paginated query.
	 *
	 * @param baseQuery
	 *           the base query
	 * @param paginationCriteria
	 *           the pagination criteria
	 * @return the string
	 */
	public static String buildPaginatedQuery(final String baseQuery, final PaginationCriteria paginationCriteria)
	{
		//String queryTemplate = "SELECT FILTERED_ORDERD_RESULTS.* FROM (SELECT BASEINFO.* FROM ( %s ) BASEINFO %s  %s ) FILTERED_ORDERD_RESULTS LIMIT %d, %d";
		final StringBuilder sb = new StringBuilder(
				"SELECT FILTERED_ORDERD_RESULTS.* FROM (SELECT BASEINFO.* FROM ( #BASE_QUERY# ) BASEINFO #WHERE_CLAUSE#  #ORDER_CLASUE# ) FILTERED_ORDERD_RESULTS LIMIT #PAGE_NUMBER#, #PAGE_SIZE#");
		String finalQuery = null;
		if (!AppUtil.isObjectEmpty(paginationCriteria))
		{
			finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery)
					.replaceAll("#WHERE_CLAUSE#",
							((AppUtil.isObjectEmpty(paginationCriteria.getFilterByClause())) ? "" : " WHERE ")
									+ paginationCriteria.getFilterByClause())
					.replaceAll("#ORDER_CLASUE#", paginationCriteria.getOrderByClause())
					.replaceAll("#PAGE_NUMBER#", paginationCriteria.getPageNumber().toString())
					.replaceAll("#PAGE_SIZE#", paginationCriteria.getPageSize().toString());
		}
		return (null == finalQuery) ? baseQuery : finalQuery;
	}
}
