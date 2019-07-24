package domain;

import java.util.List;

public class QueryIds {
    public QueryIds(List<Integer> ids) {
        this.ids = ids;
    }

    public QueryIds() {

    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    private List<Integer> ids;

}
