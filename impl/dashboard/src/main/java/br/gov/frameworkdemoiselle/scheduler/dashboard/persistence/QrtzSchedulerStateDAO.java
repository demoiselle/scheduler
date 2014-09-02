package br.gov.frameworkdemoiselle.scheduler.dashboard.persistence;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerState;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerStatePK;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class QrtzSchedulerStateDAO extends JPACrud<QrtzSchedulerState, QrtzSchedulerStatePK> {

    private static final long serialVersionUID = 1L;

}
