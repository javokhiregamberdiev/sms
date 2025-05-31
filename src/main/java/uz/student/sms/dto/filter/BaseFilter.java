package uz.student.sms.dto.filter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseFilter implements Serializable {

    String search = "";

    public static String toQuerySearch(String search) {
        return "%" + search.toLowerCase() + "%";
    }
}
