package addressbook.generators;

import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDateGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;
    @Parameter(names = "-f", description = "target file")
    public String file;
    @Parameter(names = "-g", description = "data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDateGenerator generator = new GroupDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }
        generator.run();


    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            save(groups, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(groups, new File(file));
        } else System.out.println("unknown format" + format);
    }

    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file);) {
            writer.write(json);
        }
    }

    private void save(List<GroupData> groups, File file) throws IOException {
        try (Writer writer = new FileWriter(file);) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
