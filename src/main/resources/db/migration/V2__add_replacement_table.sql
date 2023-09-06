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
)


