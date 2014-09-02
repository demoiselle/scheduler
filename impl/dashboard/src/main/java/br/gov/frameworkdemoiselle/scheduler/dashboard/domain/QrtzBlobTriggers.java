/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.frameworkdemoiselle.scheduler.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(name = "qrtz_blob_triggers", catalog = "quartz", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QrtzBlobTriggers.findAll", query = "SELECT q FROM QrtzBlobTriggers q"),
    @NamedQuery(name = "QrtzBlobTriggers.findBySchedName", query = "SELECT q FROM QrtzBlobTriggers q WHERE q.qrtzBlobTriggersPK.schedName = :schedName"),
    @NamedQuery(name = "QrtzBlobTriggers.findByTriggerName", query = "SELECT q FROM QrtzBlobTriggers q WHERE q.qrtzBlobTriggersPK.triggerName = :triggerName"),
    @NamedQuery(name = "QrtzBlobTriggers.findByTriggerGroup", query = "SELECT q FROM QrtzBlobTriggers q WHERE q.qrtzBlobTriggersPK.triggerGroup = :triggerGroup")})
public class QrtzBlobTriggers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QrtzBlobTriggersPK qrtzBlobTriggersPK;
    @Lob
    @Column(name = "BLOB_DATA")
    private byte[] blobData;
    @JoinColumns({
        @JoinColumn(name = "SCHED_NAME", referencedColumnName = "SCHED_NAME", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "TRIGGER_NAME", referencedColumnName = "TRIGGER_NAME", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "TRIGGER_GROUP", referencedColumnName = "TRIGGER_GROUP", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private QrtzTriggers qrtzTriggers;

    public QrtzBlobTriggers() {
    }

    public QrtzBlobTriggers(QrtzBlobTriggersPK qrtzBlobTriggersPK) {
        this.qrtzBlobTriggersPK = qrtzBlobTriggersPK;
    }

    public QrtzBlobTriggers(String schedName, String triggerName, String triggerGroup) {
        this.qrtzBlobTriggersPK = new QrtzBlobTriggersPK(schedName, triggerName, triggerGroup);
    }

    public QrtzBlobTriggersPK getQrtzBlobTriggersPK() {
        return qrtzBlobTriggersPK;
    }

    public void setQrtzBlobTriggersPK(QrtzBlobTriggersPK qrtzBlobTriggersPK) {
        this.qrtzBlobTriggersPK = qrtzBlobTriggersPK;
    }

    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
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
        hash += (qrtzBlobTriggersPK != null ? qrtzBlobTriggersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzBlobTriggers)) {
            return false;
        }
        QrtzBlobTriggers other = (QrtzBlobTriggers) object;
        if ((this.qrtzBlobTriggersPK == null && other.qrtzBlobTriggersPK != null) || (this.qrtzBlobTriggersPK != null && !this.qrtzBlobTriggersPK.equals(other.qrtzBlobTriggersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzBlobTriggers[ qrtzBlobTriggersPK=" + qrtzBlobTriggersPK + " ]";
    }

}
