package com.example.tests;

import com.example.annotations.TestCase;
import com.example.entities.Reminder;
import com.example.groups.Groups;
import com.example.groups.Sprints;
import com.example.groups.Teams;
import com.example.steps.ui.CommonUiSteps;
import com.example.steps.ui.NavigationSteps;
import com.example.steps.ui.ReminderSteps;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;

public class RemindersTest {

    @DataProvider
    public Object[][] createReminderData() {
        return new Object[][]{
                new Object[]{
                        new Reminder().text("Buy flowers").time(OffsetDateTime.now().plusDays(1)).done(false)
                }
        };
    }

    @Test(description = "Create reminder",
            dataProvider = "createReminderData",
            groups = {Groups.SMOKE, Teams.ALPHA, Sprints._1})
    @TestCase("REM-132")
    public void createReminder(Reminder reminder) {
        NavigationSteps.openRemindersApp();
        ReminderSteps.addReminder(reminder);
        ReminderSteps.checkLastReminder(reminder);
    }

    @DataProvider
    public Object[][] updateReminderData() {
        return new Object[][]{
                new Object[]{
                        new Reminder().text("Old text").time(OffsetDateTime.now().plusDays(1)).done(false),
                        new Reminder().text("New text").time(OffsetDateTime.now().plusDays(2)).done(false)
                }
        };
    }

    @Test(description = "Update reminder",
            dataProvider = "updateReminderData",
            groups = {Teams.BETA, Sprints._1})
    @TestCase("REM-157")
    public void updateReminder(Reminder oldReminder, Reminder newReminder) {
        NavigationSteps.openRemindersApp();
        ReminderSteps.addReminder(oldReminder);
        ReminderSteps.updateLastReminder(newReminder);
        CommonUiSteps.refreshPage();
        ReminderSteps.checkLastReminder(newReminder);
    }

    @DataProvider
    public Object[][] deleteReminderData() {
        return new Object[][]{
                new Object[]{
                        new Reminder().text("Delete me").time(OffsetDateTime.now().plusDays(5)).done(true)
                }
        };
    }

    @Test(description = "Delete reminder",
            dataProvider = "deleteReminderData",
            groups = {Teams.GAMMA, Sprints._1})
    @TestCase("REM-171")
    public void deleteReminder(Reminder reminder) {
        NavigationSteps.openRemindersApp();
        ReminderSteps.addReminder(reminder);
        ReminderSteps.deleteLastReminder();
        CommonUiSteps.refreshPage();
        ReminderSteps.checkReminderIsAbsent(reminder);
    }

}