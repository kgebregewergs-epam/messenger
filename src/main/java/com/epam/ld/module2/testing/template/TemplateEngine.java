package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    Set<String> validTemplates = new HashSet<>(Arrays.asList("${subject}", "${body}", "${cc}"));
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client){
        boolean isValid = checkTemplate(template);

        if (isValid && client != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(template.getValue());
            stringBuilder.append(": ");
            stringBuilder.append(template.getTag());
            return stringBuilder.toString();
        }

        return null;
    }

    /**
     * check if the template is valid.
     *
     * @param template the template
     * @return boolean
     */
    private boolean checkTemplate(Template template) {
        if (!(validTemplates.contains(template.getTag())) && template.getValue() == null) {
            throw new IllegalArgumentException("Provided with Invalid tag and value");
        }

        return validTemplates.contains(template.getTag());
    }
}
