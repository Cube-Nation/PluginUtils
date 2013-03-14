package de.cubenation.plugins.utils.commandapi;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandWarmUpException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandDuplicateMain1;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception.TestInvalidCommandDuplicateMain2;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax.TestInvalidCommandMinMax1;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax.TestInvalidCommandMinMax2;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.minmax.TestInvalidCommandMinMaxOk;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestInvalidCommandMainSub1;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestInvalidCommandMainSub2;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestInvalidCommandWorld1;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestInvalidCommandWorld2;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.world.TestValidCommandMultiWorld2;

public class CommandValidatorTest extends AbstractTest {
    @Test
    public void testCommandValidator() throws CommandWarmUpException {
        CommandValidator commandValidator = new CommandValidator();

        commandValidator.checkEqual(null, null);
    }

    @Test
    public void testValidateDuplicateMainCommand() throws CommandWarmUpException {
        commandsManager.add(TestInvalidCommandDuplicateMain1.class);
        try {
            commandsManager.add(TestInvalidCommandDuplicateMain2.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandDuplicateMain2.class.getName() + "] similar command found for testThreeMainCommand in class "
                    + TestInvalidCommandDuplicateMain1.class.getName() + " method testOneMainCommand", e.getMessage());
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
    public void testValidateDuplicateWorld1Command() throws CommandWarmUpException {
        commandsManager.add(TestInvalidCommandWorld1.class);
        try {
            commandsManager.add(TestInvalidCommandWorld2.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandWorld2.class.getName() + "] similar command found for testFiveWorldCommand in class "
                    + TestInvalidCommandWorld1.class.getName() + " method testOneWorldCommand", e.getMessage());
        }
    }

    @Test
    public void testValidateDuplicateWorld2Command() throws CommandWarmUpException {
        commandsManager.add(TestValidCommandMultiWorld2.class);
    }

    @Test
    public void testValidateDuplicateSubCommand() throws CommandWarmUpException {
        commandsManager.add(TestInvalidCommandMainSub1.class);
        try {
            commandsManager.add(TestInvalidCommandMainSub2.class);
            Assert.fail("expected exception");
        } catch (CommandWarmUpException e) {
            Assert.assertEquals("[" + TestInvalidCommandMainSub2.class.getName() + "] similar command found for testThreeMainOneSubCommand in class "
                    + TestInvalidCommandMainSub1.class.getName() + " method testTwoMainOneSubCommand", e.getMessage());
        }
    }
}
