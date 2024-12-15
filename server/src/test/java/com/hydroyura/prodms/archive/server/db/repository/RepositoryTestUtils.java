package com.hydroyura.prodms.archive.server.db.repository;

import com.hydroyura.prodms.archive.server.db.entity.Unit;
import java.time.Instant;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryTestUtils {

    public static final String UNIT_NAME_1 = "NAME_1";
    public static final String UNIT_NAME_2 = "NAME_2";
    public static final String UNIT_NAME_3 = "NAME_3";
    public static final String UNIT_NAME_4 = "NAME_4";

    public static final String UNIT_NUMBER_PATTERN = "NUMBER_%s";
    public static final String UNIT_NUMBER_PATTERN_1 = "CITY_%s";
    public static final String UNIT_NUMBER_PATTERN_2 = "TOWER_%s";
    public static final String UNIT_NUMBER_1 = "NUMBER_1";
    public static final String UNIT_NUMBER_2 = "NUMBER_2";
    public static final String UNIT_NUMBER_3 = "NUMBER_3";
    public static final String UNIT_NUMBER_4 = "NUMBER_4";


    public static final String UNIT_SQL_SELECT_COUNT_OF_ACTIVE_BY_NUMBER =
        "echo \"SELECT COUNT(number) from units WHERE units.number = '%s' AND units.is_active = 'true';\" | psql -U test-pg-user -d test-archive";
    public static final String UNIT_SQL_TRUNCATE =
        "echo \"TRUNCATE TABLE units;\" | psql -U test-pg-user -d test-archive";

    public static final String UNIT_SQL_CREATE_NUMBER_1 = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('NAME_1', 'NUMBER_1', 100, 100, 1, 1, 1, true, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";

    public static final String UNIT_SQL_CREATE_NUMBER_1_NOT_ACTIVE = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('NAME_1', 'NUMBER_1', 100, 100, 1, 1, 1, false, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";

    public static final String UNIT_SQL_CREATE_NUMBER_PARAM = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('NAME_1', '%s', 100, 100, 1, 1, 1, true, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";

    public static final String UNIT_SQL_CREATE_NUMBER_CREATED_PARAM = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('NAME_1', '%s', %s, 100, 1, 1, 1, true, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";

    public static final String UNIT_SQL_CREATE_NUMBER_NAME_PARAMS = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('%s', '%s', 100, 100, 1, 1, 1, true, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";

    public static final String UNIT_SQL_CREATE_NUMBER_NAME_TYPE_PARAMS = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('%s', '%s', 100, 100, 1, 1, %s, true, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";

    public static final String UNIT_SQL_CREATE_NUMBER_NAME_TYPE_STATUS_PARAMS = """
        echo "
            INSERT INTO units (name, number, created_at, updated_at, version, status, type, is_active, additional) \s
            VALUES ('%s', '%s', 100, 100, 1, %s, %s, true, 'some_additional');" | psql -U test-pg-user -d test-archive
   \s""";


    public static final String UNIT_HIST_SQL_SELECT_BY_NUMBER_AND_VERSION =
        "echo \"SELECT COUNT(number) from units_history WHERE units_history.number = '%s' AND units_history.version = %s;\" | psql -U test-pg-user -d test-archive";






    public static Unit generateUnit(String number, String name, Integer status, Integer type) {
        var now = Instant.now().getEpochSecond();
        return generateUnit(number, name, status, type, now, now);
    }

    public static Unit generateUnit(String number, String name, Integer status, Integer type, Long createdAt, Long updatedAt) {
        var unit = new Unit();
        unit.setName(name);
        unit.setNumber(number);
        unit.setCreatedAt(createdAt);
        unit.setUpdatedAt(updatedAt);
        unit.setVersion(1);
        unit.setStatus(status);
        unit.setType(type);
        unit.setIsActive(true);
        unit.setAdditional("some additional data");
        return unit;
    }

}
