package impl;

import interfaces.QueryFilter;

public class FriendQueryFilter implements QueryFilter {

    @Override
    public boolean filter(String query) {
        return query.toLowerCase().contains("friend");
    }
}
