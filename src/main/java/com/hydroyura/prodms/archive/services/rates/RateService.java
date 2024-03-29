package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBRate;
import com.hydroyura.prodms.archive.data.entities.keys.DBRateKey;
import com.hydroyura.prodms.archive.data.entities.QDBRate;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.data.entities.dto.DTORate;
import com.hydroyura.prodms.archive.services.rates.predicates.IPredicateGenerator;
import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service(value = "RateService")
public class RateService implements IRateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired @Qualifier(value = "RateRepository")
    private BaseRepository<DBRate, DBRateKey> rateRepository;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> partRepository;

    @Autowired @Qualifier(value = "PartRatePredicateGenerator")
    private IPredicateGenerator ratePredicateGenerator;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Optional<DTORate> create(String assemblyNumber, String elementNumber, long count) {
        logger.info("Start creation of rate, for assembly number = [{}], element number = [{}], count = [{}]", assemblyNumber, elementNumber, count);
        DBRate rate = new DBRate();
        rate.setCount(count);
        DBRateKey key = new DBRateKey();
        rate.setKey(key);
        rate.setAssembly(partRepository.getReferenceById(assemblyNumber));
        rate.setElement(partRepository.getReferenceById(elementNumber));
        DBRate savedRate = rateRepository.save(rate);
        return Optional.ofNullable(mapper.map(savedRate, DTORate.class));
    }

    @Override
    public Collection<DTORate> getRates(String assemblyNumber) {
        logger.info("Attempt to retrieve rates for assembly number = [{}]", assemblyNumber);
        Predicate predicate = ratePredicateGenerator.generate(Map.of("assembly-number", assemblyNumber));
        return StreamSupport.stream(rateRepository.findAll(predicate).spliterator(), false)
                .map(item -> mapper.map(item, DTORate.class))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<DTOPart> getAssemblies(String elementNumber) {
        logger.info("Attempt to retrieve assembly list for part number = [{}]", elementNumber);
        Predicate predicate = ratePredicateGenerator.generate(Map.of("element-number", elementNumber));
        return StreamSupport.stream(rateRepository.findAll(predicate).spliterator(), false)
                .map(item -> mapper.map(item, DTORate.class))
                .map(DTORate::getAssembly)
                .collect(Collectors.toList());
    }

    @Override
    public boolean changeCount(String assemblyNumber, String elementNumber, long newCount) {
        logger.info("Attempt to change count for part number = [{}], in assembly = [{}]", elementNumber, assemblyNumber);

        Optional<DBRate> currentRate = rateRepository.findById(new DBRateKey().setAssemblyId(assemblyNumber).setElementId(elementNumber));
        if (currentRate.isEmpty()) {
            logger.warn("Rate not exist");
            return false;
        }

        if (currentRate.get().getCount() == newCount) {
            logger.warn("Count wasn't updated, newCount == currentCount");
            return false;
        }

        rateRepository.save(currentRate.get().setCount(newCount));
        return true;
    }













































    @Override
    public Collection<DTORate> getDefaultRates(String assemblyNumber) {
        Collection<DTORate> allRates = getRates(assemblyNumber);
        Collection<DTORate> replacementRates = allRates.stream().filter(item -> item.getReplacement() > 0).collect(Collectors.toList());
        Collection<Long> replacementIds = replacementRates.stream().map(DTORate::getReplacement).collect(Collectors.toSet());

        Collection<DTORate> defaultReplacements = new ArrayList<>();
        replacementIds.forEach(id -> {
            Optional<DTORate> tmp = replacementRates.stream().filter(rate -> rate.getReplacement() == id && rate.getPriority() == 0).findAny();
            defaultReplacements.add(tmp.orElseThrow());
        });

        Collection<DTORate> result = allRates.stream().filter(item -> item.getReplacement() == 0).collect(Collectors.toList());
        result.addAll(defaultReplacements);
        return result;
    }

    @Override
    public boolean delete(String assemblyNumber, String elementNumber) {
        return false;
    }


    @Override
    public boolean addReplacement(String number, String subNumber, String replacementNumber) {
        return false;
    }

    @Override
    public boolean removeReplacement(String number, String subNumber, String replacementNumber) {
        return false;
    }

    @Override
    public boolean updateReplacementPriority(String number, String subNumber, String replacementNumber, int priority) {
        return false;
    }


}
