/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.frameworkdemoiselle.scheduler.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(name = "qrtz_scheduler_state", catalog = "quartz", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QrtzSchedulerState.findAll", query = "SELECT q FROM QrtzSchedulerState q"),
    @NamedQuery(name = "QrtzSchedulerState.findBySchedName", query = "SELECT q FROM QrtzSchedulerState q WHERE q.qrtzSchedulerStatePK.schedName = :schedName"),
    @NamedQuery(name = "QrtzSchedulerState.findByInstanceName", query = "SELECT q FROM QrtzSchedulerState q WHERE q.qrtzSchedulerStatePK.instanceName = :instanceName"),
    @NamedQuery(name = "QrtzSchedulerState.findByLastCheckinTime", query = "SELECT q FROM QrtzSchedulerState q WHERE q.lastCheckinTime = :lastCheckinTime"),
    @NamedQuery(name = "QrtzSchedulerState.findByCheckinInterval", query = "SELECT q FROM QrtzSchedulerState q WHERE q.checkinInterval = :checkinInterval")})
public class QrtzSchedulerState implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QrtzSchedulerStatePK qrtzSchedulerStatePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_CHECKIN_TIME", nullable = false)
    private long lastCheckinTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CHECKIN_INTERVAL", nullable = false)
    private long checkinInterval;

    public QrtzSchedulerState() {
    }

    public QrtzSchedulerState(QrtzSchedulerStatePK qrtzSchedulerStatePK) {
        this.qrtzSchedulerStatePK = qrtzSchedulerStatePK;
    }

    public QrtzSchedulerState(QrtzSchedulerStatePK qrtzSchedulerStatePK, long lastCheckinTime, long checkinInterval) {
        this.qrtzSchedulerStatePK = qrtzSchedulerStatePK;
        this.lastCheckinTime = lastCheckinTime;
        this.checkinInterval = checkinInterval;
    }

    public QrtzSchedulerState(String schedName, String instanceName) {
        this.qrtzSchedulerStatePK = new QrtzSchedulerStatePK(schedName, instanceName);
    }

    public QrtzSchedulerStatePK getQrtzSchedulerStatePK() {
        return qrtzSchedulerStatePK;
    }

    public void setQrtzSchedulerStatePK(QrtzSchedulerStatePK qrtzSchedulerStatePK) {
        this.qrtzSchedulerStatePK = qrtzSchedulerStatePK;
    }

    public long getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }

    public long getCheckinInterval() {
        return checkinInterval;
    }

    public void setCheckinInterval(long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrtzSchedulerStatePK != null ? qrtzSchedulerStatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzSchedulerState)) {
            return false;
        }
        QrtzSchedulerState other = (QrtzSchedulerState) object;
        if ((this.qrtzSchedulerStatePK == null && other.qrtzSchedulerStatePK != null) || (this.qrtzSchedulerStatePK != null && !this.qrtzSchedulerStatePK.equals(other.qrtzSchedulerStatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerState[ qrtzSchedulerStatePK=" + qrtzSchedulerStatePK + " ]";
    }

}
