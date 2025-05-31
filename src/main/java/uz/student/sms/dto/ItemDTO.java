package uz.student.sms.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 13214243L;

    private Long id;

    private String name;
}
