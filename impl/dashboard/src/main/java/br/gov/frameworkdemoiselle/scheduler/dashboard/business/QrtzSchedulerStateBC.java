package br.gov.frameworkdemoiselle.scheduler.dashboard.business;

import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerState;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerStatePK;
import br.gov.frameworkdemoiselle.scheduler.dashboard.persistence.QrtzSchedulerStateDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class QrtzSchedulerStateBC extends DelegateCrud<QrtzSchedulerState, QrtzSchedulerStatePK, QrtzSchedulerStateDAO> {

    private static final long serialVersionUID = 1L;

}
