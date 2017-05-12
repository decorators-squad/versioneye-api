package com.amihaiemil.versioneye;

import javax.json.JsonObject;

import com.amihaiemil.json.Storage;

/**
 * Mock VersionEye authenticated user.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public final class MkJsonAuthenticated implements MkAuthenticated {

    /**
     * Authenticated user json builder.
     */
    private Storage authenticated;
    
    /**
     * Ctor.
     */
    public MkJsonAuthenticated() {
        this.authenticated = new SyncStorage(new MkStorage());
    }
    
    /**
     * Ctor. This is package protected, to be used only internally.
     * @param user User as plain JsonObject.
     */
    MkJsonAuthenticated(final JsonObject user) {
        this.authenticated = new SyncStorage(new MkStorage())
            .add("username", user.getString("username", ""))
            .add("fullname", user.getString("fullname", ""))
            .add("email", user.getString("email", ""))
            .add("admin", user.getBoolean("admin", false))
            .add("deleted_user", user.getBoolean("deleted_user", false))
            .add("enterprise_projects", user.getInt("enterprise_projects", 0))
            .add("rate_limit", user.getInt("rate_limit", 0))
            .add("comp_limit", user.getInt("comp_limit", 0))
            .add("active", user.getBoolean("active", false));
    }
    
    @Override
    public String fullName() {
        return this.authenticated.build().getString("fullname");
    }

    @Override
    public String username() {
        return this.authenticated.build().getString("username");
    }
    
    @Override
    public String email() {
        return this.authenticated.build().getString("email");
    }

    @Override
    public boolean admin() {
        return this.authenticated.build().getBoolean("admin");
    }

    @Override
    public boolean deleted() {
        return this.authenticated.build().getBoolean("deleted_user");
    }

    @Override
    public int enterpriseProjects() {
        return this.authenticated.build().getInt("enterprise_projects");
    }

    @Override
    public int rateLimit() {
        return this.authenticated.build().getInt("rate_limit");
    }

    @Override
    public int compLimit() {
        return this.authenticated.build().getInt("comp_limit");
    }

    @Override
    public boolean active() {
        return this.authenticated.build().getBoolean("active");
    }

    @Override
    public JsonObject json() {
        return this.authenticated.build();
    }

    @Override
    public MkAuthenticated fullName(final String fullname) {
        this.authenticated.add("fullname", fullname);
        return this;
    }

    @Override
    public MkAuthenticated username(final String username) {
        this.authenticated.add("username", username);
        return this;
    }

    @Override
    public MkAuthenticated email(final String email) {
        this.authenticated.add("email", email);
        return this;
    }

    @Override
    public MkAuthenticated admin(final boolean admin) {
        this.authenticated.add("admin", admin);
        return this;
    }

    @Override
    public MkAuthenticated deleted(final boolean deleted) {
        this.authenticated.add("deleted_user", deleted);
        return this;
    }

    @Override
    public MkAuthenticated enterpriseProjects(final int enterpriseProjects) {
        this.authenticated.add("enterprise_projects", enterpriseProjects);
        return this;
    }

    @Override
    public MkAuthenticated rateLimit(final int rateLimit) {
        this.authenticated.add("rate_limit", rateLimit);
        return this;
    }

    @Override
    public MkAuthenticated compLimit(final int compLimit) {
        this.authenticated.add("comp_limit", compLimit);
        return this;
    }

    @Override
    public MkAuthenticated active(final boolean active) {
        this.authenticated.add("active", active);
        return this;
    }

}
