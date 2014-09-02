/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.frameworkdemoiselle.scheduler.dashboard.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 70744416353
 */
@Entity
@Table(name = "qrtz_paused_trigger_grps", catalog = "quartz", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QrtzPausedTriggerGrps.findAll", query = "SELECT q FROM QrtzPausedTriggerGrps q"),
    @NamedQuery(name = "QrtzPausedTriggerGrps.findBySchedName", query = "SELECT q FROM QrtzPausedTriggerGrps q WHERE q.qrtzPausedTriggerGrpsPK.schedName = :schedName"),
    @NamedQuery(name = "QrtzPausedTriggerGrps.findByTriggerGroup", query = "SELECT q FROM QrtzPausedTriggerGrps q WHERE q.qrtzPausedTriggerGrpsPK.triggerGroup = :triggerGroup")})
public class QrtzPausedTriggerGrps implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected QrtzPausedTriggerGrpsPK qrtzPausedTriggerGrpsPK;

    public QrtzPausedTriggerGrps() {
    }

    public QrtzPausedTriggerGrps(QrtzPausedTriggerGrpsPK qrtzPausedTriggerGrpsPK) {
        this.qrtzPausedTriggerGrpsPK = qrtzPausedTriggerGrpsPK;
    }

    public QrtzPausedTriggerGrps(String schedName, String triggerGroup) {
        this.qrtzPausedTriggerGrpsPK = new QrtzPausedTriggerGrpsPK(schedName, triggerGroup);
    }

    public QrtzPausedTriggerGrpsPK getQrtzPausedTriggerGrpsPK() {
        return qrtzPausedTriggerGrpsPK;
    }

    public void setQrtzPausedTriggerGrpsPK(QrtzPausedTriggerGrpsPK qrtzPausedTriggerGrpsPK) {
        this.qrtzPausedTriggerGrpsPK = qrtzPausedTriggerGrpsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qrtzPausedTriggerGrpsPK != null ? qrtzPausedTriggerGrpsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzPausedTriggerGrps)) {
            return false;
        }
        QrtzPausedTriggerGrps other = (QrtzPausedTriggerGrps) object;
        if ((this.qrtzPausedTriggerGrpsPK == null && other.qrtzPausedTriggerGrpsPK != null) || (this.qrtzPausedTriggerGrpsPK != null && !this.qrtzPausedTriggerGrpsPK.equals(other.qrtzPausedTriggerGrpsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzPausedTriggerGrps[ qrtzPausedTriggerGrpsPK=" + qrtzPausedTriggerGrpsPK + " ]";
    }

}
