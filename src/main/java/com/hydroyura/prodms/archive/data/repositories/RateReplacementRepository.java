package com.hydroyura.prodms.archive.data.repositories;

import com.hydroyura.prodms.archive.data.entities.DBRateReplacement;
import com.hydroyura.prodms.archive.data.entities.keys.DBRateReplacementKey;
import org.springframework.stereotype.Repository;

@Repository(value = "RateReplacementRepository")
public interface RateReplacementRepository extends BaseRepository<DBRateReplacement, DBRateReplacementKey> {}