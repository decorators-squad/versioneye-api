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
 * VersionEye authenticated user.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @sinve 1.0.0
 * @todo #14:30min/DEV Implement and unit test RtNotifications
 *  (implements Notifications) and add method notifications() here, to
 *  fetch the authenticated user's notifications info.
 */
public interface Authenticated {
    
    /**
     * Authenticated user's full name.
     * @return String.
     */
    String fullName();
    
    /**
     * Authenticated user's username.
     * @return String.
     */
    String username();
    
    /**
     * Authenticated user's email address.
     * @return String.
     */
    String email();
    
    /**
     * Is the authenticated user an admin?
     * @return Boolean.
     */
    boolean admin();
    
    /**
     * Is the authenticated user deleted?
     * @return Boolean.
     */
    boolean deleted();
    
    /**
     * Number of the authenticated user's enterprise projects.
     * @return Integer.
     */
    int enterpriseProjects();
   
    /**
     * Authenticated user's rate limit.
     * @return Integer.
     */
    int rateLimit();
    
    /**
     * Authenticated user's computational limit.
     * @return Integer
     */
    int compLimit();
    
    /**
     * Is the authenticated user active or not?
     * @return Boolean.
     */
    boolean active();
    
    /**
     * The raw Json representing the authenticated user.
     * @return JsonObject.
     */
    JsonObject json();
}
