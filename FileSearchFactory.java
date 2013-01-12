package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * 
 * @author Sanjeev Kulkarni
 * @version 1.0
 *  Singleton implementation for indexing files based on keyword,
 *  to provide single instance of Symbol Table(MyST class)
 *  for all concurrent client requests. When the first client 
 *  queries for a particular keyword, file indexing is done and then
 *  search in performed using O(log n). Given that -
 *  The files(public document libraries) are not getting modified, i.e.
 *  a. No new documents are being added to the libraries.
 *  b. No existing documents are being modified and 
 *  c. No existing documents are being deleted, 
 *  from the second client request onwards, only search is performed 
 *  using Binary Search taking O(log n).
 *   
 */

public class FileSearchFactory {

  private static FileSearchFactory instance = null;
	protected MyST<String, MySET<File>> st = null;
	private static final int SIZE = 3;

	private FileSearchFactory() throws FileSearchException {
		// key = word, value = set of files containing that word
		indexFilesForKeyWords();
	}

	private void indexFilesForKeyWords() throws FileSearchException {
		st = new MyST<String, MySET<File>>();
		// Read in files from the directory
		// For demo, below hard-coded paths are used

		String[] filePaths = new String[SIZE];
		filePaths[0] = "D:/devzone/doc1.txt";
		filePaths[1] = "D:/devzone/doc2.txt";
		filePaths[2] = "D:/devzone/doc3.txt";

		// create inverted index of all files
		System.out.println("Indexing files..");
		for (String filePath : filePaths) {
			System.out.println("  " + filePath);
			File file = new File(filePath);
			Scanner in = null;

			try {
				in = new Scanner(file);
			} catch (FileNotFoundException fnfe) {
				throw new FileSearchException(
						"File wasn't found while indexing files");
			}

			while (in.hasNext()) {
				String word = in.next();
				if (!st.contains(word))
					st.put(word, new MySET<File>());
				MySET<File> set = st.get(word);
				set.add(file);
			}
		}
	}

	public static synchronized FileSearchFactory getInstance() {
		// lazy initialization
		if (instance == null) {
			try {
				instance = new FileSearchFactory();
			} catch (FileSearchException e) {
				System.out.println(e.getMessage());
			}
		}
		return instance;
	}

	// preventing client's invoking Object.clone()
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
