package search;

import java.io.File;

/**
 * 
 * @author Sanjeev Kulkarni
 * Test Client for FileSearcher.java 
 */

public class TestFileSearcher {

  /**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSearcher fileSearcher = new FileSearcher();
		String keyWord = "www.sanjeev.org";
		MySET<File> set = fileSearcher.search(keyWord);
		sop("Files containing  " + keyWord + " - : ");
		if (set.isEmpty()) {
			sop("Keyword not found in any of the files");
			return;
		}
		for (File file : set) {
			sop( file.getName() +" at " + file.getAbsolutePath());
		}
	}

	private static void sop(Object o) {
		System.out.println(o);
	}

}
