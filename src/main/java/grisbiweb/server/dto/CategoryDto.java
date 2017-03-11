package grisbiweb.server.dto;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class CategoryDto {

    private Long idCategory;

    private Long idSubCategory;

    private String nameCategory;

    private String nameSubCategory;

    public String getCompleteId() {
        StringBuilder compelteId = new StringBuilder(String.valueOf(idCategory));
        if (idSubCategory != null) {
            compelteId.append('-').append(idSubCategory);
        }
        return compelteId.toString();
    }

    public String getCompleteName() {
        StringBuilder completeName = new StringBuilder(nameCategory);
        if (StringUtils.isNotEmpty(nameSubCategory)) {
            completeName.append(" : ").append(nameSubCategory);
        }
        return completeName.toString();
    }

}
