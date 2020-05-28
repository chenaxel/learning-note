package com.axel.alg.data.structure;

import java.lang.reflect.Array;
import java.util.*;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/5/25
 */
public class BinarySearchTree<T extends Comparable<T>> implements ITree<T> {

	protected int modifications;
	protected int size;
	protected Node<T> root;

	public enum DepthSearchOrder {
		preOrder, inOrder, postOrder;
	}

	@Override
	public boolean add(T t) {
		addValue(t);
		return true;
	}

	protected Node<T> addValue(T value) {
		Node<T> newNode = new Node<>(value);
		if (root == null) {
			root = newNode;
			size++;
			return newNode;
		}

		Node<T> node = root;
		while (true) {
			if (newNode.value.compareTo(node.value) > 0) {
				//greater than root
				if (node.right == null) {
					node.right = newNode;
					newNode.parent = node;
					size++;
					return newNode;
				}
				node = node.right;
			} else {
				//less than root
				if (node.left == null) {
					node.left = newNode;
					newNode.parent = node;
					size++;
					return newNode;
				}
				node = node.left;
			}
		}
	}

	protected void rotateLeft(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> left = node.right.left;
		Node<T> right = node.right;

		node.right.left = node;
		node.parent = node.right;
		node.right = left;

		if (left != null) {
			left.parent = node;
		}
		if (parent != null) {
			if (node == parent.left) {
				parent.left = right;
				right.parent = parent.left;
			}
			if (node == parent.right) {
				parent.right = right;
				right.parent = parent.right;
			}
		} else {
			root = right;
			root.parent = null;
		}
	}

	protected void rotateRight(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> right = node.left.right;
		Node<T> left = node.left;

		node.parent = left;
		left.right = node;
		node.left = right;
		if (right != null) {
			right.parent = node;
		}
		if (parent != null) {
			if (node == parent.left) {
				parent.left = left;
			}
			if (node == parent.right) {
				parent.right = left;
			}
		} else {
			root = left;
			root.parent = null;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T remove(T t) {
		Node<T> remove = getNode(t);
		if (remove == null) {
			return null;
		}
		if (remove.value.compareTo(remove.parent.value) > 0) {
			//删right
			if (remove.left == null && remove.right == null) {
				remove.parent.right = null;
				size--;
				return remove.value;
			}
			if (remove.left == null) {
				remove.parent.right = remove.right;
				remove.right.parent = remove.parent;
				size--;
				return remove.value;
			}
			if (remove.right == null) {
				remove.parent.right = remove.left;
				remove.left.parent = remove.parent;
				size--;
				return remove.value;
			}
			Node<T> left = remove.left;
			Node<T> right = remove.right;
			if (modifications % 2 == 0) {
				Node<T> least = getLeast(right);
				remove.parent.right = right;
				right.parent = remove.parent;
				least.left = left;
				left.parent = least;
			} else {
				Node<T> greatest = getGreatest(left);
				remove.parent.right = left;
				left.parent = remove.parent;
				greatest.right = right;
				right.parent = greatest;
			}
		} else {
			if (remove.left == null && remove.right == null) {
				remove.parent.left = null;
				size--;
				return remove.value;
			}
			if (remove.left == null) {
				remove.parent.left = remove.right;
				remove.right.parent = remove.parent;
				size--;
				return remove.value;
			}
			if (remove.right == null) {
				remove.parent.left = remove.left;
				remove.left.parent = remove.parent;
				size--;
				return remove.value;
			}
			Node<T> left = remove.left;
			Node<T> right = remove.right;
			if (modifications % 2 == 0) {
				Node<T> least = getLeast(right);
				remove.parent.left = right;
				right.parent = remove.parent;
				least.left = left;
				left.parent = least;
			} else {
				Node<T> greatest = getGreatest(left);
				remove.parent.left = left;
				left.parent = remove.parent;
				greatest.right = right;
				right.parent = greatest;
			}
		}
		modifications++;
		size--;
		return remove.value;
	}

	private Node<T> getGreatest(Node<T> root) {
		if (root == null) {
			return null;
		}
		Node<T> node = root.right;
		while (node != null && node.right != null) {
			Node<T> greater = node.right;
			if (greater.right != null) {
				node = greater;
			} else {
				break;
			}
		}
		return node;
	}

	private Node<T> getLeast(Node<T> root) {
		if (root == null) {
			return null;
		}
		Node<T> node = root.left;
		while (node != null && node.left != null) {
			Node<T> less = node.left;
			if (less.left != null) {
				node = less;
			} else {
				break;
			}
		}
		return node;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(T t) {
		return getNode(t) != null;
	}

	@Override
	public boolean validate() {
		if (root == null) {
			return true;
		}
		return validateNode(root);
	}

	private boolean validateNode(Node<T> node) {
		boolean less = true;
		if (node.left != null) {
			less = node.left.value.compareTo(node.value) <= 0;
			if (less) {
				less = validateNode(node.left);
			}
		}
		if (!less) {
			return false;
		}
		boolean great = true;
		if (node.right != null) {
			great = node.right.value.compareTo(node.value) > 0;
			if (great) {
				great = validateNode(node.right);
			}
		}
		return great;
	}

	@Override
	public Collection<T> toCollection() {
		return new JavaCompatibleBinarySearchTree<>(this);
	}

	@Override
	public String toString() {
		return TreePrinter.getString(this);
	}

	protected static class Node<T extends Comparable<T>> {

		protected T value;
		protected Node<T> parent;
		protected Node<T> left = null;
		protected Node<T> right = null;

		public Node(T value) {
			this.value = value;
			this.parent = null;
		}

		@Override
		public String toString() {
			return "Node{" +
					"value=" + value +
					", left=" + left +
					", right=" + right +
					'}';
		}
	}

	private Node<T> getNode(T value) {
		if (root == null) {
			return null;
		}
		Node<T> node = root;
		while (node != null) {
			if (node.value.compareTo(value) == 0) {
				return node;
			} else if (node.value.compareTo(value) > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return null;
	}

	public T[] getBFS() {
		return getBFS(root, size);
	}

	public T[] getDFS(DepthSearchOrder order) {
		return getDFS(order, this);
	}

	private <T extends Comparable<T>> T[] getBFS(Node<T> start, int size) {

		Deque<Node<T>> deque = new ArrayDeque<>();
		final T[] values = (T[]) Array.newInstance(start.value.getClass(), size);
		Node<T> node = start;
		int count = 0;
		while (node != null) {
			values[count] = node.value;
			if (node.left != null) {
				deque.add(node.left);
			}
			if (node.right != null) {
				deque.add(node.right);
			}
			if (!deque.isEmpty()) {
				node = deque.pop();
			} else {
				node = null;
			}
			count++;
		}
		return values;
	}

	private <T extends Comparable<T>> T[] getDFS(DepthSearchOrder order, BinarySearchTree<T> bst) {

		if (bst.size == 0) {
			return null;
		}
		order = order == null ? DepthSearchOrder.inOrder : order;
		T[] values = (T[]) Array.newInstance(bst.root.value.getClass(), bst.size);
		Set<Node<T>> added = new HashSet<>();
		int count = 0;
		Node<T> node = bst.root;
		switch (order) {
			case preOrder:
				while (count < bst.size && node != null) {
					if (!added.contains(node)) {
						values[count++] = node.value;
						added.add(node);
					} else if (node.left != null && !added.contains(node.left)) {
						node = node.left;
					} else if (node.right != null && !added.contains(node.right)) {
						node = node.right;
					} else if (added.contains(node)) {
						node = node.parent;
					} else {
						node = null;
					}
				}
				break;
			case inOrder:
				while (count < bst.size && node != null) {
					if (node.left != null && !added.contains(node.left)) {
						node = node.left;
					} else {
						if (!added.contains(node)) {
							values[count++] = node.value;
							added.add(node);
						}
						if (node.right != null && !added.contains(node.right)) {
							node = node.right;
						} else if (added.contains(node)) {
							node = node.parent;
						} else {
							node = null;
						}
					}
				}
				break;
			case postOrder:
				while (count < bst.size && node != null) {
					if (node.left != null && !added.contains(node.left)) {
						node = node.left;
					} else {
						if (node.right != null && !added.contains(node.right)) {
							node = node.right;
						} else {
							values[count++] = node.value;
							added.add(node);
							node = node.parent;
						}
					}
				}
				break;
			default:
				break;
		}
		return values;
	}

	protected static class TreePrinter {

		public static <T extends Comparable<T>> String getString(BinarySearchTree<T> tree) {
			if (tree.root == null)
				return "Tree has no nodes.";
			return getString(tree.root, "", true);
		}

		private static <T extends Comparable<T>> String getString(Node<T> node, String prefix, boolean isTail) {
			StringBuilder builder = new StringBuilder();

			if (node.parent != null) {
				String side = "left";
				if (node.equals(node.parent.right))
					side = "right";
				builder.append(prefix).append(isTail ? "└── " : "├── ").append("(").append(side).append(") ").append(node.value).append("\n");
			} else {
				builder.append(prefix).append(isTail ? "└── " : "├── ").append(node.value).append("\n");
			}
			List<Node<T>> children = null;
			if (node.left != null || node.right != null) {
				children = new ArrayList<>(2);
				if (node.left != null)
					children.add(node.left);
				if (node.right != null)
					children.add(node.right);
			}
			if (children != null) {
				for (int i = 0; i < children.size() - 1; i++) {
					builder.append(getString(children.get(i), prefix + (isTail ? "    " : "│   "), false));
				}
				if (children.size() >= 1) {
					builder.append(getString(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true));
				}
			}
			return builder.toString();
		}
	}

	private static class JavaCompatibleBinarySearchTree<T extends Comparable<T>> extends AbstractCollection<T> {

		private BinarySearchTree<T> tree;

		public JavaCompatibleBinarySearchTree(BinarySearchTree<T> tree) {
			this.tree = tree;
		}

		@Override
		public Iterator<T> iterator() {
			return new BinarySearchTreeIterator<>(tree);
		}

		@Override
		public int size() {
			return tree.size;
		}

		private static class BinarySearchTreeIterator<C extends Comparable<C>> implements Iterator<C> {

			private BinarySearchTree<C> tree;
			private BinarySearchTree.Node<C> last;
			private Deque<BinarySearchTree.Node<C>> array = new ArrayDeque<>();

			public BinarySearchTreeIterator(BinarySearchTree<C> tree) {
				this.tree = tree;
				if (tree.root != null) {
					array.add(tree.root);
				}
			}

			@Override
			public boolean hasNext() {
				return array.size() > 0;
			}

			@Override
			public C next() {
				while (array.size() > 0) {
					BinarySearchTree.Node<C> node = array.pop();
					if (node.left != null) {
						array.add(node.left);
					}
					if (node.right != null) {
						array.add(node.right);
					}
					last = node;
					return node.value;
				}
				return null;
			}
		}
	}
}
