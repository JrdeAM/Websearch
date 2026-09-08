import impl.KeywordQueryFilter;
import interfaces.QueryObserver;

/**
 * Watches the search queries
 */
public class Snooper {
    private final WebSearchModel model;

    public Snooper(WebSearchModel model) {
        this.model = model;

        model.addQueryObserver(
                new QueryObserver() {

                    @Override
                    public void onQuery(String query) {
                        System.out.println("Query: " + query);
                    }

                },
                new KeywordQueryFilter("king"));
    }
}
