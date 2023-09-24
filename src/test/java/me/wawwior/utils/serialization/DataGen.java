package me.wawwior.utils.serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataGen {

    public static void main(String[] args) throws IOException {

        for (int i = 2; i <= 22; i++) {
            File file = new File("src/main/java/me/wawwior/utils/common/functions/Function" + i + ".java");
            if (!file.exists()) file.createNewFile();
            StringBuilder builder = new StringBuilder();
            builder.append("package me.wawwior.utils.common.functions;\n\n");
            builder.append("public interface Function").append(i).append("<");
            for (int j = 1; j <= i; j++) {
                builder.append("P").append(j);
                if (j != i) {
                    builder.append(", ");
                }
            }
            builder.append(", R> {\n\n");
            builder.append("    R apply(");
            for (int j = 1; j <= i; j++) {
                builder.append("P").append(j).append(" p").append(j);
                if (j != i) {
                    builder.append(", ");
                }
            }
            builder.append(");\n\n");
            builder.append("    default Function").append(i - 1).append("<");
            for (int j = 1; j <= i - 1; j++) {
                builder.append("P").append(j);
                if (j != i - 1) {
                    builder.append(", ");
                }
            }
            builder.append(", R> bind(P").append(i).append(" p").append(i).append(") {\n");
            builder.append("    return (");
            for (int j = 1; j <= i - 1; j++) {
                builder.append("P").append(j).append(" p").append(j);
                if (j != i - 1) {
                    builder.append(", ");
                }
            }
            builder.append(") -> apply(");
            for (int j = 1; j <= i - 1; j++) {
                builder.append("p").append(j);
                if (j != i - 1) {
                    builder.append(", ");
                }
            }
            builder.append(", p").append(i).append(");\n");
            builder.append("}\n\n");
            builder.append("}\n");
            FileWriter writer = new FileWriter(file);
            writer.write(builder.toString());
            writer.close();
        }

    }

}
