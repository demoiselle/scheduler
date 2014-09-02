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
public class QrtzJobDetailsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "SCHED_NAME", nullable = false, length = 120)
    private String schedName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "JOB_NAME", nullable = false, length = 200)
    private String jobName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "JOB_GROUP", nullable = false, length = 200)
    private String jobGroup;

    public QrtzJobDetailsPK() {
    }

    public QrtzJobDetailsPK(String schedName, String jobName, String jobGroup) {
        this.schedName = schedName;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (schedName != null ? schedName.hashCode() : 0);
        hash += (jobName != null ? jobName.hashCode() : 0);
        hash += (jobGroup != null ? jobGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QrtzJobDetailsPK)) {
            return false;
        }
        QrtzJobDetailsPK other = (QrtzJobDetailsPK) object;
        if ((this.schedName == null && other.schedName != null) || (this.schedName != null && !this.schedName.equals(other.schedName))) {
            return false;
        }
        if ((this.jobName == null && other.jobName != null) || (this.jobName != null && !this.jobName.equals(other.jobName))) {
            return false;
        }
        if ((this.jobGroup == null && other.jobGroup != null) || (this.jobGroup != null && !this.jobGroup.equals(other.jobGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzJobDetailsPK[ schedName=" + schedName + ", jobName=" + jobName + ", jobGroup=" + jobGroup + " ]";
    }

}
