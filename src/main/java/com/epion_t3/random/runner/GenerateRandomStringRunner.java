package com.epion_t3.random.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.random.command.GenerateRandomString;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.basics.StringGenerator;
import org.slf4j.Logger;

public class GenerateRandomStringRunner extends AbstractCommandRunner<GenerateRandomString> {

    @Override
    public CommandResult execute(GenerateRandomString command, Logger logger) throws Exception {
        StringGenerator string = JRand.string();
        String generateString = string.length(command.getLength()).gen();
        logger.info("Generated String : {}", generateString);
        setVariable(command.getTarget(), generateString);
        return CommandResult.getSuccess();
    }
}
