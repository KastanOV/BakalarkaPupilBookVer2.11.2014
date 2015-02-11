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
package SessionBeans;

/**
 *
 * @author Topr
 */
public class PasswordReset {
    private String LoginStudent;

    public String getLoginStudent() {
        return LoginStudent;
    }

    public void setLoginStudent(String LoginStudent) {
        this.LoginStudent = LoginStudent;
    }

    public String getPasswordStudent() {
        return PasswordStudent;
    }

    public void setPasswordStudent(String PasswordStudent) {
        this.PasswordStudent = PasswordStudent;
    }

    public String getLoginParrent() {
        return LoginParrent;
    }

    public void setLoginParrent(String LoginParrent) {
        this.LoginParrent = LoginParrent;
    }

    public String getPasswordParrent() {
        return PasswordParrent;
    }

    public void setPasswordParrent(String PasswordParrent) {
        this.PasswordParrent = PasswordParrent;
    }
    private String PasswordStudent;
    private String LoginParrent;
    private String PasswordParrent;
    
}
