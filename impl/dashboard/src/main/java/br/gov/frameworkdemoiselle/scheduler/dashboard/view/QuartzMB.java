/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.scheduler.dashboard.view;

import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzCronTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzFiredTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzJobDetailsBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzSchedulerStateBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.business.QrtzTriggersBC;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzCronTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggers;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzFiredTriggersPK;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzJobDetails;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzSchedulerState;
import br.gov.frameworkdemoiselle.scheduler.dashboard.domain.QrtzTriggers;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

@ViewController
public class QuartzMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private MindmapNode root;

    private MindmapNode selectedNode;

    private Long timeCheckin = 20l;

    private List<QrtzSchedulerState> listSchedulerState = new ArrayList<QrtzSchedulerState>();
    private List<QrtzJobDetails> listQrtzJobDetails = new ArrayList<QrtzJobDetails>();
    private List<QrtzTriggers> listQrtzTriggerses = new ArrayList<QrtzTriggers>();

    //@Inject
    private QrtzSchedulerStateBC qrtzSchedulerStateBC = Beans.getReference(QrtzSchedulerStateBC.class);
    //@Inject
    private QrtzTriggersBC qrtzTriggersBC = Beans.getReference(QrtzTriggersBC.class);
    //@Inject
    private QrtzJobDetailsBC qrtzJobDetailsBC = Beans.getReference(QrtzJobDetailsBC.class);
    //@Inject
    private QrtzCronTriggersBC qrtzCronTriggersBC = Beans.getReference(QrtzCronTriggersBC.class);
    //@Inject
    private QrtzFiredTriggersBC qrtzFiredTriggersBC = Beans.getReference(QrtzFiredTriggersBC.class);

    public QuartzMB() {
        updateScheduler();
        updateJobs();
        updateTriggers();
    }

    private void updateJobs() {
        listQrtzJobDetails = qrtzJobDetailsBC.findAll();
    }

    private void updateTriggers() {
        listQrtzTriggerses = qrtzTriggersBC.findAll();
    }

    private void updateScheduler() {

        listSchedulerState = qrtzSchedulerStateBC.findAll();

        String name = listSchedulerState.get(0).getQrtzSchedulerStatePK().getSchedName();
        timeCheckin = (listSchedulerState.get(0).getCheckinInterval() / 1000);

        root = new DefaultMindmapNode(name, name, "FFCC00", false);
        MindmapNode ips;

        for (QrtzSchedulerState object : listSchedulerState) {

            List<QrtzFiredTriggers> lista = findQrtzFiredTriggers(object.getQrtzSchedulerStatePK().getInstanceName());

            if (lista != null && !lista.isEmpty()) {

                ips = new DefaultMindmapNode(object.getQrtzSchedulerStatePK().getInstanceName(), object.getQrtzSchedulerStatePK().getInstanceName() + " # Last Checkin -" + new Date(object.getLastCheckinTime()), "6e9000", true);

                for (QrtzFiredTriggers qrtzFiredTriggers : lista) {
                    ips.addNode(new DefaultMindmapNode("" + qrtzFiredTriggers.getFiredTime(), "" + qrtzFiredTriggers.getSchedTime(), "db0c0c", true));
                }

            } else {
                ips = new DefaultMindmapNode(object.getQrtzSchedulerStatePK().getInstanceName(), object.getQrtzSchedulerStatePK().getInstanceName() + " # Last Checkin -" + new Date(object.getLastCheckinTime()), "4262c5", true);
            }

            root.addNode(ips);
        }
    }

    private QrtzCronTriggers findQrtzCronTriggers(String instanceName) {
        return null;
    }

    private List<QrtzFiredTriggers> findQrtzFiredTriggers(String instanceName) {
        return qrtzFiredTriggersBC.findQrtzFiredTriggers(instanceName);
    }

    public Long getTimeCheckin() {
        return timeCheckin;
    }

    public List<QrtzSchedulerState> getListSchedulerState() {
        updateScheduler();
        return listSchedulerState;
    }

    public List<QrtzJobDetails> getListQrtzJobDetails() {
        updateJobs();
        return listQrtzJobDetails;
    }

    public List<QrtzTriggers> getListQrtzTriggerses() {
        updateTriggers();
        return listQrtzTriggerses;
    }

    public MindmapNode getRoot() {
        updateScheduler();
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();

        //populate if not already loaded
//        if (node.getChildren().isEmpty()) {
//            Object label = node.getLabel();
//            List<QrtzFiredTriggers> lista = findQrtzFiredTriggers(label.toString());
//            if (lista != null && !lista.isEmpty()) {
//                for (QrtzFiredTriggers qrtzFiredTriggers : lista) {
//                    node.addNode(new DefaultMindmapNode("" + qrtzFiredTriggers.getFiredTime(), "" + qrtzFiredTriggers.getSchedTime(), "db0c0c", false));
//                }
//            }
//        }
    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }
}
