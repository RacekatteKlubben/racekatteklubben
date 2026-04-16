package com.example.racekatteklubben.infrastructur;
import com.example.racekatteklubben.domain.CatShowRegistration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatShowRegistrationRepository implements ICatShowRegistrationRepository {

    private final JdbcTemplate jdbcTemplate;

    public CatShowRegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createRegistration(CatShowRegistration registration) {
        String sql = """
            INSERT INTO catshow_registrations(catId, catShowId, registrationDate, status)
            VALUES (?, ?, ?, ?)
            """;

        try {
            jdbcTemplate.update(
                    sql,
                    registration.getCatId(),
                    registration.getCatShowId(),
                    registration.getRegistrationDate(),
                    registration.getStatus()
            );
        } catch (Exception e) {
            throw new RuntimeException("Fejl ved gemning i database: ");
        }
    }

    @Override
    public List<CatShowRegistration> findByCatId(int catId) {
        String sql = "SELECT * FROM catshow_registrations WHERE catId = ?";

        return jdbcTemplate.query(sql, new Object[]{catId}, (rs, rowNum) ->
                new CatShowRegistration(
                        rs.getInt("registrationId"),
                        rs.getInt("catId"),
                        rs.getInt("catShowId"),
                        rs.getTimestamp("registrationDate").toLocalDateTime(),
                        rs.getString("status")
                )
        );
    }

    @Override
    public List<CatShowRegistration> findByCatShowId(int catShowId) {
        String sql = "SELECT * FROM catshow_registrations WHERE catShowId = ?";

        return jdbcTemplate.query(sql, new Object[]{catShowId}, (rs, rowNum) ->
                new CatShowRegistration(
                        rs.getInt("registrationId"),
                        rs.getInt("catId"),
                        rs.getInt("catShowId"),
                        rs.getTimestamp("registrationDate").toLocalDateTime(),
                        rs.getString("status")
                )
        );
    }

    @Override
    public void deleteRegistration(int registrationId) {
        String sql = "DELETE FROM catshow_registrations WHERE registrationId = ?";
        jdbcTemplate.update(sql, registrationId);
    }
}