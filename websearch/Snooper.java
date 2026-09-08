import interfaces.QueryObserver;
import impl.FriendQueryFilter;
import impl.LongQueryFilter;

public class Snooper {

    public Snooper(WebSearchModel model) {

        // Observador 1 - procura "friend"
        model.addQueryObserver(

                new QueryObserver() {

                    @Override
                    public void onQuery(String query) {
                        System.out.println("Oh Yes! " + query);
                    }

                },

                new FriendQueryFilter()

        );

        // Observador 2 - consultas maiores que 60 caracteres
        model.addQueryObserver(

                new QueryObserver() {

                    @Override
                    public void onQuery(String query) {
                        System.out.println("So long " + query);
                    }

                },

                new LongQueryFilter()

        );

    }
}