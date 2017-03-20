package com.hardtofind.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zdrillings on 3/19/17.
 */
public class SearchResult {

    private List<String> results;

    private Long searchTime;

    public SearchResult() {
        // Jackson deserialization
    }

    public SearchResult(long searchTime, List<String> results) {
        this.searchTime = searchTime;
        this.results = results;
    }

    @JsonProperty
    public List<String> getresults() {
        return results;
    }

    @JsonProperty("search_time")
    public Long getSearchTime() {
        return searchTime;
    }
}
