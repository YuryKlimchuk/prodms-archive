package com.hydroyura.prodms.archive.services.parts;

import com.hydroyura.prodms.archive.data.entities.dto.DTOPart;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PartComparator {

    public Optional<PartChangeEventType> getDifference(DTOPart current, DTOPart updated) {
        PartChangeEventType type = null;

        if (!current.getName().equals(updated.getName())) type = PartChangeEventType.UPDATED_NAME;
        if (!current.getInfo().equals(updated.getInfo())) type = PartChangeEventType.UPDATED_INFO;
        if (!current.getPdf().equals(updated.getPdf())) type = PartChangeEventType.UPDATED_PDF;
        if (!current.getOtherFile().equals(updated.getOtherFile())) type = PartChangeEventType.UPDATED_OTHER_FILE;
        if (!current.getStatus().equals(updated.getStatus())) type = PartChangeEventType.UPDATED_STATUS;

        return Optional.ofNullable(type);
    }
}