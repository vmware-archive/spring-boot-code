package io.pivotal.workshop.snippet.repository;

import java.util.Collection;

public interface SimpleRepository<T> {

	Iterable<T> findAll();
	void saveAll(Collection<T> items);
	T saveAll(T item);
	T findById(String id);
}
