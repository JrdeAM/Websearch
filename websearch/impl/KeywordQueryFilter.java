package impl;
import interfaces.QueryFilter;


public class KeywordQueryFilter implements QueryFilter {

    private String keyword;

    public KeywordQueryFilter(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean filter(String query) {
        return query.contains(keyword);
    }
}