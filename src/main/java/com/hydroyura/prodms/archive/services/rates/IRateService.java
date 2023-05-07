package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.dto.DTORate;

import java.util.Collection;
import java.util.Optional;

public interface IRateService {

    Collection<DTORate> getAllRates(String assemblyNumber);
    Collection<DTORate> getDefaultRates(String assemblyNumber);
    Collection<DTORate> getAssemblies(String elementNumber);

    Optional<DTORate> create(String assemblyNumber, String elementNumber, long count);


}
