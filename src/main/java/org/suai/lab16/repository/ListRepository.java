package org.suai.lab16.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ListRepository {
	private final Map<String, List<String>> list = new ConcurrentHashMap<>();

	public void add(String outer, List<String> inner) {
		list.put(outer, inner);
	}

	public Set<Map.Entry<String, List<String>>> entrySet() {
		return list.entrySet();
	}
}