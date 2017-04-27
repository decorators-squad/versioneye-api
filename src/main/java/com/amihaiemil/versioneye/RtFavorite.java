/**
 * Copyright (c) 2017, Mihai Emil Andronache
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * Neither the name of the copyright holder nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package com.amihaiemil.versioneye;

import javax.json.JsonObject;

/**
 * VersionEye favorite.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
final class RtFavorite implements Favorite{
    
    /**
     * The favorite as a JsonObject.
     */
    private JsonObject favorite;
    
    /**
     * Ctor.
     * @param favorite Json favorite as it is returned by the API.
     */
    RtFavorite(final JsonObject favorite) {
        this.favorite = favorite;
    }
    
    @Override
    public String name() {
        return this.favorite.getString("name");
    }

    @Override
    public String language() {
        return this.favorite.getString("language");
    }

    @Override
    public String productKey() {
        return this.favorite.getString("prod_key");
    }

    @Override
    public String version() {
        return this.favorite.getString("version");
    }

    @Override
    public String productType() {
        return this.favorite.getString("prod_type");
    }

    @Override
    public JsonObject json() {
        return this.favorite;
    }
    
}
