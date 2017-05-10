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

/**
 * Mock authenticated user.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public interface MkAuthenticated extends Authenticated {
    
    /**
     * Set authenticated user's fullname.
     * @param fullname String.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated fullName(String fullname);
    
    /**
     * Set authenticated user's username.
     * @param username String.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated username(String username);
   
    /**
     * Set authenticated user's email.
     * @param email String.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated email(String email);
   
    /**
     * Set whether the user is admin or not.
     * @param admin Boolean.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated admin(boolean admin);
   
    /**
     * Set whether user is deleted or not.
     * @param deleted Boolean.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated deleted(boolean deleted);
   
    /**
     * Set the number of user's enterprise projects.
     * @param enterpriseProjects Integer.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated enterpriseProjects(int enterpriseProjects);
   
    /**
     * Set the user's rate limit.
     * @param rateLimit Integer.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated rateLimit(int rateLimit);
   
    /**
     * Set the user's computational limit.
     * @param compLimit Integer.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated compLimit(int compLimit);
   
    /**
     * Set whether user is active or not.
     * @param active Boolean.
     * @return MkAuthenticated this user.
     */
    MkAuthenticated active(boolean active);
}
