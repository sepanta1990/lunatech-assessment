package com.lunatech.dao.bean;

public class GenreCountBean {
    private long count;
    private String genreName;

    public GenreCountBean(long count, String genreName) {
        this.count = count;
        this.genreName = genreName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
