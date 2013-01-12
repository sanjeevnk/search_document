package search;

/***************************************************************************
 * @author Sanjeev Kulkarni
 * @version 1.0
 * Test client code
 **************************************************************************/

public class TestDocumentST {

  public static void main(String[] args) {
		
		DocumentST docST = new DocumentST();
		// insert key, value
		sop("Putting values..." );
		docST.put(1, "one");
		docST.put(2, "two");
		docST.put(3, "three");
		docST.put(4, "four");
		docST.put(6, "six");
		docST.put(5, "five");
		docST.put(7, "seven");
		docST.put(8, "eight");
		docST.put(9, "nine");
		docST.put(10, "nine");

		// get keys
		int[] keys = docST.keys();
		printKeys(keys);

		// delete one key
		docST.delete(8);
		keys = docST.keys();
		sop("keys after deletion = [ ");
		printKeys(keys);
		sop("]");
		// find docid that was removed
		int docId = findMissingDocument(docST, keys);
		sop("docId deleted = " + docId);

	}

	public static int findMissingDocument(DocumentST docST, int[] documents) {
		int docId = docST.deletedDocId(documents);
		return docId;
	}

	private static void printKeys(int[] keys) {
		for (int i = 0; i < keys.length; i++) {
			System.out.print(keys[i] + ",");
		}
	}

	private static void sop(Object o) {
		System.out.println(o);
	}
}
