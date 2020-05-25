package com.axel.alg.data.structure;

import java.util.Collection;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/5/25
 */
public interface ITree<T> {

	boolean add(T t);

	T remove(T t);

	int size();

	void clear();

	boolean contains(T t);

	boolean validate();

	Collection<T> toCollection();
}
