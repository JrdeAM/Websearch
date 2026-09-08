import interfaces.QueryObserver;
import impl.KeywordQueryFilter;

public class Snooper {

    public Snooper(WebSearchModel model) {

        // Usuário 1
        model.addQueryObserver(
                new QueryObserver() {

                    @Override
                    public void onQuery(String query) {
                        System.out.println("Usuário 1 recebeu: " + query);
                    }

                },
                new KeywordQueryFilter("king"));

        // Usuário 2
        model.addQueryObserver(
                new QueryObserver() {

                    @Override
                    public void onQuery(String query) {
                        System.out.println("Usuário 2 recebeu: " + query);
                    }

                },
                new KeywordQueryFilter("queen"));

    }
}