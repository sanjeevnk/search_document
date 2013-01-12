package search;

import java.io.File;

interface ISearcher {
  /*
	 * * @param keyWord - a single word to be searched in the available
	 * documents * @returns List<String> - a list of files in which the word is
	 * present
	 */
	
	// It can't contain duplicate elements.
	// It has to be a SET since each file (Document) has a unique name
	MySET<File> search(String keyWord); 
	
}
