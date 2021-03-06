package com.example.dash.entities.domain.pagination;

/**
 * The Enum SortOrder.
 */
public enum SortOrder
{

	/** The asc. */
	ASC("ASC"),
	/** The desc. */
	DESC("DESC");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new sort order.
	 *
	 * @param v
	 *           the v
	 */
	SortOrder(final String v)
	{
		value = v;
	}

	/**
	 * From value.
	 *
	 * @param v
	 *           the v
	 * @return the sort order
	 */
	public static SortOrder fromValue(final String v)
	{
		for (final SortOrder c : SortOrder.values())
		{
			if (c.name().equals(v))
			{
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value()
	{
		return value;
	}

}
