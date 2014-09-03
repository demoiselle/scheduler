/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.persistence;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggersPK;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

/**
 *
 * @author 70744416353
 */
@PersistenceController
public class QrtzCronTriggersDAO extends JPACrud<QrtzCronTriggers, QrtzCronTriggersPK> {

    private static final long serialVersionUID = 1L;

    public QrtzCronTriggers finQrtzCronTriggers(String instanceName) {
        return null;
    }

}
