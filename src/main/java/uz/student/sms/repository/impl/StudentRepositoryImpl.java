package uz.student.sms.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import uz.student.sms.domain.Student;
import uz.student.sms.dto.filter.BaseFilter;
import uz.student.sms.repository.custom.StudentRepositoryCustom;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentsByFilter(BaseFilter filter) {

        StringBuilder sql = new StringBuilder();
        sql.append("select s from Student s ");
        sql.append(" join s.user u");
        sql.append(" where s.deleted = false ");

        if (StringUtils.hasText(filter.getSearch())) {
            sql.append(" and (lower(u.firstName) like :search ");
            sql.append(" or lower(u.lastName) like :search ");
            sql.append(" or lower(u.username) like :search ");
            sql.append(" or lower(u.phone) like :search ");
            sql.append(" or lower(u.email) like :search) ");
        }

        sql.append(" order by s.createdDate desc ");

        TypedQuery<Student> query = entityManager.createQuery(sql.toString(), Student.class);

        if (StringUtils.hasText(filter.getSearch())) {
            query.setParameter("search", BaseFilter.toQuerySearch(filter.getSearch()));
        }
        return query.getResultList();
    }
}
