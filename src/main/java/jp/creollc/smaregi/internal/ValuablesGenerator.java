package jp.creollc.smaregi.internal;

import com.google.common.base.CaseFormat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FilenameUtils.getFullPath;

/**
 * Valuable generator
 *
 * <p>
 * Generate Smaregi API POJO / Const class from a source value list.
 * <p>
 * The format is as follows:
 * <code>
 * Product
 * storeId
 * productId
 * </code>
 * The first value is the class name (table name) and values follow
 *
 * To run this generator, please @see jp.creollc.smaregi.internal.ValuablesGeneratorTest
 *
 * @author Yasuyuki Takeo
 */
public class ValuablesGenerator {

    /**
     * Smaregi API POJO / Const class Generator
     *
     * @param args arg1 is an input file path. arg2 is the directory path to output.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        List<String> values = FileUtils.readLines(new File(args[0]), StandardCharsets.UTF_8);
        String outputPath = FilenameUtils.getFullPathNoEndSeparator(args[1]);
        String outputBaseName = outputPath + File.separator + values.get(0);

        // Generate constants class
        List<String> constClassValues = constantsClassGenerator(values);
        FileUtils.writeLines(new File(outputBaseName+ "Constants.java"), constClassValues, "\n");

        // Generate class
        List<String> classValues = classGenerator(values);
        FileUtils.writeLines(new File(outputBaseName+ ".java"), classValues, "\n");
    }

    /**
     * Constant Class Generator
     *
     * @param values
     * @return
     */
    protected static List<String> constantsClassGenerator(List<String> values) {
        List<String> output = new ArrayList<>();
        List<String> processedValue = new ArrayList<>();

        processedValue.addAll(values);
        processedValue.remove(0);
        String modelName = values.get(0);

        output.add("public class " + modelName + "Constants {");

        List<String> convValues = new ArrayList<>();

        for (String value : processedValue) {
            final String upper_snake = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.trim(value));

            StringBuffer sb = new StringBuffer();
            sb
                .append("public static final String ")
                .append(upper_snake)
                .append(" = \"" + StringUtils.trim(value) + "\";");
            convValues.add(sb.toString());
        }

        output.addAll(convValues);
        output.add("}");

        return output;
    }

    /**
     * Class Generator
     *
     * @param values
     * @return
     */
    protected static List<String> classGenerator(List<String> values) {
        List<String> output = new ArrayList<>();
        List<String> processedValue = new ArrayList<>();

        processedValue.addAll(values);
        processedValue.remove(0);
        String modelName = values.get(0);

        output.add("@Data");
        output.add("@JsonInclude(JsonInclude.Include.NON_EMPTY)");
        output.add("public class " + modelName + " {");

        List<String> convValues = new ArrayList<>();

        for (String value : processedValue) {
            StringBuffer sb = new StringBuffer();
            sb
                .append("public String ")
                .append(StringUtils.trim(value))
                .append(";");
            convValues.add(sb.toString());
        }

        output.addAll(convValues);
        output.add("}");

        return output;
    }

    /**
     * Genelating values for a class
     *
     * @param values
     * @return
     */
    protected static List<String> valueGenerator(String prefix, List<String> values) {

        List<String> output = new ArrayList<>();

        for (String value : values) {
            StringBuffer sb = new StringBuffer();
            sb
                .append(prefix)
                .append(value)
                .append(";");
            output.add(sb.toString());
        }

        return output;
    }
}
