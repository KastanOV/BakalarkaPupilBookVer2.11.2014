/*
 * Copyright (C) 2015 Topr
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package servicesDTO;

import java.util.Date;

/**
 *
 * @author Topr
 */
public class InformationsDTO {
    
    private Integer idinformations;
    private String description;
    private String someMessage;
    private Boolean infoForParrents;
    private Date createDate;
    private Integer studyGroupidStudyGroup;
    private String teacherLogin;
    private String teacherName;
    
    public InformationsDTO() {
    }

    public InformationsDTO(String description, String someMessage, Boolean infoForParrents, Date createDate, Integer studyGroupidStudyGroup, String teacherLogin, String teacherName) {
        this.description = description;
        this.someMessage = someMessage;
        this.infoForParrents = infoForParrents;
        this.createDate = createDate;
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
        this.teacherLogin = teacherLogin;
        this.teacherName = teacherName;
    }
    public Integer getIdinformations() {
        return idinformations;
    }

    public void setIdinformations(Integer idinformations) {
        this.idinformations = idinformations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSomeMessage() {
        return someMessage;
    }

    public void setSomeMessage(String someMessage) {
        this.someMessage = someMessage;
    }

    public Boolean getInfoForParrents() {
        return infoForParrents;
    }

    public void setInfoForParrents(Boolean infoForParrents) {
        this.infoForParrents = infoForParrents;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStudyGroupidStudyGroup() {
        return studyGroupidStudyGroup;
    }

    public void setStudyGroupidStudyGroup(Integer studyGroupidStudyGroup) {
        this.studyGroupidStudyGroup = studyGroupidStudyGroup;
    }

    public String getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(String teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
}
