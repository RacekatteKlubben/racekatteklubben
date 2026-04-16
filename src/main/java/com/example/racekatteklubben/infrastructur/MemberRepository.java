package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository implements IMemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
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

    @Override
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

    public List<Member> memberSearch(String query) {
        String sql = "SELECT memberId, name, email, password, phoneNumber " +
                "FROM members " +
                "WHERE name LIKE ? OR email LIKE ?";

        String searchParam = "%" + query + "%";

        return jdbcTemplate.query(sql, new Object[]{searchParam, searchParam}, (rs, rowNum) ->
                new Member(
                        rs.getInt("memberId"),
                        rs.getString("name"),
                        null,
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        true
                )
        );
    }

    public void deleteMember(int memberId){
        String sql = "DELETE FROM members WHERE memberid = ?";

        jdbcTemplate.update(sql, memberId);
    }

    public List<Member> findAllMembers() {
        String sql = "SELECT * FROM members";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Member(
                        rs.getInt("memberId"),
                        rs.getString("name"),
                        null,
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        true
                )
        );
    }
}
