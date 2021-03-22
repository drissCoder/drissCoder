/**
 *
 */
package com.example.dash.entities.domain.pagination;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The Class DataTableColumnSpecs.
 *
 * @author pavan.solapure
 */
public class DataTableColumnSpecs
{

	/** The index. */
	private int index;

	/** The data. */
	private String data;

	/** The name. */
	private String name;

	/** The searchable. */
	private boolean searchable;

	/** The orderable. */
	private boolean orderable;

	/** The search. */
	private String search;

	/** The regex. */
	private boolean regex;

	/** The sort dir. */
	private String sortDir;


	/**
	 * Instantiates a new data table column specs.
	 *
	 * @param request
	 *           the request
	 * @param i
	 *           the i
	 */
	public DataTableColumnSpecs(final Map<String, Object> map1, final int i)
	{
		this.setIndex(i);
		prepareColumnSpecs(map1, i);
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *           the data to set
	 */
	public void setData(final String data)
	{
		this.data = data;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *           the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Checks if is searchable.
	 *
	 * @return the searchable
	 */
	public boolean isSearchable()
	{
		return searchable;
	}

	/**
	 * Sets the searchable.
	 *
	 * @param searchable
	 *           the searchable to set
	 */
	public void setSearchable(final boolean searchable)
	{
		this.searchable = searchable;
	}

	/**
	 * Checks if is orderable.
	 *
	 * @return the orderable
	 */
	public boolean isOrderable()
	{
		return orderable;
	}

	/**
	 * Sets the orderable.
	 *
	 * @param orderable
	 *           the orderable to set
	 */
	public void setOrderable(final boolean orderable)
	{
		this.orderable = orderable;
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
	 * Gets the sort dir.
	 *
	 * @return the sortDir
	 */
	public String getSortDir()
	{
		return sortDir;
	}

	/**
	 * Sets the sort dir.
	 *
	 * @param sortDir
	 *           the sortDir to set
	 */
	public void setSortDir(final String sortDir)
	{
		this.sortDir = (null != sortDir) ? sortDir.toUpperCase() : sortDir;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index
	 *           the index to set
	 */
	public void setIndex(final int index)
	{
		this.index = index;
	}

	/**
	 * Prepare column specs.
	 *
	 * @param request
	 *           the request
	 * @param i
	 *           the i
	 */
	private void prepareColumnSpecs(final Map<String, Object> map1, final int i)
	{

		final LinkedHashMap<String, Object> f = (LinkedHashMap<String, Object>) map1.get("columns[" + i + "]");

		this.setData((String) f.get("data"));
		this.setName((String) f.get("name"));
		this.setOrderable((Boolean) f.get("orderable"));
		final LinkedHashMap<String, Object> h = (LinkedHashMap<String, Object>) f.get("search");
		this.setRegex((Boolean) h.get("regex"));
		this.setSearch((String) h.get("value"));
		this.setSearchable((boolean) f.get("searchable"));
		//final LinkedHashMap<String, Object> g = (LinkedHashMap<String, Object>) map1.get("order[0]");
		//final int sortableCol = (Integer) g.get("column");
		//final String sortDir = (String) g.get("dir");

		//if (i == sortableCol)
		//{
		//this.setSortDir(sortDir);
		//}
	}

}
