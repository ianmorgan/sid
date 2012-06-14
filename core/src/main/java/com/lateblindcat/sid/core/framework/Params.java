package com.lateblindcat.sid.core.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lateblindcat.sid.core.framework.Params.Param;

/**
 * <p>
 * A Immutable list for holding and manipulating the request data (tokens) in an
 * Http Request.
 * </p>
 * 
 * <p>
 * This does not follow the HttpServletRequest model that keep the request path
 * and it query params or submitted data separate. Instead, it is based on the
 * model used by Ruby on Rails whereby a request and it query parameters are
 * standardised into a number of tokens based on the route match and the values
 * in the query parameters.
 * </p>
 * 
 * <p>
 * This makes it easier to deal with complex routes and params, as both are
 * handled in the same way. The example below make it clearer:
 * 
 * <pre>
 *  The route
 *       /account/:action/:id
 *       
 *  Can be matched by any of 
 *       GET http://example.com/account/show/27
 *   or
 *       GET http://example.com/account/show?id=27
 *   or
 *       POST http://example.com/account/show  (with form data containing "id=27")
 *     
 *  In all cases there are three params:
 *       name    value
 *       -----   -----
 *       (null)  account
 *       action  show
 *       id      27
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * <i>Note: The current implementation is simplistic and not optimised.</i>
 * </p>
 * 
 * <p>
 * <i>Note: Consider implement using generics.</i>
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class Params implements Iterable<Param> {
	private List<Param> parts;

	

	public Params(List<Param> params) {
		this.parts = new ArrayList<Param>(params);
	}

	

	public Params() {
		this.parts = new ArrayList<Param>();
	}

	@Override
	public Iterator<Param> iterator() {
		return parts.iterator();
	}

	public int size() {
		return parts.size();
	}

	public boolean isEmpty() {
		return parts.isEmpty();
	}

	public Param head() {
		return parts.get(0);
	}

	public Param last() {
		return parts.get(parts.size() - 1);
	}

	public Params append(String part) {
		List<Param> working = new ArrayList<Param>(this.parts);
		working.add(new Param(part));
		return new Params(working);
	}

	/**
	 * Takes the parts and turn into a path for use in http request
	 * 
	 * @return
	 */
	public String expandToPath() {
		return toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Param param : parts) {
			if (sb.length() > 0) {
				sb.append("/");
			}
			sb.append(param.value);
		}
		return sb.toString();
	}

	

	public class Param {
		public Param(String value) {
			this.value = value;
		}

		public Param(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String value;
		public String name;
	}


}