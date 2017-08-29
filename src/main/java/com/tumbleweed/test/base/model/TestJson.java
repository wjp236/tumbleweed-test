package com.tumbleweed.test.base.model;

import java.util.List;

/**
 * Created by mylover on 8/16/16.
 */
public class TestJson {

    private String nextPage;
    private List<Model> ents;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public List<Model> getEnts() {
        return ents;
    }

    public void setEnts(List<Model> ents) {
        this.ents = ents;
    }
}
