package uz.student.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDTO {

    @JsonProperty(value = "name")
    private String name;
}
