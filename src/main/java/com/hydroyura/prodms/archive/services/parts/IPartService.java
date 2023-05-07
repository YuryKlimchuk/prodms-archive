package com.hydroyura.prodms.archive.services.parts;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface IPartService<DTO, ID> {

    public Optional<DTO> getItemById(ID id);
    public Collection<DTO> getAllByFilter(Map<String, String> filter);
    public Optional<DTO> delete(ID id);
    public Optional<DTO> create(DTO entity);
    public Optional<DTO> update(DTO entity);

}
