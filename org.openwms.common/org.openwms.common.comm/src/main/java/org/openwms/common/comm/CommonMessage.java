/*
 * openwms.org, the Open Warehouse Management System.
 * Copyright (C) 2014 Heiko Scherrer
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.comm;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A CommonMessage is the abstract superclass of all messages sent to subsystems like PLC or ERP. A CommonMessage has always a message
 * header and a body.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.2
 */
public abstract class CommonMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private final CommonHeader header;
    private String errorCode;
    private Date created;

    public static final short ERROR_CODE_LENGTH = 8;
    public static final short DATE_LENGTH = 14;
    public static final int MESSAGE_IDENTIFIER_LENGTH = 4;

    /**
     * Create a new CommonMessage.
     * 
     * @param header
     *            The message header
     */
    public CommonMessage(CommonHeader header) {
        this.header = header;
    }

    /**
     * Subclasses have to return an unique, case-sensitive message identifier.
     * 
     * @return The message TYPE field (see OSIP specification)
     */
    public abstract String getMessageIdentifier();

    /**
     * Does this type of message needs to be replied to?
     * 
     * @return {@literal true} no reply needed, otherwise {@literal false}
     */
    public abstract boolean isWithoutReply();

    /**
     * Get the header.
     * 
     * @return header
     */
    public CommonHeader getHeader() {
        return header;
    }

    /**
     * Get the errorCode.
     * 
     * @return the errorCode.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Set the errorCode.
     * 
     * @param errorCode
     *            The errorCode to set.
     */
    protected void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Get the created.
     * 
     * @return the created.
     */
    public Date getCreated() {
        if (created == null) {
            created = new Date();
        }
        return created;
    }

    /**
     * Set the created.
     * 
     * @param created
     *            The created to set.
     */
    protected void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonMessage that = (CommonMessage) o;
        return Objects.equals(header, that.header) &&
                Objects.equals(errorCode, that.errorCode) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, errorCode, created);
    }

    @Override
    public String toString() {
        return "CommonMessage{" +
                "errorCode='" + errorCode + '\'' +
                ", created=" + created +
                "} with " + header;
    }
}
