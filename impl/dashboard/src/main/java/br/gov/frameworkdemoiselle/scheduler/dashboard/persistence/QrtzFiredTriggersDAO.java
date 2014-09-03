/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.persistence;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggersPK;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import java.util.List;

/**
 *
 * @author 70744416353
 */
@PersistenceController
public class QrtzFiredTriggersDAO extends JPACrud<QrtzFiredTriggers, QrtzFiredTriggersPK> {

    private static final long serialVersionUID = 1L;

    public List<QrtzFiredTriggers> findQrtzFiredTriggers(String instanceName) {
        return getEntityManager().createNamedQuery("QrtzFiredTriggers.findByInstanceName").setParameter("instanceName", instanceName).getResultList();
    }

}
