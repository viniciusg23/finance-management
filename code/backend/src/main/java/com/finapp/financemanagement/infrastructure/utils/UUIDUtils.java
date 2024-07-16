package com.finapp.financemanagement.infrastructure.utils;

import java.util.UUID;

import com.finapp.financemanagement.domain.exception.utils.InvalidUUIDException;

public class UUIDUtils {

    public static UUID stringToUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException exception) {
            throw new InvalidUUIDException();
        }
    }
    
}
