package main;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
	
	/**
	 * TODO: Don't forget to constantly compare linksToParse.size() to maxLinksToParse so we only 
	 * grab as many links as we need
	 * TODO: Separate bits in 'startParsing' method into separate methods - maybe the link 
	 * gathering part? (where new links are stored into 'linksToParse')
	 * TODO: Start parsing links gathered from the initial parse in 'startParsing' method
	 */
	
	int maxLinksToParse;
	int linksParsedCounter;
	ArrayList<String> linksToParse = new ArrayList<String>();
	ArrayList<String> wordsFound = new ArrayList<String>();
	
	public Scraper(int maxLinksToParse) {
		this.maxLinksToParse = maxLinksToParse;
	}

	public void startParsing(String initialLink) {
		try {
			Document doc = Jsoup.connect(initialLink).get();
			Elements links = doc.select("a[href]");
			
			for (Element link : links) {
	            linksToParse.add(link.attr("abs:href"));
	        }
			
			Elements paragraphs = doc.select(".mw-content-ltr p, .mw-body ul");
			
			for(Element paragraph : paragraphs) {
				storeAllWordsFromElement(paragraph, wordsFound);
			}
			
//			for(int i = 0; i < wordsFound.size(); i++)
//			{
//				System.out.println(wordsFound.get(i));
//			}
			
			while(linksParsedCounter < maxLinksToParse) {
				Document currentDoc = Jsoup.connect(linksToParse.get(linksParsedCounter)).get();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stores all words found within a JSoup Element into a passed in ArrayList.  Words are assumed to 
	 * be delimited by spaces.
	 * 
	 * @param element - JSoup element to retrieve space delimited words from
	 * @param wordStorageArrayList - The ArrayList that will store the words
	 * @return void
	 */
	public void storeAllWordsFromElement(Element element, ArrayList<String> wordStorageArrayList) {
		String elementText = element.text();
		String cleanedElementText = removeNonLetterCharacters(elementText);
		String[] words = cleanedElementText.split(" ");
		
		for(int i = 0; i < words.length; i++) {
			wordStorageArrayList.add(words[i]);
		}
	}
	
	/**
	 *  Removes all non letter characters from the input String.
	 *  
	 * @param passedInString
	 * @return modifiedString
	 */
	public String removeNonLetterCharacters(String passedInString) {
		String modifiedString = passedInString.replaceAll("[^a-zA-Z ]", "");

		return modifiedString;
	}
	
}
