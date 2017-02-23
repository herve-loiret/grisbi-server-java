package grisbiweb.server.model;

import lombok.Data;

@Data
public class SubCategory {

    private String id;
    private String idCategory;
    private String name;

    public Long getIdLong() {
        return Long.valueOf(id);
    }

}
