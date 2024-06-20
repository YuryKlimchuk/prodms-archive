DROP TABLE IF EXISTS unit_types;

DROP TABLE IF EXISTS units;
CREATE TABLE units(
    number          	VARCHAR(255)    	NOT NULL    PRIMARY KEY,
    name            	VARCHAR(255)    	NOT NULL,
    version         	SMALLSERIAL         NOT NULL,
    type            	VARCHAR(50)	     	NOT NULL,
    status          	VARCHAR(50)	     	NOT NULL,
    created         	DATE          		NOT NULL,
    updated	     		DATE          		NOT NULL,
    comment            	TEXT
);

DROP TABLE IF EXISTS hist_units;
CREATE TABLE hist_units(
	id					BIGSERIAL			NOT NULL	PRIMARY KEY,
	operation          	VARCHAR(50)     	NOT NULL,
	------------------------------------------------
	unit_number         VARCHAR(255)    	NOT NULL,
    name            	VARCHAR(255)    	NOT NULL,
    version         	SMALLSERIAL         NOT NULL,
    type            	VARCHAR(50)     	NOT NULL,
    status          	VARCHAR(50)     	NOT NULL,
    created         	DATE   		       	NOT NULL,
    updated	     		DATE	          	NOT NULL,
    comment            	TEXT,

    CONSTRAINT fk_histUnits_Units
            FOREIGN KEY (unit_number)
            REFERENCES units (number)
);

DROP TABLE IF EXISTS assemblies;
CREATE TABLE assemblies(
    assembly_id 		VARCHAR(255) 		NOT NULL,
    unit_id 			VARCHAR(255) 		NOT NULL,
    count 				SMALLSERIAL 		NOT NULL,
	version         	SMALLSERIAL         NOT NULL,
	status          	VARCHAR(50)     	NOT NULL,

    PRIMARY KEY (assembly_id, unit_id),

    CONSTRAINT fk_assemblies_units_unitId
        FOREIGN KEY (unit_id)
        REFERENCES units (number) ON DELETE CASCADE,

    CONSTRAINT fk_assemblies_units_assemblyId
        FOREIGN KEY (assembly_id)
        REFERENCES units (number) ON DELETE CASCADE
);

DROP TABLE IF EXISTS hist_assemblies;
CREATE TABLE hist_assemblies(
	operation          	VARCHAR(50)     	NOT NULL,
	------------------------------------------------
    assembly_id 		VARCHAR(255) 		NOT NULL,
    unit_id 			VARCHAR(255) 		NOT NULL,
    count 				SMALLSERIAL 		NOT NULL,
	version         	SMALLSERIAL         NOT NULL,
	status          	VARCHAR(50)     	NOT NULL,

	PRIMARY KEY (assembly_id, unit_id, version),

	CONSTRAINT fk_histAssemblies_units_unitId
		FOREIGN KEY (unit_id)
		REFERENCES units (number) ON DELETE CASCADE,

	CONSTRAINT fk_histAssemblies_units_assemblyId
		FOREIGN KEY (assembly_id)
		REFERENCES units (number) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION save_unit_to_history() RETURNS TRIGGER AS $save_unit_to_history$
	BEGIN
		IF (TG_OP = 'DELETE') THEN
			INSERT INTO hist_units(operation, unit_number, name, version, type, status, created, updated, comment)
				VALUES ('DELETE', OLD.number, OLD.name, OLD.version, OLD.type, OLD.status, OLD.created, OLD.updated, OLD.comment);
            RETURN OLD;
        ELSIF (TG_OP = 'UPDATE') THEN
			INSERT INTO hist_units(operation, unit_number, name, version, type, status, created, updated, comment)
				VALUES ('UPDATE', NEW.number, NEW.name, NEW.version, NEW.type, NEW.status, NEW.created, NEW.updated, NEW.comment);
            RETURN NEW;
        ELSIF (TG_OP = 'INSERT') THEN
			INSERT INTO hist_units(operation, unit_number, name, version, type, status, created, updated, comment)
				VALUES ('CREATE', NEW.number, NEW.name, NEW.version, NEW.type, NEW.status, NEW.created, NEW.updated, NEW.comment);
            RETURN NEW;
        END IF;
        RETURN NULL;
	END;
$save_unit_to_history$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER save_unit_to_history
	AFTER INSERT OR UPDATE OR DELETE ON units
	FOR EACH ROW EXECUTE FUNCTION save_unit_to_history();