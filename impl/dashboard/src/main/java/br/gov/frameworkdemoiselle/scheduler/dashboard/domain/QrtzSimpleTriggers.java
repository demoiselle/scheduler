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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(name = "qrtz_simple_triggers", catalog = "quartz", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QrtzSimpleTriggers.findAll", query = "SELECT q FROM QrtzSimpleTriggers q"),
    @NamedQuery(name = "QrtzSimpleTriggers.findBySchedName", query = "SELECT q FROM QrtzSimpleTriggers q WHERE q.qrtzSimpleTriggersPK.schedName = :schedName"),
    @NamedQuery(name = "QrtzSimpleTriggers.findByTriggerName", query = "SELECT q FROM QrtzSimpleTriggers q WHERE q.qrtzSimpleTriggersPK.triggerName = :triggerName"),
    @NamedQuery(name = "QrtzSimpleTriggers.findByTriggerGroup", query = "SELECT q FROM QrtzSimpleTriggers q WHERE q.qrtzSimpleTriggersPK.triggerGroup = :triggerGroup"),
    @NamedQuery(name = "QrtzSimpleTriggers.findByRepeatCount", query = "SELECT q FROM QrtzSimpleTriggers q WHERE q.repeatCount = :repeatCount"),
    @NamedQuery(name = "QrtzSimpleTriggers.findByRepeatInterval", query = "SELECT q FROM QrtzSimpleTriggers q WHERE q.repeatInterval = :repeatInterval"),
    @NamedQuery(name = "QrtzSimpleTriggers.findByTimesTriggered", query = "SELECT q FROM QrtzSimpleTriggers q WHERE q.timesTriggered = :timesTriggered")})
public class QrtzSimpleTriggers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QrtzSimpleTriggersPK qrtzSimpleTriggersPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPEAT_COUNT", nullable = false)
    private long repeatCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPEAT_INTERVAL", nullable = false)
    private long repeatInterval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMES_TRIGGERED", nullable = false)
    private long timesTriggered;
    @JoinColumns({
        @JoinColumn(name = "SCHED_NAME", referencedColumnName = "SCHED_NAME", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "TRIGGER_NAME", referencedColumnName = "TRIGGER_NAME", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "TRIGGER_GROUP", referencedColumnName = "TRIGGER_GROUP", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private QrtzTriggers qrtzTriggers;

    public QrtzSimpleTriggers() {
    }

    public QrtzSimpleTriggers(QrtzSimpleTriggersPK qrtzSimpleTriggersPK) {
        this.qrtzSimpleTriggersPK = qrtzSimpleTriggersPK;
    }

    public QrtzSimpleTriggers(QrtzSimpleTriggersPK qrtzSimpleTriggersPK, long repeatCount, long repeatInterval, long timesTriggered) {
        this.qrtzSimpleTriggersPK = qrtzSimpleTriggersPK;
        this.repeatCount = repeatCount;
        this.repeatInterval = repeatInterval;
        this.timesTriggered = timesTriggered;
    }

    public QrtzSimpleTriggers(String schedName, String triggerName, String triggerGroup) {
        this.qrtzSimpleTriggersPK = new QrtzSimpleTriggersPK(schedName, triggerName, triggerGroup);
    }

    public QrtzSimpleTriggersPK getQrtzSimpleTriggersPK() {
        return qrtzSimpleTriggersPK;
    }

    public void setQrtzSimpleTriggersPK(QrtzSimpleTriggersPK qrtzSimpleTriggersPK) {
        this.qrtzSimpleTriggersPK = qrtzSimpleTriggersPK;
    }

    public long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(long repeatCount) {
        this.repeatCount = repeatCount;
    }

    public long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public long getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

    public QrtzTriggers getQrtzTriggers() {
        return qrtzTriggers;
    }

    public void setQrtzTriggers(QrtzTriggers qrtzTriggers) {
        this.qrtzTriggers = qrtzTriggers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrtzSimpleTriggersPK != null ? qrtzSimpleTriggersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzSimpleTriggers)) {
            return false;
        }
        QrtzSimpleTriggers other = (QrtzSimpleTriggers) object;
        if ((this.qrtzSimpleTriggersPK == null && other.qrtzSimpleTriggersPK != null) || (this.qrtzSimpleTriggersPK != null && !this.qrtzSimpleTriggersPK.equals(other.qrtzSimpleTriggersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSimpleTriggers[ qrtzSimpleTriggersPK=" + qrtzSimpleTriggersPK + " ]";
    }

}
