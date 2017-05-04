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
 * A VersionEye Team.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public interface Team {
    
    /**
     * Team id.
     * @return String
     */
    String teamId();
    
    /**
     * Team name.
     * @return String
     */
    String name();
    
    /**
     * Does this team receive versions notifications?
     * @return Boolean.
     */
    boolean versionNotifications();
    
    /**
     * Does this team receive licenses notifications?
     * @return Boolean.
     */
    boolean licenseNotifications();
    
    /**
     * Does this team receive security notifications?
     * @return Boolean.
     */
    boolean securityNotifications();
    
    /**
     * Should notifications be sent on Mondays?
     * @return Boolean.
     */
    boolean monday();
    
    /**
     * Should notifications be sent on Tuesdays?
     * @return Boolean.
     */
    boolean tuesday();
    
    /**
     * Should notifications be sent on Wednesdays?
     * @return Boolean.
     */
    boolean wednesday();
    
    /**
     * Should notifications be sent on Thursdays?
     * @return Boolean.
     */
    boolean thursday();
    
    /**
     * Should notifications be sent on Fridays?
     * @return Boolean.
     */
    boolean friday();
    
    /**
     * Should notifications be sent on Saturdays?
     * @return Boolean.
     */
    boolean saturday();
    
    /**
     * Should notifications be sent on Sundays?
     * @return Boolean.
     */
    boolean sunday();
    
    /**
     * Members of this team.
     * @return List of UserData.
     */
    List<UserData> users();
    
    /**
     * Created at.
     * @return String.
     */
    String createdAt();
    
    /**
     * Updated at.
     * @return String.
     */
    String updatedAt();
    
    /**
     * This Team as JsonObject.
     * @return JsonObject.
     */
    JsonObject json();

}
