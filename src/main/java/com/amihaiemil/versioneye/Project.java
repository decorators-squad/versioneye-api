package com.amihaiemil.versioneye;

/**
 * A project on VersionEye.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #38:30min/DEV Provide and unit test RtProject.
 */
public interface Project {
    
    /**
     * The team which holds this project.
     * @return Team.
     */
    Team team();
}
