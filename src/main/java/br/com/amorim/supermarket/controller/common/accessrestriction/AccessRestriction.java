package br.com.amorim.supermarket.controller.common.accessrestriction;

import java.util.UUID;

public interface AccessRestriction {

    boolean isAuthorized(UUID id);
}
