package com.ra.web.model;

public class Category {
    private String id;
    private String name;
    private String parentId;
    private boolean status;

    public Category() {
    }

    public Category(String id, String name, String parentId, boolean status) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
