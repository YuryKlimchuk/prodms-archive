package com.hydroyura.prodms.archive.services.rates;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBRate;
import com.hydroyura.prodms.archive.data.entities.DBRateReplacement;
import com.hydroyura.prodms.archive.data.entities.keys.DBRateKey;
import com.hydroyura.prodms.archive.data.entities.QDBRate;
import com.hydroyura.prodms.archive.data.entities.keys.DBRateReplacementKey;
import com.hydroyura.prodms.archive.data.repositories.BaseRepository;
import com.hydroyura.prodms.archive.dto.DTOPart;
import com.hydroyura.prodms.archive.dto.DTORate;
import com.hydroyura.prodms.archive.services.predicates.IPredicateGenerator;
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


    @Autowired @Qualifier(value = "PartRatePredicateGenerator")
    private IPredicateGenerator partRatePredicateGenerator;

    @Autowired @Qualifier(value = "RateRepository")
    private BaseRepository<DBRate, DBRateKey> rateRepository;

    @Autowired @Qualifier(value = "PartRepository")
    private BaseRepository<DBPart, String> partRepository;

    @Autowired @Qualifier(value = "RateReplacementRepository")
    private BaseRepository<DBRateReplacement, DBRateReplacementKey> rateReplacementRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Collection<DTORate> getAllRates(String assemblyNumber) {
        Predicate predicate = QDBRate.dBRate.assembly.number.eq(assemblyNumber);
        return StreamSupport.stream(rateRepository.findAll(predicate).spliterator(), false)
                    .map(item -> mapper.map(item, DTORate.class))
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<DTORate> getDefaultRates(String assemblyNumber) {
        Collection<DTORate> allRates = getAllRates(assemblyNumber);
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
    public Collection<DTOPart> getAssemblies(String elementNumber) {
        Predicate predicate = QDBRate.dBRate.element.number.eq(elementNumber);
        return StreamSupport.stream(rateRepository.findAll(predicate).spliterator(), false)
                .map(item -> mapper.map(item.getAssembly(), DTOPart.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DTORate> create(String assemblyNumber, String elementNumber, long count) {
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
    public boolean delete(String assemblyNumber, String elementNumber) {
        Map<String, String> params = Map.of(
                "assembly-number", assemblyNumber,
                "element-number", elementNumber
        );

        Predicate predicate = partRatePredicateGenerator.generate(params);

        try {
            Collection<DBRateKey> rateKeysForDeleting = StreamSupport.stream(rateRepository.findAll(predicate).spliterator(), true)
                    .map(DBRate::getKey)
                    .collect(Collectors.toList());
            rateRepository.deleteAllById(rateKeysForDeleting);
        } catch (Exception e) {
            logger.error("Error while removing DB rates");
            return false;
        }
        return true;
    }

    @Override
    public boolean changeCount(String assemblyNumber, String elementNumber, long newCount) {
        Map<String, String> params = Map.of(
                "assembly-number", assemblyNumber,
                "element-number", elementNumber
        );

        Predicate predicate = partRatePredicateGenerator.generate(params);
        DBRate rate = rateRepository.findAll(predicate).iterator().next();

        rate.setCount(newCount);
        rateRepository.save(rate);
        return true;
    }

    @Override
    public boolean addReplacement(String assemblyNumber, String elementNumber, String replacementNumber) {
        DBRateKey rateKey = new DBRateKey().setAssemblyId(assemblyNumber).setElementId(elementNumber);

        if (!rateRepository.existsById(rateKey)) {
            logger.info("rate not exist");
            return false;
        };

        DBRateReplacementKey rateReplacementKey = new DBRateReplacementKey()
                .setReplacementId(replacementNumber)
                .setAssemblyId(assemblyNumber)
                .setElementId(elementNumber);

        DBRateReplacement rateReplacement = new DBRateReplacement()
                .setKey(rateReplacementKey)
                .setAssembly(partRepository.findById(assemblyNumber).get())
                .setElement(partRepository.findById(elementNumber).get())
                .setReplacement(partRepository.findById(replacementNumber).get())
                .setPriority(0L);

        try {
            rateReplacementRepository.save(rateReplacement);
        } catch (Exception e) {
            logger.error("error while adding replacement to rate", e);
            return false;
        }

        return true;
    }


}
