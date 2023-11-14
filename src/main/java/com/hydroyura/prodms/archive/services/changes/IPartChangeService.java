package com.hydroyura.prodms.archive.services.changes;

import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.DBPartChange;
import com.hydroyura.prodms.archive.data.entities.dto.DTOPartChange;

import java.util.Collection;

public interface IPartChangeService {

    Collection<DTOPartChange> getChanges(String number);
    void create(DBPartChange partChange);

}
