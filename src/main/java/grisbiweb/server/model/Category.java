package grisbiweb.server.model;

import lombok.Data;

@Data
public class Category {

	private String id;
	private String name;

	public Long getIdLong() {
		return Long.valueOf(getId());
	}
}
