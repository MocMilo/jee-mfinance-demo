package com.infoshare.web.adminpanel.agentservice;

import com.infoshare.web.adminpanel.email.MailSender;
import com.infoshare.web.adminpanel.email.ReportComponents;
import com.infoshare.web.adminpanel.trigger.ITerminable;
import com.infoshare.web.adminpanel.trigger.ITriggerable;
import com.infoshare.web.adminpanel.trigger.Trigger;
import com.infoshare.web.analyzer.IFavouriteService;
import com.infoshare.web.api.client.ReportClient;
import com.infoshare.web.container.ModelContainer;
import com.infoshare.web.user.report.IUserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class Agent implements ITriggerable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Agent.class);

    private ITaskService taskService;
    private IFavouriteService favouriteService;
    private IUserActivityService userActivityService;
    private List<Task> triggeredTasks = new ArrayList<>();
    private List<ITerminable> triggerProviders = new ArrayList<>();

    public Agent(ITaskService taskService, IFavouriteService favouriteService, IUserActivityService userActivityService) {
        this.taskService = taskService;
        this.favouriteService = favouriteService;
        this.userActivityService = userActivityService;
    }

    public Agent() {
    }

    @Override
    public void executeAction() {
        this.doAgentJob();
    }

    private void doAgentJob() {
        LOGGER.info("Agent job execution - START");
        this.checkActualTime();
        List<Task> userTasks = taskService.getAllTasks();

        LOGGER.info("number of tasks {}", userTasks.size());

        if (!triggeredTasks.isEmpty()) {
            LOGGER.info("Triggered tasks size: {} ", triggeredTasks.size());
        }

        for (Task item : userTasks) {
            if (item.isActive() && !hasTriggeredStatus(item) && isActualTask(item)) {
                this.startTask(item);
            } else {

                List<Task> toRemoveFromTriggeredTasks = new ArrayList<>();
                List<ITerminable> toRemoveFromTriggeredProviders = new ArrayList<>();

                if (!triggeredTasks.isEmpty()) {
                    for (Task task : triggeredTasks) {
                        LOGGER.info("Evautated Task Id:{}, Name:{}, isTaskToKill:{} ",
                                task.getId(), task.getTaskName(), isTaskToKill(task));

                        if (isTaskToKill(task)) {
                            if (!triggerProviders.isEmpty())
                                for (ITerminable triggerProvider : triggerProviders) {
                                    if (triggerProvider.getTaskId() == task.getId()) {
                                        triggerProvider.killAction();
                                        LOGGER.info("Killing Task Id:{}, Name:{} ",
                                                task.getId(),
                                                task.getTaskName());
                                        toRemoveFromTriggeredTasks.add(task);
                                        toRemoveFromTriggeredProviders.add(triggerProvider);
                                    }
                                }
                        }
                    }
                    doCleanLists(toRemoveFromTriggeredProviders, toRemoveFromTriggeredTasks);
                }
            }
        }
        LOGGER.info("Agent job execution - END");
    }


    private void doCleanLists(List<ITerminable> terminableList, List<Task> taskList) {
        for (Task task : taskList) triggeredTasks.remove(task);
        for (ITerminable item : terminableList) triggerProviders.remove(item);
    }

    public void forceShutDownAllTriggeredTasks() {
        if (!triggeredTasks.isEmpty()) {
            for (Task task : triggeredTasks) {
                LOGGER.info("Evautated Task Id:{}, Name:{}, isTaskToKill:{} ",
                        task.getId(), task.getTaskName(), isTaskToKill(task));
                if (!triggerProviders.isEmpty())
                    for (ITerminable triggerProvider : triggerProviders) {
                        if (triggerProvider.getTaskId() == task.getId()) {
                            triggerProvider.killAction();
                            LOGGER.info("Forced ShutDown Task Id:{}, Name:{} ",
                                    task.getId(),
                                    task.getTaskName());
                        }
                    }
            }
            this.triggeredTasks.clear();
            this.triggerProviders.clear();
        }
    }

    private LocalDate checkActualTime() {
        return LocalDate.now();
    }

    private boolean isTaskToKill(Task task) {
        Task actualTask = taskService.getTaskbyId(task.getId());
        if (!actualTask.isActive() || !isActualTask(actualTask)) {
            return true;
        } else {
            return false;
        }
    }

    private void startTask(Task task) {

        Trigger trigger = new Trigger(getActionProvider(task), task.getStartDelay(), task.getTimeSpan(), TimeUnit.SECONDS);
        triggeredTasks.add(task);
        trigger.setTriggeredTask(task);
        trigger.startAction();

        triggerProviders.add(trigger);

        LOGGER.info("Agent started Task: {} {}", task.getId(), task.getTaskName());
    }

    private ITriggerable getActionProvider(Task task) {
        switch (task.getTaskTypeName()) {
            case "EMAIL_SENDING": {
                ReportComponents reportComponents = new ReportComponents(favouriteService);
                return new MailSender(reportComponents);
            }
            case "MAIN_CONTAINER_UPDATING": {
                return new ModelContainer();
            }
            case "API_REPORT_DATA_UPDATING": {
                return new ReportClient(userActivityService);
            }
            default: {
                return new UnsupportedActionProvider();
            }
        }
    }

    private boolean hasTriggeredStatus(Task task) {
        LOGGER.info("Number of triggered tasks:{} ", triggeredTasks.size());
        LOGGER.info("Checked Task Id:{} ", task.getId());

        Predicate<Task> hasItem = x -> Objects.equals(x.getId(), task.getId());

        Optional<Task> any = triggeredTasks.stream()
                .filter(hasItem)
                .findAny();

        if (any.isPresent()) {
            LOGGER.info("Triggered status:true TaskId: {}", task.getId());
            return true;
        } else {
            LOGGER.info("Triggered status:false TaskId: {}", task.getId());
            return false;
        }
    }

    private boolean isActualTask(Task task) {
        boolean isActual = task.getStartDate().isBefore(this.checkActualTime())
                && task.getEndDate().isAfter(this.checkActualTime());

        LOGGER.info("Is task actual:{}, TaskId:{}", isActual, task.getId());
        return isActual;
    }

}



