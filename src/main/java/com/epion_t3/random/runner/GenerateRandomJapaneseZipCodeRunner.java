package com.epion_t3.random.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.random.bean.JapaneseZipCodeData;
import com.epion_t3.random.command.GenerateRandomJapaneseZipCode;
import com.epion_t3.random.message.RandomMessages;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class GenerateRandomJapaneseZipCodeRunner extends AbstractCommandRunner<GenerateRandomJapaneseZipCode> {

    private static final String japaneseZipCodeDataResourceFile = "japanese_zipcode_data.yml";

    @Override
    public CommandResult execute(GenerateRandomJapaneseZipCode command, Logger logger) throws Exception {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (InputStream is =
                     GenerateRandomJapaneseZipCodeRunner.class.getClassLoader()
                             .getResourceAsStream(japaneseZipCodeDataResourceFile);) {
            JapaneseZipCodeData jzcd = objectMapper.readValue(is, JapaneseZipCodeData.class);
            String zipCode = jzcd.getZipcode().get(RandomUtils.nextInt(0, jzcd.getZipcode().size() - 1));
            logger.info("Generated ZipCode : {}", zipCode);
            setVariable(command.getTarget(), zipCode);
        } catch (IOException e) {
            throw new SystemException(RandomMessages.RANDOM_ERR_9002, e);
        }
        return CommandResult.getSuccess();
    }
}
