/**
 *
 */
package com.example.dash.entities.domain.pagination;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.dash.entities.util.AppUtil;


/**
 * The Class DataTableRequest.
 *
 * @author pavan.solapure
 */
public class DataTableRequest<T>
{

	/** The unique id. */
	private String uniqueId;

	/** The draw. */
	private String draw;

	/** The start. */
	private Integer start;

	/** The length. */
	private Integer length;

	/** The search. */
	private String search;

	/** The regex. */
	private boolean regex;

	/** The columns. */
	private List<DataTableColumnSpecs> columns;

	/** The order. */
	private DataTableColumnSpecs order;

	/** The is global search. */
	private boolean isGlobalSearch;

	/**
	 * Instantiates a new data table request.
	 *
	 * @param request
	 *           the request
	 */
	public DataTableRequest(final Map<String, Object> map1)
	{
		prepareDataTableRequest(map1);
	}

	/**
	 * Gets the unique id.
	 *
	 * @return the uniqueId
	 */
	public String getUniqueId()
	{
		return uniqueId;
	}

	/**
	 * Sets the unique id.
	 *
	 * @param uniqueId
	 *           the uniqueId to set
	 */
	public void setUniqueId(final String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Integer getStart()
	{
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start
	 *           the start to set
	 */
	public void setStart(final Integer start)
	{
		this.start = start;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public Integer getLength()
	{
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length
	 *           the length to set
	 */
	public void setLength(final Integer length)
	{
		this.length = length;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public String getSearch()
	{
		return search;
	}

	/**
	 * Sets the search.
	 *
	 * @param search
	 *           the search to set
	 */
	public void setSearch(final String search)
	{
		this.search = search;
	}

	/**
	 * Checks if is regex.
	 *
	 * @return the regex
	 */
	public boolean isRegex()
	{
		return regex;
	}

	/**
	 * Sets the regex.
	 *
	 * @param regex
	 *           the regex to set
	 */
	public void setRegex(final boolean regex)
	{
		this.regex = regex;
	}

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public List<DataTableColumnSpecs> getColumns()
	{
		return columns;
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns
	 *           the columns to set
	 */
	public void setColumns(final List<DataTableColumnSpecs> columns)
	{
		this.columns = columns;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public DataTableColumnSpecs getOrder()
	{
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order
	 *           the order to set
	 */
	public void setOrder(final DataTableColumnSpecs order)
	{
		this.order = order;
	}

	/**
	 * Gets the draw.
	 *
	 * @return the draw
	 */
	public String getDraw()
	{
		return draw;
	}

	/**
	 * Sets the draw.
	 *
	 * @param draw
	 *           the draw to set
	 */
	public void setDraw(final String draw)
	{
		this.draw = draw;
	}

	/**
	 * Checks if is global search.
	 *
	 * @return the isGlobalSearch
	 */
	public boolean isGlobalSearch()
	{
		return isGlobalSearch;
	}

	/**
	 * Sets the global search.
	 *
	 * @param isGlobalSearch
	 *           the isGlobalSearch to set
	 */
	public void setGlobalSearch(final boolean isGlobalSearch)
	{
		this.isGlobalSearch = isGlobalSearch;
	}

	/**
	 * Prepare data table request.
	 *
	 * @param request
	 *           the request
	 */
	private void prepareDataTableRequest(final Map<String, Object> map1)
	{

		this.setStart((Integer) map1.get(PaginationCriteria.PAGE_NO));
		this.setLength((Integer) map1.get(PaginationCriteria.PAGE_SIZE));
		this.setUniqueId((String) map1.get("_"));
		this.setDraw(String.valueOf(map1.get(PaginationCriteria.DRAW)));


		final LinkedHashMap<String, Object> b = (LinkedHashMap<String, Object>) map1.get("search");

		this.setSearch((String) b.get("value"));
		this.setRegex((Boolean) b.get("regex"));

		//final LinkedHashMap<String, Object> d = (LinkedHashMap<String, Object>) map1.get("order[0]");
		//final int sortableCol = (Integer) d.get("column");

		final List<DataTableColumnSpecs> columns = new ArrayList<DataTableColumnSpecs>();

		if (!AppUtil.isObjectEmpty(this.getSearch()))
		{
			this.setGlobalSearch(true);
		}

		maxParamsToCheck = 3;//getNumberOfColumns(map1);


		for (int i = 0; i < maxParamsToCheck; i++)
		{
			final LinkedHashMap<String, Object> e = (LinkedHashMap<String, Object>) map1.get("columns[" + i + "]");

			if (null != e.get("data") && !"null".equalsIgnoreCase((String) e.get("data")) && !AppUtil.isObjectEmpty(e.get("data")))
			{
				final DataTableColumnSpecs colSpec = new DataTableColumnSpecs(map1, i);
				//if (i == sortableCol)
				//{
				//this.setOrder(colSpec);
				//}
				columns.add(colSpec);

				if (!AppUtil.isObjectEmpty(colSpec.getSearch()))
				{
					this.setGlobalSearch(false);
				}
			}
		}

		if (!AppUtil.isObjectEmpty(columns))
		{
			this.setColumns(columns);
		}
	}

	private int getNumberOfColumns(final Map<String, Object> map1)
	{
		final Pattern p = Pattern.compile("columns\\[[0-9]+\\]\\[data\\]");
		@SuppressWarnings("rawtypes")
		final Set<String> params = map1.keySet();
		final List<String> lstOfParams = new ArrayList<String>();
		for (final String paramName : params)
		{
			final Matcher m = p.matcher(paramName);
			if (m.matches())
			{
				lstOfParams.add(paramName);
			}
		}
		return lstOfParams.size();
	}

	/**
	 * Gets the pagination request.
	 *
	 * @return the pagination request
	 */
	public PaginationCriteria getPaginationRequest()
	{

		final PaginationCriteria pagination = new PaginationCriteria();
		pagination.setPageNumber(this.getStart());
		pagination.setPageSize(this.getLength());

		SortBy sortBy = null;
		if (!AppUtil.isObjectEmpty(this.getOrder()))
		{
			sortBy = new SortBy();
			sortBy.addSort(this.getOrder().getData(), SortOrder.fromValue(this.getOrder().getSortDir()));
		}

		final FilterBy filterBy = new FilterBy();
		filterBy.setGlobalSearch(this.isGlobalSearch());
		for (final DataTableColumnSpecs colSpec : this.getColumns())
		{
			if (colSpec.isSearchable())
			{
				if (!AppUtil.isObjectEmpty(this.getSearch()) || !AppUtil.isObjectEmpty(colSpec.getSearch()))
				{
					filterBy.addFilter(colSpec.getData(), (this.isGlobalSearch()) ? this.getSearch() : colSpec.getSearch());
				}
			}
		}

		pagination.setSortBy(sortBy);
		pagination.setFilterBy(filterBy);

		return pagination;
	}

	/** The max params to check. */
	private int maxParamsToCheck = 0;

}
