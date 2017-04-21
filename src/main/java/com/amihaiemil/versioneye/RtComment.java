package com.amihaiemil.versioneye;

import javax.json.JsonObject;

/**
 * VersionEye comment.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
final class RtComment implements Comment {

    /**
     * This comment as a JsonObject.
     */
    private JsonObject comment;
    
    /**
     * Ctor.
     * @param comment Json comment as it is returned by the API.
     */
    RtComment(final JsonObject comment) {
        this.comment = comment;
    }
    
    @Override
    public String id() {
        return this.comment.getString("id");
    }

    @Override
    public String text() {
        return this.comment.getString("comment");
    }

    @Override
    public String createdAt() {
        return this.comment.getString("created_at");
    }

    @Override
    public UserData user() {
        return new JsonUserData(this.comment.getJsonObject("user"));
    }

    @Override
    public JsonObject json() {
        return this.comment;
    }

}
