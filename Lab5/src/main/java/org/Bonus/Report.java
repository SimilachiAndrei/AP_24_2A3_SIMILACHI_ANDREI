package org.Bonus;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@Setter
public class Report implements Command {
    private Repository repository;

    public static void createTemplateFile() {
        String templateContent =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Repository Report</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>Repository Report</h1>\n" +
                        "    <ul>\n" +
                        "    <#list files as file>\n" +
                        "        <li>${file}</li>\n" +
                        "    </#list>\n" +
                        "    </ul>\n" +
                        "</body>\n" +
                        "</html>";

        try {
            FileWriter fileWriter = new FileWriter("report_template.ftl");
            fileWriter.write(templateContent);
            fileWriter.close();
            System.out.println("Template file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDefaultEncoding("UTF-8");

        try {
            // Set the directory where the template file is located
            cfg.setDirectoryForTemplateLoading(new File("."));

            // Check if the template file exists
            File templateFile = new File("report_template.ftl");
            if (!templateFile.exists()) {
                createTemplateFile();
            }

            // Load template
            Template template = cfg.getTemplate("report_template.ftl");

            // Create data model
            Map<String, Object> data = new HashMap<>();
            data.put("files", repository.getFileNames()); // Assuming getFileNames() returns a list of file names

            // Merge data model with template
            StringWriter out = new StringWriter();
            template.process(data, out);

            // Output HTML report
            String html = out.toString();
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("report.html"), "UTF-8"))) {
                writer.write(html);
            }

            System.out.println("HTML report generated successfully.");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

}
