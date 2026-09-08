import java.io.File;
import interfaces.QueryFilter;
import impl.KeywordQueryFilter;


/**
 * Launch the web-search example
 */
public class Main {
    public static void main(String[] args) {
        // Source file (in the project's data/ folder)
        File inputTextFile = new File("data/Hamlet.txt");

        // Build object graph
        QueryFilter filter = new KeywordQueryFilter("king");
        WebSearchModel model = new WebSearchModel(inputTextFile, filter);
        Snooper snoop = new Snooper(model);

        // Execute
        model.pretendToSearch();
    }
}
