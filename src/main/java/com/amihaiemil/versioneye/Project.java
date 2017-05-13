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
 * A project on VersionEye.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public interface Project {
    
    /**
     * This project's id.
     * @return String.
     */
    String projectId();

    /**
     * This project's name.
     * @return String.
     */
    String name();
    
    /**
     * This project's type (e.g. Maven).
     * @return String.
     */
    String type();

    /**
     * Is this a public project?
     * @return Boolean.
     */
    boolean publicProject();

    /**
     * Does this project have a private SCM?
     * @return Boolean.
     */
    boolean privateScm();

    /**
     * This project's period (e.g. daily).
     * @return String.
     */
    String period();
    
    /**
     * This project's source.
     * @return String.
     */
    String source();
    
    /**
     * Number of dependencies.
     * @return Integer.
     */
    int dependencies();
    
    /**
     * Number of outdated dependencies.
     * @return Integer.
     */
    int outdated();
    
    /**
     * Number of unknown licenses.
     * @return Integer.
     */
    int licensesUnknown();

    /**
     * Number of red licenses.
     * @return Integer.
     */
    int licensesRed();

    /**
     * Name of the licenses whitelist.
     * @return String.
     */
    String whitelist();

    /**
     * Ids of this project's children.
     * @return List of String.
     */
    List<String> childIds();
    
    /**
     * Its parent's id.
     * @return String.
     */
    String parentId();
    
    /**
     * Date of creation (e.g. 2017-05-12T13:22:22.032Z).
     * @return String.
     */
    String createdAt();
    
    /**
     * Date of update (e.g. 2017-05-13T13:45:22.032Z).
     * @return String.
     */
    String updatedAt();

    /**
     * This project's whole json object.
     * @return JsonObject.
     */
    JsonObject json();
    
    /**
     * The team which holds this project.
     * @return Team.
     */
    Team team();
    
    /**
     * This project's organization.
     * @return Organization.
     */
    Organization organization();
}
