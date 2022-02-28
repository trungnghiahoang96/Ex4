package Util.FileParsing;

import wrappers.EntriesList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    private final EntriesList rawList;

    public FileParser(String fileName, String parseToken) {
        this.rawList = rawEntries(fileName, parseToken);
    }


    private EntriesList rawEntries(String fileName,
                                   String parseToken) {

        EntriesList entriesList = new EntriesList();

        List<String> entries;

        // List of all raw lines
        entries = fileEntries(fileName);

        for (String rawEntry : entries) {

            addEntry(parseToken, entriesList, rawEntry);
        }

        return entriesList;
    }

    /**
     * @param fileName csv input file
     *                 return List of raw lines after readAllLines
     */
    private List<String> fileEntries(String fileName) {

        List<String> entries = new ArrayList<>();

        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String headers = br.readLine();

            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
        } catch (IOException e) {

            new IllegalArgumentException("Error Reading File : " + fileName);
        }

        return entries;
    }


    /**
     * @param parseToken  separator of each line (like , tab or :: ....)
     * @param entriesList final result type ArrayList<List<String>
     * @param rawEntry    raw line need to be parsed
     */

    private void addEntry(String parseToken,
                          EntriesList entriesList,
                          String rawEntry) {

        List<String> entry = Arrays.asList(rawEntry.split(parseToken));
        List<String> cleanEntry = cleanList(entry);

        entriesList.add(cleanEntry);
    }

    // clean List of entry
    private List<String> cleanList(List<String> entry) {
        List<String> entryList = new ArrayList<>();

        for (String element : entry) {
            entryList.add(element.trim().replaceAll("\\s+", " "));
        }
        return entryList;
    }


    public List<List<String>> getRawList() {
        return rawList;
    }
}
