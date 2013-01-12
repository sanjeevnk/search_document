package search;

import java.io.File;

public class FileSearcher implements ISearcher {

  private MyST<String, MySET<File>> myST = null;

	public MySET<File> search(String keyWord) {
		FileSearchFactory fileSearchfactory = FileSearchFactory.getInstance();
		myST = fileSearchfactory.st;
		MySET<File> set = new MySET<File>();
		if (myST.contains(keyWord)) {
			set = myST.get(keyWord);
		}
		return set;
	}
	

}
