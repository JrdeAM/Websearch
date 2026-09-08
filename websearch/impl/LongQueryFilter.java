package impl;

import interfaces.QueryFilter;

public class LongQueryFilter implements QueryFilter {

    @Override
    public boolean filter(String query) {
        return query.length() > 60;
    }
}