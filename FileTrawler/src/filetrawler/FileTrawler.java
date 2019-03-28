//Isaac Lichter
package filetrawler;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileTrawler {

    private List<String> results;

    public FileTrawler() {
        results = new LinkedList<>();
    }

    List<String> findFileLocation(String fileName, String startDirectory) {
        results.clear();

        File start = new File(startDirectory);
        if (start.isDirectory()) {
            recursiveAddToList(fileName, startDirectory);
        }

        return results;
    }

    private void recursiveAddToList(String fileName, String startDirectory) {

        File start = new File(startDirectory);

        File[] list = start.listFiles();
        
        if (list == null) {
            return;
        }

        for (File file : list) {
            if (file.getName().equals(fileName)) {
                results.add(file.getAbsolutePath());
            }

            if (file.isDirectory()) {
                recursiveAddToList(fileName, file.getAbsolutePath());
            }

        }

    }

}
