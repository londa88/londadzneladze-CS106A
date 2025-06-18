
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {
	private ArrayList<String> lexicon;

	public HangmanLexicon() throws IOException { // saves every word from hangmanlexicon.txt into the arraylist
		lexicon = new ArrayList<String>();
		try {

			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {

				String line = rd.readLine();

				if (line == null) {

					break;

				}

				lexicon.add(line);

			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public int getWordCount() { // this method shows the amount of words this lexicon contains
		return lexicon.size();
	}

	public String getWord(int i) { // returns the string of the written index
		return lexicon.get(i);
	}
}
