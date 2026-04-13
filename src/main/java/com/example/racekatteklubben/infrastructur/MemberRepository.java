package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository implements IMemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createMember(Member member) {
        String sql = "INSERT INTO members(name, password, email, phonenumber) VALUES (?,?,?,?)";

        jdbcTemplate.update(
                sql,
                member.getName(),
                member.getPassword(),
                member.getEmail(),
                member.getPhoneNumber()
        );
    }

    public void updateMember(Member member) {
        String Sql = "UPDATE members SET name= ?, password = ?, email = ?, phonenumber = ? WHERE memberid = ?";

        jdbcTemplate.update(Sql,
                member.getName(),
                member.getPassword(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getMemberId()
        );
    }

    @Override
    public Member findMemberByEmail(String email) {
        String sql = """
                SELECT memberId, name, password, email, phoneNumber 
                FROM members 
                WHERE email = ?
                """;

        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) ->
                new Member(
                        rs.getInt("memberId"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        true
                )
        );
    }
}
