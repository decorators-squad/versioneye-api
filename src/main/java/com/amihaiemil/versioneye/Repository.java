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

import java.util.List;

import javax.json.JsonObject;

/**
 * Github repository.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public interface Repository {

    /**
     * The repository's name.
     * @return String
     */
    String name();
    
    /**
     * The repository's fullname.
     * @return String
     */
    String fullname();
    
    /**
     * The repository's language.
     * @return String
     */
    String language();
    
    /**
     * The repository's owner login.
     * @return String
     */
    String ownerLogin();
    
    /**
     * The repository's owner type.
     * @return String
     */
    String ownerType();
    
    /**
     * The repository's description.
     * @return String
     */
    String description();
    
    /**
     * Whether repository is private or not.
     * @return Boolean
     */
    boolean isPrivate();
    
    /**
     * Whether repository is a fork or not?
     * @return Boolean
     */
    boolean fork();
    
    /**
     * The repository's branches.
     * @return List of branches
     */
    List<String> branches();
    
    /**
     * The repository as JsonObject.
     * @return JsonObject
     */
    JsonObject json();
}
