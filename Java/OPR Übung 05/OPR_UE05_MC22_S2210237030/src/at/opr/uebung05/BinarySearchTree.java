package at.opr.uebung05;

public class BinarySearchTree<T extends Comparable<T>> {

	/**
	 * protected inner class for the binary tree node.
	 */
	protected class BinaryTreeNode {
		public BinaryTreeNode left;
		public BinaryTreeNode right;
		public T data;

		public BinaryTreeNode(T data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	/**
	 * Root node of the tree.
	 */
	protected BinaryTreeNode root;

	/**
	 * Number of elements stored in the tree.
	 */
	protected int size;

	/**
	 * Inserts the given element. Duplicate elements are not allowed. Returns true
	 * if insertion was successful, false otherwise.
	 */
	public boolean insert(T elem) {
		if (find(elem)) {
			return false;
		}
		BinaryTreeNode t = new BinaryTreeNode(elem);
		BinaryTreeNode parent = root;

		if (root == null) {
			root = t;
			size++;
			return true;
		}

		while (parent != null) {
			if (parent.data.compareTo(elem) < 0 && parent.right != null) {
				parent = parent.right;
			} else if (parent.data.compareTo(elem) > 0 && parent.left != null) {
				parent = parent.left;
			} else {
				if (parent.data.compareTo(elem) < 0) {
					parent.right = t;
					parent = null;
				} else {
					parent.left = t;
					parent = null;
				}
			}
		}

		size++;
		return true;
	}

	/**
	 * Searches for the (first) element with the given key. Returns true if it could
	 * be found, false otherwise.
	 */
	public boolean find(T key) {
		BinaryTreeNode t = root;

		while (t != null) {
			if (t.data.compareTo(key) == 0) {
				return true;
			}
			if (t.data.compareTo(key) < 0) {
				t = t.right;
			} else {
				t = t.left;
			}
		}
		return false;
	}

	/**
	 * Recursive method for remove() to determine the size of the subtree
	 */
	private int count(BinaryTreeNode t, int counter) {
		if (t.left != null) {
			counter = count(t.left, counter);
		}
		counter++;
		if (t.right != null) {
			counter = count(t.right, counter);
		}

		return counter;
	}

	/**
	 * Recursive method for remove() to determine the new root
	 */
	private T newRoot(BinaryTreeNode m, BinaryTreeNode n, BinaryTreeNode root) {
		if (m.left == null || m.right == null) {
			return m.data;
		}

		if (root.left.data == n.data) {
			m.data = newRoot(m.right, n, root);
		} else if (root.right.data == n.data) {
			m.data = newRoot(m.left, n, root);
		}
		return m.data;
	}

	/**
	 * Removes the element with the given key. Returns true if the key could be
	 * found (and removed), false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(T key) {
		if (find(key) == false) {
			return false;
		}
		BinaryTreeNode t = root;
		BinaryTreeNode parent = null;
		BinaryTreeNode parentM = null;

		if (root.data == key) {
			T[] rootSubTree = (T[]) new Comparable[size];
			toArrayPre(rootSubTree, 0, t);

			BinaryTreeNode m = root;

			if (root.left != null) {
				m = root.left;
			} else {
				m = root.right;
			}

			BinaryTreeNode n = new BinaryTreeNode(newRoot(m, m, root));
			root = n;

			for (int i = 1; i < rootSubTree.length; i++) {
				insert(rootSubTree[i]);
				size--;
			}
			return true;
		}

		while (t != null) {
			if (t.data.compareTo(key) == 0) {
				break;
			}
			parent = t;
			if (t.data.compareTo(key) < 0) {
				t = t.right;
			} else {
				t = t.left;
			}
		}

		int sizeSubTree = count(t, 0);

		T[] subTree = (T[]) new Comparable[sizeSubTree];
		toArrayPre(subTree, 0, t);

		if (t.data.compareTo(parent.data) < 0) {
			parent.left = null;
		} else {
			parent.right = null;
		}

		for (int i = 1; i < subTree.length; i++) {
			insert(subTree[i]);
			size--;
		}

		size--;
		return true;
	}

	/**
	 * Returns the number of elements stored in the tree.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the parent element of the given key. Integer.MIN_VALUE if no parent
	 * can be found.
	 */
	public T getParent(T key) {
		if (find(key) == false || key == root.data) {
			return null;
		}

		BinaryTreeNode parent = null;
		BinaryTreeNode child = root;

		while (child != null) {
			if (child.data.compareTo(key) < 0) {
				parent = child;
				child = child.right;
			} else if (child.data.compareTo(key) > 0) {
				parent = child;
				child = child.left;
			} else {
				if (child.data == key) {
					child = null;
				}
			}
		}

		return parent.data;
	}

	/**
	 * Recursive method for toArray()
	 */
	private int toArray(T[] ret, boolean ascending, int offset, BinaryTreeNode n) {
		if (n != null) {
			if (ascending) {
				// Left, Root, Right

				if (n.left != null) {
					offset = toArray(ret, ascending, offset, n.left);
				}
				ret[offset++] = n.data;
				if (n.right != null) {
					offset = toArray(ret, ascending, offset, n.right);
				}
			} else {
				// Right, Root, Left
				if (n.right != null) {
					offset = toArray(ret, ascending, offset, n.right);
				}
				ret[offset++] = n.data;
				if (n.left != null) {
					offset = toArray(ret, ascending, offset, n.left);
				}
			}
		}
		return offset;
	}

	/**
	 * Returns the elements of the tree in ascending (inorder traversal) or
	 * descending (reverse inorder traversal) order.
	 */
	@SuppressWarnings("unchecked")
	public T[] toArray(boolean ascending) {
		T[] a = (T[]) new Comparable[size];
		toArray(a, ascending, 0, root);
		return a;
	}

	/**
	 * Recursive method for toArrayPostOrder()
	 */
	private int toArrayPost(T[] ret, int offset, BinaryTreeNode n) {
		// Left, Right, Root

		if (n.left != null) {
			offset = toArrayPost(ret, offset, n.left);
		}

		if (n.right != null) {
			offset = toArrayPost(ret, offset, n.right);
		}
		ret[offset++] = n.data;

		return offset;
	}

	/**
	 * Returns the elements of the tree (postorder traversal).
	 */
	@SuppressWarnings("unchecked")
	public T[] toArrayPostOrder() {
		T[] a = (T[]) new Comparable[size];
		toArrayPost(a, 0, root);
		return a;
	}

	/**
	 * Recursive method for toArrayPreOrder()
	 */
	private int toArrayPre(T[] ret, int offset, BinaryTreeNode n) {
		// Left, Right, Root

		ret[offset++] = n.data;
		if (n.left != null) {
			offset = toArrayPre(ret, offset, n.left);
		}
		if (n.right != null) {
			offset = toArrayPre(ret, offset, n.right);
		}
		return offset;
	}

	/**
	 * Returns the elements of the tree (preorder traversal).
	 */
	@SuppressWarnings("unchecked")
	public T[] toArrayPreOrder() {
		T[] a = (T[]) new Comparable[size];
		toArrayPre(a, 0, root);
		return a;
	}

	/**
	 * Returns largest number stored in the tree. Integer.MIN_VALUE if no largest
	 * element can be found.
	 */
	public T max() {
		BinaryTreeNode t = root;
		BinaryTreeNode parent = null;
		T max = null;

		while (t != null) {
			if (t.right != null) {
				parent = t;
				t = t.right;
			} else {
				max = t.data;
				t = null;
			}
		}
		return max;
	}

	/**
	 * Returns smallest number stored in the tree. Integer.MIN_VALUE if no smallest
	 * element can be found.
	 */
	public T min() {
		BinaryTreeNode t = root;
		BinaryTreeNode parent = null;
		T min = null;

		while (t != null) {
			if (t.left != null) {
				parent = t;
				t = t.left;
			} else {
				min = t.data;
				t = null;
			}
		}
		return min;
	}

	/**
	 * Recursive method for toString()
	 */
	private void print(StringBuilder b, BinaryTreeNode t, int indent, int line) {
		if (line == size) {
			return;
		}
		T[] array = toArray(false);

		while (t != null) {
			if (t.data.compareTo(array[line]) == 0) {
				break;
			}
			indent += 15;
			if (t.data.compareTo(array[line]) < 0) {
				t = t.right;
			} else {
				t = t.left;
			}
		}

		for (int j = 0; j < indent; j++) {
			b.append(" ");
		}
		b.append(array[line]);
		b.append("\n");

		print(b, root, 0, line + 1);
	}

	/**
	 * Represents the tree in a human readable form.
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		print(b, root, 0, 0);
		return b.toString();
	}

}
