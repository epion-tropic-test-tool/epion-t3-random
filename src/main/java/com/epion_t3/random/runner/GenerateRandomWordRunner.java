package com.epion_t3.random.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.random.command.GenerateRandomWord;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.text.WordGenerator;
import org.slf4j.Logger;

public class GenerateRandomWordRunner extends AbstractCommandRunner<GenerateRandomWord> {

    @Override
    public CommandResult execute(GenerateRandomWord command, Logger logger) throws Exception {
        WordGenerator word = JRand.word();
        String generateWord = word.length(command.getLength()).gen();
        logger.info("Generated Word : {}", generateWord);
        setVariable(command.getTarget(), generateWord);
        return CommandResult.getSuccess();
    }
}
