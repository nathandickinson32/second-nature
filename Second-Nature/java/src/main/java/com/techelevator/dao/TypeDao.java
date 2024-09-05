package com.techelevator.dao;

import com.techelevator.model.Type;
import com.techelevator.model.TypeDto;

import java.util.List;

public interface TypeDao {
    // Create
    public Type createType(TypeDto typeDto);

    // Read
    public List<Type> getAllTypes();
    public Type getTypeByTypeId(int typeId);

    // Update
    public Type updateType(Type type);

    // Delete
    public void deleteType(int typeId);
}