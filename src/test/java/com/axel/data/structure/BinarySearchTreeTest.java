package com.axel.data.structure;

import com.axel.alg.data.structure.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/5/25
 */
public class BinarySearchTreeTest {

	private BinarySearchTree<Integer> bst;

	@Before
	public void setUp() {
		bst = new BinarySearchTree<>();
		bst.add(10);
		bst.add(20);
		bst.add(1);
		bst.add(4);
		bst.add(11);
		bst.add(12);
		bst.add(17);
		bst.add(3);
		bst.add(23);
		bst.add(22);
	}

	@Test
	public void add() {
		bst.add(1);
	}

	@Test
	public void contains() {
		assert bst.contains(5);
	}

	@Test
	public void remove() {
		System.out.println(bst.remove(10));
	}

	@Test
	public void toCollection() {
		System.out.println(bst.toCollection());
	}

	@Test
	public void size() {
		System.out.println(bst.size());
	}

	@Test
	public void clear() {
		bst.clear();
		System.out.println(bst);
	}

	@Test
	public void validate() {
		System.out.println(bst.validate());
	}

	@Test
	public void testToString() {
		System.out.println(bst);
	}

	@Test
	public void getBFS() {
		System.out.println(Arrays.toString(bst.getBFS()));
	}

	@Test
	public void getDFS() {
		System.out.println(Arrays.toString(bst.getDFS(BinarySearchTree.DepthSearchOrder.postOrder)));
	}
}