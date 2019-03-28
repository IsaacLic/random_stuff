//Isaac Lichter
package filetrawler;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileTrawlerTest {
    
    @Test
    public void testFindFileLocation() {
        System.out.println("findFileLocation");
        String fileName = "murach";
        String startDirectory = "C:\\";
        FileTrawler instance = new FileTrawler();
        String expResult = "[C:\\murach]";
        List<String> result = instance.findFileLocation(fileName, startDirectory);
        assertEquals(expResult, result.toString());
    }
    
}
