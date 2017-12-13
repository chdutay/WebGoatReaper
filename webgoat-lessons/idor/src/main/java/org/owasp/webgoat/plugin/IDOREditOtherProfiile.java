package org.owasp.webgoat.plugin;

import org.owasp.webgoat.assignments.AssignmentEndpoint;
import org.owasp.webgoat.assignments.AssignmentHints;
import org.owasp.webgoat.assignments.AssignmentPath;
import org.owasp.webgoat.assignments.AttackResult;
import org.owasp.webgoat.session.UserSessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ************************************************************************************************
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details,
 * please see http://www.owasp.org/
 * <p>
 * Copyright (c) 2002 - 20014 Bruce Mayhew
 * <p>
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 * <p>
 * Getting Source ==============
 * <p>
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software
 * projects.
 * <p>
 *
 * @author misfir3
 * @version $Id: $Id
 * @since January 3, 2017
 */

@AssignmentPath("IDOR/profile/{userId}")
@AssignmentHints({"idor.hints.otherProfile1","idor.hints.otherProfile2","idor.hints.otherProfile3","idor.hints.otherProfile4","idor.hints.otherProfile5","idor.hints.otherProfile6","idor.hints.otherProfile7","idor.hints.otherProfile8","idor.hints.otherProfile9"})
public class IDOREditOtherProfiile extends AssignmentEndpoint {

    @Autowired
    private UserSessionData userSessionData;

    @PutMapping(consumes = "application/json")
    public @ResponseBody
    AttackResult completed(@PathVariable("userId") String userId, @RequestBody UserProfile userSubmittedProfile) {

        String authUserId = (String)userSessionData.getValue("idor-authenticated-user-id");
        // this is where it starts ... accepting the user submitted ID and assuming it will be the same as the logged in userId and not checking for proper authorization
        // Certain roles can sometimes edit others' profiles, but we shouldn't just assume that and let everyone, right?
        // Except that this is a vulnerable app ... so we will
        UserProfile currentUserProfile = new UserProfile(userId);
        if (userSubmittedProfile.getUserId() != null && !userSubmittedProfile.getUserId().equals(authUserId)) {
            // let's get this started ...
            currentUserProfile.setColor(userSubmittedProfile.getColor());
            currentUserProfile.setRole(userSubmittedProfile.getRole());
            // we will persist in the session object for now in case we want to refer back or use it later
            userSessionData.setValue("idor-updated-other-profile",currentUserProfile);
            if (currentUserProfile.getRole() <= 1 && currentUserProfile.getColor().toLowerCase().equals("red")) {
                return trackProgress(success()
                    .feedback("idor.edit.profile.success1")
                    .output(currentUserProfile.profileToMap().toString())
                    .build());
            }

            if (currentUserProfile.getRole() > 1 && currentUserProfile.getColor().toLowerCase().equals("red")) {
                return trackProgress(success()
                        .feedback("idor.edit.profile.failure1")
                        .output(currentUserProfile.profileToMap().toString())
                        .build());
            }

            if (currentUserProfile.getRole() <= 1 && !currentUserProfile.getColor().toLowerCase().equals("red")) {
                return trackProgress(success()
                    .feedback("idor.edit.profile.failure2")
                    .output(currentUserProfile.profileToMap().toString())
                    .build());
            }

            // else
            return trackProgress(failed().
                feedback("idor.edit.profile.failure3")
                .output(currentUserProfile.profileToMap().toString())
                .build());
        } else if (userSubmittedProfile.getUserId().equals(authUserId)) {
            return failed().feedback("idor.edit.profile.failure4").build();
        }

        if (currentUserProfile.getColor().equals("black") && currentUserProfile.getRole() <= 1 ) {
            return trackProgress(success()
                .feedback("idor.edit.profile.success2")
                .output(userSessionData.getValue("idor-updated-own-profile").toString())
                .build());
        } else if (authUserId == null) {
            return trackProgress(failed().feedback("idor.edit.profile.failure0").build());
        } else {
            return trackProgress(failed().feedback("idor.edit.profile.failure3").build());
        }

    }

}
