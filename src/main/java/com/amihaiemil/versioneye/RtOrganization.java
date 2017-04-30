package com.amihaiemil.versioneye;

import javax.json.JsonObject;

import com.jcabi.http.Request;

/**
 * Real implementation for {@link Oganization}.
 * @author Sherif Waly (sheifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
final class RtOrganization implements Organization {

    /**
     * Ctor.
     * @param organization Json organization as returned by API. 
     * @param entry HTTP request.
     */
    RtOrganization(final JsonObject organization, final Request entry) {
        
    }

    @Override
    public Teams teams() {
        return null;
    }

    @Override
    public Projects projects() {
        return null;
    }
}
