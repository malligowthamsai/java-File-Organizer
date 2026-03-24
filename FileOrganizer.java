import java.io.File;

public class FileOrganizer {

    public static void main(String[] args) {

        // 🔹 Change this path to your folder
        String folderPath = "C:\\Users\\cynic\\Desktop\\check\\dude";

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path!");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No files found.");
            return;
        }

        for (File file : files) {

            if (file.isFile()) {

                String name = file.getName();
                String extension = getExtension(name);

                String category = getCategory(extension);

                File newDir = new File(folderPath + "\\" + category);

                // Create folder if not exists
                if (!newDir.exists()) {
                    newDir.mkdir();
                }

                File newFile = new File(newDir, file.getName());

                boolean moved = file.renameTo(newFile);

                if (moved) {
                    System.out.println("Moved: " + name + " → " + category);
                } else {
                    System.out.println("Failed: " + name);
                }
            }
        }

        System.out.println("\nOrganizing Complete!");
    }

    // Get file extension
    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            return fileName.substring(index + 1).toLowerCase();
        }
        return "";
    }

    // Categorize file types
    public static String getCategory(String ext) {

        switch (ext) {

            case "jpg":
            case "png":
            case "jpeg":
            case "gif":
                return "Images";

            case "pdf":
            case "doc":
            case "docx":
            case "txt":
                return "Documents";

            case "mp4":
            case "mkv":
            case "avi":
                return "Videos";

            case "mp3":
            case "wav":
                return "Audio";

            case "java":
            case "py":
            case "js":
            case "html":
            case "css":
                return "Code";

            default:
                return "Others";
        }
    }
}
