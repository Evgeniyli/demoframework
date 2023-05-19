package com.ui.core.parser;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class JSONParserUtils {

    /**
     * Parse JSON file
     *
     * @param fileName path to JSON file
     * @return Container with all values
     */
    public static Map<String, Map<String, String>> parseJSON(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fileName), new TypeReference<Map<String, Map<String, String>>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
