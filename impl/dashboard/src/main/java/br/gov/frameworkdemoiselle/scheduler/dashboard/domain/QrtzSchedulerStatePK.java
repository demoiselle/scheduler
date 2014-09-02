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
public class QrtzSchedulerStatePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "INSTANCE_NAME", nullable = false, length = 200)
    private String instanceName;

    public QrtzSchedulerStatePK() {
    }

    public QrtzSchedulerStatePK(String schedName, String instanceName) {
        this.schedName = schedName;
        this.instanceName = instanceName;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schedName != null ? schedName.hashCode() : 0);
        hash += (instanceName != null ? instanceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzSchedulerStatePK)) {
            return false;
        }
        QrtzSchedulerStatePK other = (QrtzSchedulerStatePK) object;
        if ((this.schedName == null && other.schedName != null) || (this.schedName != null && !this.schedName.equals(other.schedName))) {
            return false;
        }
        if ((this.instanceName == null && other.instanceName != null) || (this.instanceName != null && !this.instanceName.equals(other.instanceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerStatePK[ schedName=" + schedName + ", instanceName=" + instanceName + " ]";
    }

}
