package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTORate;

import java.util.Collection;
import java.util.Optional;

public interface IRateService {

    Optional<DTORate> create(String assemblyNumber, String elementNumber, long count);
    Collection<DTORate> getRates(String assemblyNumber);
    Collection<DTOPart> getAssemblies(String elementNumber);
    boolean changeCount(String assemblyNumber, String elementNumber, long newCount);






    Collection<DTORate> getDefaultRates(String assemblyNumber);


    boolean delete(String assemblyNumber, String elementNumber);


    boolean addReplacement(String number, String subNumber, String replacementNumber);

    boolean removeReplacement(String number, String subNumber, String replacementNumber);

    boolean updateReplacementPriority(String number, String subNumber, String replacementNumber, int priority);

}
