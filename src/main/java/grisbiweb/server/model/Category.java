package grisbiweb.server.model;

import java.util.List;

import lombok.Data;

@Data
public class Category {

	private String id;
	private String name;
	
	private List<Category> subCategories;
	
	public Long getIdLong() {
		return Long.valueOf(getId());
	}
}
