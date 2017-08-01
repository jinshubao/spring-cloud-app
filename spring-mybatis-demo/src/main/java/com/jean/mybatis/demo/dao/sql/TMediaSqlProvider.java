package com.jean.mybatis.demo.dao.sql;

import org.apache.ibatis.jdbc.SQL;

public class TMediaSqlProvider {

    public String selectTutorById() {
        return new SQL() {
            {
                SELECT("t.tutor_id, t.name as tutor_name, email");
                SELECT("a.addr_id, street, city, state, zip, country");
                SELECT("course_id, c.name as course_name, description, start_date, end_date");
                FROM("TUTORS t");
                LEFT_OUTER_JOIN("addresses a on t.addr_id=a.addr_id");
                LEFT_OUTER_JOIN("courses c on t.tutor_id=c.tutor_id");
                WHERE("t.TUTOR_ID = #{id}");
            }
        }.toString();
    }
}
