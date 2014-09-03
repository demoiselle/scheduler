/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.business;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggersPK;
import br.gov.frameworkdemoiselle.scheduler.dashboard.persistence.QrtzFiredTriggersDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import java.util.List;

/**
 *
 * @author 70744416353
 */
@BusinessController
public class QrtzFiredTriggersBC extends DelegateCrud<QrtzFiredTriggers, QrtzFiredTriggersPK, QrtzFiredTriggersDAO> {

    private static final long serialVersionUID = 1L;

     public List<QrtzFiredTriggers> findQrtzFiredTriggers(String instanceName) {
        return getDelegate().findQrtzFiredTriggers(instanceName);
    }

}
