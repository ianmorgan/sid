package com.lateblindcat.sid.framework.forms;

import java.util.HashMap;

public class AttrList extends HashMap<String, Object> {
	private static final long serialVersionUID = -5054088440351808774L;

	public String render() {
		StringBuilder sb = new StringBuilder();
		for (String attr : this.keySet()) {
			sb.append(attr);
			sb.append("=\"");
			sb.append(this.get(attr).toString());
			sb.append("\" ");
		}
		return sb.toString();
	}

}
