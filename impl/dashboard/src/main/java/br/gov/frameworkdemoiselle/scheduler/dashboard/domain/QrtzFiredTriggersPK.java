/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.frameworkdemoiselle.scheduler.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 70744416353
 */
@Embeddable
public class QrtzFiredTriggersPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 95)
    @Column(name = "ENTRY_ID", nullable = false, length = 95)
    private String entryId;

    public QrtzFiredTriggersPK() {
    }

    public QrtzFiredTriggersPK(String schedName, String entryId) {
        this.schedName = schedName;
        this.entryId = entryId;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schedName != null ? schedName.hashCode() : 0);
        hash += (entryId != null ? entryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzFiredTriggersPK)) {
            return false;
        }
        QrtzFiredTriggersPK other = (QrtzFiredTriggersPK) object;
        if ((this.schedName == null && other.schedName != null) || (this.schedName != null && !this.schedName.equals(other.schedName))) {
            return false;
        }
        if ((this.entryId == null && other.entryId != null) || (this.entryId != null && !this.entryId.equals(other.entryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggersPK[ schedName=" + schedName + ", entryId=" + entryId + " ]";
    }

}
