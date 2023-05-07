package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBRate;
import com.hydroyura.prodms.archive.data.entities.DBRateKey;
import org.springframework.stereotype.Repository;

@Repository(value = "RateRepository")
public interface RateRepository extends BaseRepository<DBRate, DBRateKey> {}
