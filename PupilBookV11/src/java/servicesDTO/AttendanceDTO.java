/*
 * Copyright (C) 2015 Jaroslav
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

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jaroslav
 */
@XmlRootElement
public class AttendanceDTO {

    public AttendanceDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getExcused() {
        return excused;
    }

    public void setExcused(String excused) {
        this.excused = excused;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public AttendanceDTO(Integer id, String start, String end, String excused, String login) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.excused = excused;
        this.login = login;
    }

    
    private Integer id;
    private String start;
    private String end;
    private String excused;
    private String login;

    
    
}
