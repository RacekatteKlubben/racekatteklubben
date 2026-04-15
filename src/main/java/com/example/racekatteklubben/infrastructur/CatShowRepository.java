package com.example.racekatteklubben.infrastructur;

import com.example.racekatteklubben.domain.CatShow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatShowRepository implements ICatShowRepository {

    private final JdbcTemplate jdbcTemplate;

    public CatShowRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCatShow(CatShow catShow) {
        String sql = "INSERT INTO catshows(title, location, date) VALUES (?, ?, ?)";

        jdbcTemplate.update(
                sql,
                catShow.getTitle(),
                catShow.getLocation(),
                catShow.getDate()
        );
    }

    @Override
    public List<CatShow> findAllCatShows() {
        String sql = "SELECT * FROM catshows";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new CatShow(
                        rs.getInt("catShowId"),
                        rs.getString("title"),
                        rs.getString("location"),
                        rs.getTimestamp("date").toLocalDateTime()
                )
        );
    }

    @Override
    public CatShow findCatShowById(int catShowId) {
        String sql = "SELECT * FROM catshows WHERE catShowId = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{catShowId}, (rs, rowNum) ->
                new CatShow(
                        rs.getInt("catShowId"),
                        rs.getString("title"),
                        rs.getString("location"),
                        rs.getTimestamp("date").toLocalDateTime()
                )
        );
    }
}
