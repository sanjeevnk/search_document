package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.print.attribute.standard.Fidelity;

import org.omg.CORBA.portable.RemarshalException;

/**
 * 
 * @author Sanjeev Kulkarni
 * @version 1.0
 * 
 */

public class DocumentST {
  private static final int INIT_CAPACITY = 2;
	private int[] keys;
	private String[] vals;
	private int N = 0;

	// create an empty symbol table with default initial capacity
	public DocumentST() {
		this(INIT_CAPACITY);
	}

	// create an empty symbol table with given initial capacity
	public DocumentST(int capacity) {
		keys = (int[]) new int[capacity];
		vals = (String[]) new String[capacity];
		// vals can be title/description of this document
	}

	// resize the underlying arrays
	private void resize(int capacity) {
		assert capacity >= N;
		int[] tempk = new int[capacity];
		String[] tempv =  new String[capacity];
		for (int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		vals = tempv;
		keys = tempk;
	}

	// is the key in the table?
	public boolean contains(int key) {
		return get(key) != null;
	}

	// number of key-value pairs in the table
	public int size() {
		return N;
	}

	// is the symbol table empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// return the value associated with the given key, or -1 if no such key
	public String get(int key) {
		if (isEmpty())
			return null;
		return vals[key];
	}

	// Insert (key, value) = [docId, docDesc],
	// Search for key. Update value if found; grow table if new.
	public void put(int key, String val) {
		if (val == null) {
			delete(key);
			return;
		}

		// key is already in table
		if (key < N && (keys[key - 1] == key)) {
			vals[key - 1] = val;
			return;
		}

		// insert new key-value pair
		if (N == keys.length)
			resize(2 * keys.length);

		keys[key - 1] = key;
		vals[key - 1] = val;
		N++;
	}

	// Since documents are not very rarely deleted, below delete(..) works well
	// to find the document that was removed
	// Update the (key, value) for suitable values without the key-value pair if
	// present
	public void delete(int key) {
		if (isEmpty())
			return;
		// key not in table
		if (key == N) {
			return;
		}

		// Note that entry is not removed from the table, it's key and values
		// are updated
		// if multiples deletes occur,
		keys[key - 1] = -key; // deleted element, -i used to identify which
		// key was
		// actually deleted
		vals[key - 1] = null;
	}

	/***************************************************************************
	 * Ordered symbol table methods
	 **************************************************************************/
	public int min() {
		if (isEmpty())
			return -1;
		return keys[0];
	}

	public int max() {
		if (isEmpty())
			return -1;
		return keys[N - 1];
	}

	public int[] keys() {
		return keys;
	}

	/***************************************************************************
	 * Ordered symbol table methods for special situation of removal
	 **************************************************************************/
	public int deletedDocId(int[] documents) {
		return removedDocument(keys);
	}

	
	private int removedDocument(int[] keys) {
		for (int i = 0; i < keys.length - 1; i++) {
			// maybe, we could use (keys[i + 1] - keys[i]) != 1, instead of below, 
			// if the docId missing is only one at any given time, since we're updating
			// the key value to it's negative counterpart during deletion
			if ((keys[i + 1] - keys[i]) < 0) { 
				return keys[i] + 1;
			}
		}
		return -1;
	}

}
