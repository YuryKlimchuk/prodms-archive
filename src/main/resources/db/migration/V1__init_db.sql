-- create tables parts: BEGIN
CREATE TABLE parts(
    number VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version SERIAL NOT NULL,
    type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created DATE NOT NULL,
    last_update DATE NOT NULL,
    other_file VARCHAR(255),
    pdf VARCHAR(255),
    info TEXT
);
-- create tables parts: END
-- create tables parts_changes: BEGIN
CREATE TABLE parts_changes(
    part_number VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    last_update DATE NOT NULL,
    operation VARCHAR(255) NOT NULL,
    version SERIAL NOT NULL,
    field_name VARCHAR(255),
    field_value VARCHAR(255),
    object_json TEXT NOT NULL,

    PRIMARY KEY (part_number, version),
    CONSTRAINT fk_parts2parts_changes
        FOREIGN KEY (part_number)
        REFERENCES parts (number)
);
-- create tables parts_changes: END

CREATE TABLE assembly_rates(
    assembly_id VARCHAR(255) NOT NULL,
    element_id VARCHAR(255) NOT NULL,
    replacement bigint NOT NULL,
    count bigint,
    priority bigint,
    PRIMARY KEY (assembly_id, element_id, replacement),
    CONSTRAINT fki7d2y2lh5v32s1wu8i2lrrrfk
        FOREIGN KEY (element_id)
        REFERENCES parts (number),
    CONSTRAINT fknxg4bmya15yuvnhm7ejsmdb7a
        FOREIGN KEY (assembly_id)
        REFERENCES parts (number)
)



