package com.hydroyura.prodms.archive.services.changes;

import com.hydroyura.prodms.archive.dto.DTOPartChange;

import java.util.Collection;

public interface IPartChangeService {

    Collection<DTOPartChange> getChanges(String number);

}
