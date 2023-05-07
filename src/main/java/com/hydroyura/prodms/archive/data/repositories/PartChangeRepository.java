package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.DBPartChangeKey;
import org.springframework.stereotype.Repository;

@Repository(value = "PartChangeRepository")
public interface PartChangeRepository extends BaseRepository<DBPartChange, DBPartChangeKey> {}
