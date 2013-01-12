package search;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MySET<Key extends Comparable<Key>> implements Iterable<Key> {

  private TreeSet<Key> set;

	/**
	 * Create an empty set.
	 */
	public MySET() {
		set = new TreeSet<Key>();
	}

	/**
	 * Is this set empty?
	 */
	public boolean isEmpty() {
		return set.isEmpty();
	}

	/**
	 * Add the key to this set.
	 */
	public void add(Key key) {
		set.add(key);
	}

	/**
	 * Does this set contain the given key?
	 */
	public boolean contains(Key key) {
		return set.contains(key);
	}

	/**
	 * Delete the given key from this set.
	 */
	public void delete(Key key) {
		set.remove(key);
	}

	/**
	 * Return the number of keys in this set.
	 */
	public int size() {
		return set.size();
	}

	/**
	 * Return an Iterator for this set.
	 */
	public Iterator<Key> iterator() {
		return set.iterator();
	}

	/**
	 * Return the key in this set with the maximum value.
	 */
	public Key max() {
		return set.last();
	}

	/**
	 * Return the key in this set with the minimum value.
	 */
	public Key min() {
		return set.first();
	}

	/**
	 * Return the smallest key in this set >= k.
	 */
	public Key ceil(Key k) {
		SortedSet<Key> tail = set.tailSet(k);
		if (tail.isEmpty())
			return null;
		else
			return tail.first();
	}

	/**
	 * Return the largest key in this set <= k.
	 */
	public Key floor(Key k) {
		if (set.contains(k))
			return k;

		// does not include key if present (!)
		SortedSet<Key> head = set.headSet(k);
		if (head.isEmpty())
			return null;
		else
			return head.last();
	}

	/**
	 * Return the union of this set with that set.
	 */
	public MySET<Key> union(MySET<Key> that) {
		MySET<Key> c = new MySET<Key>();
		for (Key x : this) {
			c.add(x);
		}
		for (Key x : that) {
			c.add(x);
		}
		return c;
	}

	/**
	 * Return the intersection of this set with that set.
	 */
	public MySET<Key> intersects(MySET<Key> that) {
		MySET<Key> c = new MySET<Key>();
		if (this.size() < that.size()) {
			for (Key x : this) {
				if (that.contains(x))
					c.add(x);
			}
		} else {
			for (Key x : that) {
				if (this.contains(x))
					c.add(x);
			}
		}
		return c;
	}

}
