package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;

import org.apache.commons.lang3.StringUtils;

@ApiModel
public class CategoryResponse {
	
	private Long idCategory;

	private Long idSubCategory;

	private String nameCategory;

	private String nameSubCategory;

	public String getCompleteId() {
		String compelteId = String.valueOf(idCategory);
		if (idSubCategory != null) {
			compelteId = compelteId + "-" + idSubCategory;
		}
		return compelteId;
	}

	public String getCompleteName() {
		String completeName = nameCategory;
		if (StringUtils.isNotEmpty(nameSubCategory)) {
			completeName += " : " + nameSubCategory;
		}
		return completeName;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public Long getIdSubCategory() {
		return idSubCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public String getNameSubCategory() {
		return nameSubCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public void setIdSubCategory(Long idSubCategory) {
		this.idSubCategory = idSubCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public void setNameSubCategory(String nameSubCategory) {
		this.nameSubCategory = nameSubCategory;
	}
}
