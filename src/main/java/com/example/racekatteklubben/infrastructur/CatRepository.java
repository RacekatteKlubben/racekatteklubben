package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.Cat;
import com.example.racekatteklubben.domain.Race;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatRepository implements ICatRepository {

    private final JdbcTemplate jdbcTemplate;

    public CatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createCat(Cat cat, int memberId) {
        String sql = "INSERT INTO cats(name, mom, dad, color, race, memberId) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                cat.getName(),
                cat.getMom(),
                cat.getDad(),
                cat.getColor(),
                cat.getRace().name(),
                memberId
        );
    }

    public List<Cat> findAllCats() {
        String sql = "SELECT * FROM cats";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(
                        rs.getInt("catId"),
                        rs.getString("name"),
                        rs.getString("mom"),
                        rs.getString("dad"),
                        rs.getString("color"),
                        Race.valueOf(rs.getString("race")),
                        rs.getInt("memberId")
                )
        );
    }

    public List<Cat> findCatsByMemberId(int memberId) {
        String sql = "SELECT * FROM cats where memberId = ?";

        return jdbcTemplate.query(sql, new Object[]{memberId}, (rs, rowNum) ->
                new Cat(
                        rs.getInt("catId"),
                        rs.getString("name"),
                        rs.getString("mom"),
                        rs.getString("dad"),
                        rs.getString("color"),
                        Race.valueOf(rs.getString("race")),
                        rs.getInt("memberId")
                )
        );
    }


}


