package grisbiweb.server.rest.model.response;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
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

}
