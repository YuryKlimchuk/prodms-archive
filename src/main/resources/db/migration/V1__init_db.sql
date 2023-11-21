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

CREATE TABLE parts_changes(
    part_number VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    last_update DATE NOT NULL,
    operation VARCHAR(255) NOT NULL,
    version SERIAL NOT NULL,
    object_json TEXT NOT NULL,

    PRIMARY KEY (part_number, version),
    CONSTRAINT fk_parts2parts_changes
        FOREIGN KEY (part_number)
        REFERENCES parts (number)
);

CREATE TABLE assembly_rates(
    assembly_id VARCHAR(255) NOT NULL,
    element_id VARCHAR(255) NOT NULL,
    count bigint NOT NULL,

    PRIMARY KEY (assembly_id, element_id),

    CONSTRAINT asm_rates2parts__element_id2number
        FOREIGN KEY (element_id)
        REFERENCES parts (number),
    CONSTRAINT asm_rates2parts__assembly_id2number
        FOREIGN KEY (assembly_id)
        REFERENCES parts (number)
);

CREATE TABLE rate_replacements(
    assembly_id VARCHAR(255) NOT NULL,
    element_id VARCHAR(255) NOT NULL,
    replacement_id VARCHAR(255) NOT NULL,
    priority bigint NOT NULL,

    PRIMARY KEY (assembly_id, element_id, replacement_id),

    CONSTRAINT rate_replacements2parts__element_id2number
        FOREIGN KEY (element_id)
        REFERENCES parts (number),
    CONSTRAINT rate_replacements2parts__assembly_id2number
        FOREIGN KEY (assembly_id)
        REFERENCES parts (number),
    CONSTRAINT rate_replacements2parts__replacement_id2number
        FOREIGN KEY (replacement_id)
        REFERENCES parts (number)
);



