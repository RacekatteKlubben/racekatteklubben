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
        String sql = "INSERT INTO cats(name, mom, dad, color, race, memberId, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                cat.getName(),
                cat.getMom(),
                cat.getDad(),
                cat.getColor(),
                cat.getRace().name(),
                memberId,
                cat.getImage()
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
                        rs.getInt("memberId"),
                        rs.getBytes("image")
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
                        rs.getInt("memberId"),
                        rs.getBytes("image")
                )
        );
    }

    @Override
    public Cat findById(int catId) {
        String sql = "SELECT * FROM cats WHERE catId = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{catId}, (rs, rowNum) ->
                new Cat(
                        rs.getInt("catId"),
                        rs.getString("name"),
                        rs.getString("mom"),
                        rs.getString("dad"),
                        rs.getString("color"),
                        Race.valueOf(rs.getString("race")),
                        rs.getInt("memberId"),
                        rs.getBytes("image")
                )
        );
    }

    public void updateCat(Cat cat) {
        String Sql = "UPDATE cats SET name= ?, mom = ?, dad = ?, color = ?, race = ?, image = ? WHERE catId = ? AND memberId = ?";

        int rows = jdbcTemplate.update(Sql,
                cat.getName(),
                cat.getMom(),
                cat.getDad(),
                cat.getColor(),
                cat.getRace().name(),
                cat.getImage(),
                cat.getCatId(),
                cat.getMemberId()
        );
    }

    public void deleteCat(int catId){
        String sql = "DELETE FROM cats WHERE catId = ?";

        jdbcTemplate.update(sql,catId);
    }
}


