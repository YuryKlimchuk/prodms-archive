package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.dto.DTORate;

import java.util.Collection;
import java.util.Optional;

public interface IRateService {

    Collection<DTORate> getAllRates(String assemblyNumber);
    Collection<DTORate> getDefaultRates(String assemblyNumber);
    Collection<DTOPart> getAssemblies(String elementNumber);

    Optional<DTORate> create(String assemblyNumber, String elementNumber, long count);

    boolean delete(String assemblyNumber, String elementNumber);

    boolean changeCount(String assemblyNumber, String elementNumber, long newCount);

    boolean addReplacement(String number, String subNumber, String replacementNumber);

    boolean removeReplacement(String number, String subNumber, String replacementNumber);

    boolean updateReplacementPriority(String number, String subNumber, String replacementNumber, int priority);
}
