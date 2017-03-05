package grisbiweb.server.model;

import lombok.Data;

@Data
public class Party {

    private String id;
    private String name;
    private String description;

    public Long getIdLong() {
        return Long.valueOf(getId());
    }
}
