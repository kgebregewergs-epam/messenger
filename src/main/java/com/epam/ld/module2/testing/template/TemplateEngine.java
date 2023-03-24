package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.Set;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    Set<String> validTemplates = Set.of("${subject}", "${body}", "${cc}");

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
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
    public boolean checkTemplate(Template template) {
        if (template.getTag() == null) {
            throw new IllegalArgumentException("Provided with Invalid tag");
        }

        if (template.getValue() == null) {
            throw new IllegalArgumentException("Provided with Invalid tag");
        }

        return validTemplates.contains(template.getTag());
    }
}
