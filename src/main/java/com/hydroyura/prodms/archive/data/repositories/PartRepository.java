package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import org.springframework.stereotype.Repository;

@Repository(value = "PartRepository")
public interface PartRepository extends BaseRepository<DBPart, String> {}