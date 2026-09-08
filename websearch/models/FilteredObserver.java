package models;

import interfaces.QueryFilter;
import interfaces.QueryObserver;

public class FilteredObserver {

    private QueryObserver observer;
    private QueryFilter filter;

    public FilteredObserver(QueryObserver observer, QueryFilter filter) {
        this.observer = observer;
        this.filter = filter;
    }

    public boolean accepts(String query) {
        return filter.filter(query);
    }

    public void notify(String query) {
        observer.onQuery(query);
    }
}