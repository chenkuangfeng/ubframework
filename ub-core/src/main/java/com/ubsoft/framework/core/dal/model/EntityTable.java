package com.ubsoft.framework.core.dal.model;

import java.util.List;
import java.util.Map;

public class EntityTable {
    private String name;
    private EntityColumn  pkColumn;
    private EntityColumn  verColumn;
    private List<EntityColumn> entityColumns;

    public Map<String, EntityColumn> getMapEntityColumns() {
        return mapEntityColumns;
    }

    public void setMapEntityColumns(Map<String, EntityColumn> mapEntityColumns) {
        this.mapEntityColumns = mapEntityColumns;
    }

    private Map<String,EntityColumn> mapEntityColumns;
    public String getName() {
        return name;
    }



    public List<EntityColumn> getEntityColumns() {
        return entityColumns;
    }

    public void setEntityColumns(List<EntityColumn> entityColumns) {
        this.entityColumns = entityColumns;
    }
    public void setName(String name) {
        this.name = name;
    }

    public EntityColumn getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(EntityColumn pkColumn) {
        this.pkColumn = pkColumn;
    }

    public EntityColumn getVerColumn() {
        return verColumn;
    }

    public void setVerColumn(EntityColumn verColumn) {
        this.verColumn = verColumn;
    }
}
