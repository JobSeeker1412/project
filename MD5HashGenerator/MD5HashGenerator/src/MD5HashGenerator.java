import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;

public class MD5HashGenerator {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("input.json");
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            JsonObject student = jsonObject.getAsJsonObject("student");
            String firstName = student.get("first_name").getAsString().toLowerCase();
            String rollNumber = student.get("roll_number").getAsString().toLowerCase();

            String concatenatedString = firstName + rollNumber;
            String md5Hash = getMD5Hash(concatenatedString);

            FileWriter writer = new FileWriter("output.txt");
            writer.write(md5Hash);
            writer.close();

            System.out.println(md5Hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMD5Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
