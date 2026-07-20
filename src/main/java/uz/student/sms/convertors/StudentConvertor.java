package uz.student.sms.convertors;

import org.springframework.stereotype.Component;
import uz.student.sms.domain.Student;
import uz.student.sms.dto.student.StudentDTO;
import uz.student.sms.test.Converter;

@Component
public class StudentConvertor extends Converter<StudentDTO, Student> {

    public StudentConvertor() {
        super(studentDTO -> {
            Student student = new Student();
            student.setDateOfBirth(studentDTO.getDateOfBirth());
            student.setCardId(studentDTO.getCardId());
            student.setGender(studentDTO.getGender());
            return student;
        }, student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setGender(student.getGender());
            studentDTO.setCardId(student.getCardId());
            return studentDTO;
        });
    }
}
