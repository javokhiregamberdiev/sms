package uz.student.sms.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import uz.student.sms.domain.Attendance;
import uz.student.sms.dto.filter.AttendanceFilter;
import uz.student.sms.repository.custom.AttendanceRepositoryCustom;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Repository
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Attendance> getListByFilter(AttendanceFilter filter) {
        LocalDateTime startOfMonth = LocalDateTime.of(filter.getYear(), filter.getMonth(), 1, 0, 0, 0);
        YearMonth yearMonth = YearMonth.of(filter.getYear(), filter.getMonth());
        LocalDateTime endOfMonth = LocalDateTime.of(filter.getYear(), filter.getMonth(), yearMonth.lengthOfMonth(), 23, 59, 59);

        StringBuilder sql = new StringBuilder();
        sql.append("select a from Attendance a ");
        sql.append(" join a.user u");
        sql.append(" where a.deleted = false ");

        sql.append(" and a.checkIn >=:fromDate");
        sql.append(" and a.checkIn <=:toDate");

        if (StringUtils.hasText(filter.getSearch())) {
            sql.append(" and lower(u.firstName) like :search");
        }

        sql.append(" order by a.createdDate desc ");

        TypedQuery<Attendance> query = entityManager.createQuery(sql.toString(), Attendance.class);

        query.setParameter("fromDate", startOfMonth);
        query.setParameter("toDate", endOfMonth);

        if (StringUtils.hasText(filter.getSearch())) {
            query.setParameter("search", AttendanceFilter.toQuerySearch(filter.getSearch()));
        }
        return query.getResultList();
    }
}
