package com.epion_t3.random.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.random.command.GenerateRandomBirthdayString;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.person.BirthdayGenerator;
import org.slf4j.Logger;

/**
 *
 */
public class GenerateRandomBirthdayStringRunner extends AbstractCommandRunner<GenerateRandomBirthdayString> {

    @Override
    public CommandResult execute(GenerateRandomBirthdayString command, Logger logger) throws Exception {
        BirthdayGenerator birthday = JRand.birthday();
        String generateBirthday = birthday.format(command.getFormat()).adult().genString();
        logger.info("Generated Birthday String : {}", generateBirthday);
        setVariable(command.getTarget(), generateBirthday);
        return CommandResult.getSuccess();
    }
}
