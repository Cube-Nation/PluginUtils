package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandDuplicateMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax.TestInvalidCommandMinMax1;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax.TestInvalidCommandMinMax2;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax.TestInvalidCommandMinMaxOk;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestInvalidCommandMainSub;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestInvalidCommandWorld;

public class CommandValidatorTest extends AbstractTest {
    @Test
    public void testCommandValidator() throws CommandWarmUpException {
        CommandValidator commandValidator = new CommandValidator();

        commandValidator.checkEqual(null, null);
    }

    @Test
    public void testValidateDuplicateMainCommand() {
        try {
            commandsManager.add(TestInvalidCommandDuplicateMain.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandDuplicateMain.class.getName() + "] similar command found for testThreeMainCommand in class "
                    + TestInvalidCommandDuplicateMain.class.getName() + " method testOneMainCommand", e.getMessage());
        }
    }

    @Test
    public void testValidateDuplicateMinMaxCommandOk() throws CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMinMaxOk.class);
    }

    @Test
    public void testValidateDuplicateMinMaxCommand1() throws CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMinMax1.class);
        try {
            commandsManager.add(TestInvalidCommandMinMax2.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandMinMax2.class.getName() + "] similar command found for testMaxCommand in class "
                    + TestInvalidCommandMinMax1.class.getName() + " method testMinCommand", e.getMessage());
        }
    }

    @Test
    public void testValidateDuplicateMinMaxCommand2() throws CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMinMax2.class);
        try {
            commandsManager.add(TestInvalidCommandMinMax1.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandMinMax1.class.getName() + "] similar command found for testMinCommand in class "
                    + TestInvalidCommandMinMax2.class.getName() + " method testMaxCommand", e.getMessage());
        }
    }

    @Test
    public void testValidateDuplicateWorldCommand() throws CommandWarmUpException {
        try {
            commandsManager.add(TestInvalidCommandWorld.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWorld.class.getName() + "] similar command found for testFiveWorldCommand in class "
                    + TestInvalidCommandWorld.class.getName() + " method testOneWorldCommand", e.getMessage());
        }
    }

    @Test
    public void testValidateDuplicateSubCommand() throws CommandWarmUpException {
        try {
            commandsManager.add(TestInvalidCommandMainSub.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandMainSub.class.getName() + "] similar command found for testThreeMainOneSubCommand in class "
                    + TestInvalidCommandMainSub.class.getName() + " method testTwoMainOneSubCommand", e.getMessage());
        }
    }
}
