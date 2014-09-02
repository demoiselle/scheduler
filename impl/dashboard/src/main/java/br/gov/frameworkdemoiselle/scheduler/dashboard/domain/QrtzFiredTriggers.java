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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(name = "qrtz_fired_triggers", catalog = "quartz", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QrtzFiredTriggers.findAll", query = "SELECT q FROM QrtzFiredTriggers q"),
    @NamedQuery(name = "QrtzFiredTriggers.findBySchedName", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.qrtzFiredTriggersPK.schedName = :schedName"),
    @NamedQuery(name = "QrtzFiredTriggers.findByEntryId", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.qrtzFiredTriggersPK.entryId = :entryId"),
    @NamedQuery(name = "QrtzFiredTriggers.findByTriggerName", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.triggerName = :triggerName"),
    @NamedQuery(name = "QrtzFiredTriggers.findByTriggerGroup", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.triggerGroup = :triggerGroup"),
    @NamedQuery(name = "QrtzFiredTriggers.findByInstanceName", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.instanceName = :instanceName"),
    @NamedQuery(name = "QrtzFiredTriggers.findByFiredTime", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.firedTime = :firedTime"),
    @NamedQuery(name = "QrtzFiredTriggers.findBySchedTime", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.schedTime = :schedTime"),
    @NamedQuery(name = "QrtzFiredTriggers.findByPriority", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.priority = :priority"),
    @NamedQuery(name = "QrtzFiredTriggers.findByState", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.state = :state"),
    @NamedQuery(name = "QrtzFiredTriggers.findByJobName", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.jobName = :jobName"),
    @NamedQuery(name = "QrtzFiredTriggers.findByJobGroup", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.jobGroup = :jobGroup"),
    @NamedQuery(name = "QrtzFiredTriggers.findByIsNonconcurrent", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.isNonconcurrent = :isNonconcurrent"),
    @NamedQuery(name = "QrtzFiredTriggers.findByRequestsRecovery", query = "SELECT q FROM QrtzFiredTriggers q WHERE q.requestsRecovery = :requestsRecovery")})
public class QrtzFiredTriggers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QrtzFiredTriggersPK qrtzFiredTriggersPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TRIGGER_NAME", nullable = false, length = 200)
    private String triggerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TRIGGER_GROUP", nullable = false, length = 200)
    private String triggerGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "INSTANCE_NAME", nullable = false, length = 200)
    private String instanceName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIRED_TIME", nullable = false)
    private long firedTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SCHED_TIME", nullable = false)
    private long schedTime;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int priority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(nullable = false, length = 16)
    private String state;
    @Size(max = 200)
    @Column(name = "JOB_NAME", length = 200)
    private String jobName;
    @Size(max = 200)
    @Column(name = "JOB_GROUP", length = 200)
    private String jobGroup;
    @Size(max = 1)
    @Column(name = "IS_NONCONCURRENT", length = 1)
    private String isNonconcurrent;
    @Size(max = 1)
    @Column(name = "REQUESTS_RECOVERY", length = 1)
    private String requestsRecovery;

    public QrtzFiredTriggers() {
    }

    public QrtzFiredTriggers(QrtzFiredTriggersPK qrtzFiredTriggersPK) {
        this.qrtzFiredTriggersPK = qrtzFiredTriggersPK;
    }

    public QrtzFiredTriggers(QrtzFiredTriggersPK qrtzFiredTriggersPK, String triggerName, String triggerGroup, String instanceName, long firedTime, long schedTime, int priority, String state) {
        this.qrtzFiredTriggersPK = qrtzFiredTriggersPK;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
        this.instanceName = instanceName;
        this.firedTime = firedTime;
        this.schedTime = schedTime;
        this.priority = priority;
        this.state = state;
    }

    public QrtzFiredTriggers(String schedName, String entryId) {
        this.qrtzFiredTriggersPK = new QrtzFiredTriggersPK(schedName, entryId);
    }

    public QrtzFiredTriggersPK getQrtzFiredTriggersPK() {
        return qrtzFiredTriggersPK;
    }

    public void setQrtzFiredTriggersPK(QrtzFiredTriggersPK qrtzFiredTriggersPK) {
        this.qrtzFiredTriggersPK = qrtzFiredTriggersPK;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public long getFiredTime() {
        return firedTime;
    }

    public void setFiredTime(long firedTime) {
        this.firedTime = firedTime;
    }

    public long getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(long schedTime) {
        this.schedTime = schedTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }

    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrtzFiredTriggersPK != null ? qrtzFiredTriggersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzFiredTriggers)) {
            return false;
        }
        QrtzFiredTriggers other = (QrtzFiredTriggers) object;
        if ((this.qrtzFiredTriggersPK == null && other.qrtzFiredTriggersPK != null) || (this.qrtzFiredTriggersPK != null && !this.qrtzFiredTriggersPK.equals(other.qrtzFiredTriggersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggers[ qrtzFiredTriggersPK=" + qrtzFiredTriggersPK + " ]";
    }

}
