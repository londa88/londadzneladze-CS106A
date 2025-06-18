import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {
	private HashMap<String, NameSurferEntry> dataMap;

	public NameSurferDataBase(String filename) { //spacing every line in the data-text-txt into the hashmap
		dataMap = new HashMap<String, NameSurferEntry>();
		try {

			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {

				String line = rd.readLine();

				if (line == null) {

					break;

				}
				NameSurferEntry entry = new NameSurferEntry(line); //transffering every line into the namesurferentry method

				dataMap.put(entry.getName(), entry);

			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) { //formating first chars into uppercase, restchars into lowercase

     	char ch = name.charAt(0);
		if (Character.isLowerCase(ch)) {
			ch=Character.toUpperCase(ch);
		}
		
		String restChars = name.substring(1).toLowerCase();
		name = ch + restChars;
		if(dataMap.keySet().contains(name)) { //checking if hashmap contains typed name
		return dataMap.get(name);
	}
		else return null;
	}
}
